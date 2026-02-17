package matrix_energy.genesis.kinetic

import arc.struct.IntSet
import arc.struct.Queue
import arc.struct.Seq
import arc.util.Time
import matrix_energy.genesis.kinetic.KineticBlock.KineticBuild
import kotlin.math.sqrt

/**
 * KineticGraph - 基于机械动力模组概念的应力网络管理系统
 * 
 * 这个类管理机械动力系统中的应力传输，包括：
 * - 应力和转速的计算
 * - 应力平衡和分配
 * - 网络拓扑结构管理
 * - 应力损耗和效率计算
 * 
 * @author dg
 */
class KineticGraph {
    companion object {
        private val queue: Queue<KineticBuild> = Queue<KineticBuild>()
        private val outArray1: Seq<KineticBuild> = Seq<KineticBuild>()
        private val outArray2: Seq<KineticBuild> = Seq<KineticBuild>()
        private val closedSet: IntSet = IntSet()

        // 机械常数
        private const val MAX_ANGULAR_VELOCITY: Int = 3600 // 最大角速度 (RPM)
        private const val MIN_ANGULAR_VELOCITY: Int = 1    // 最小角速度 (RPM)
        private const val EFFICIENCY_LOSS = 0.05f     // 每节点传输效率损失
        private const val INERTIA_FACTOR = 0.1f       // 惯性因子
    }

    // 网络成员
    val producers: Seq<KineticBuild> = Seq<KineticBuild>(false, 32, KineticBuild::class.java)
    val consumers: Seq<KineticBuild> = Seq<KineticBuild>(false, 32, KineticBuild::class.java)
    val transmitters: Seq<KineticBuild> = Seq<KineticBuild>(false, 64, KineticBuild::class.java)
    val all: Seq<KineticBuild> = Seq<KineticBuild>(false, 128, KineticBuild::class.java)

    // 网络状态
    var angularVelocity: Int = 0           // 当前角速度 (RPM)
    var targetAngularVelocity: Int = 0     // 目标角速度 (RPM)
    var networkStress: Float = 0f             // 网络总应力 (Stress)
    var networkPower: Float = 0f              // 网络总功率 (W)
    var networkEfficiency: Float = 1f         // 网络效率 (0-1)
    var isOverloaded: Boolean = false         // 是否过载
    var lastUpdateTime: Float = 0f            // 最后更新时间

    // 网络统计
    var totalNodes: Int = 0
    var totalConnections: Int = 0
    var averagePathLength: Double = 0.0

    /** 网络所需功率 */
    val powerNeeded: Float
        get() = if (angularVelocity <= 0) {
            0f
        } else {
            consumers.sumf {
                val block = it.block as KineticBlock
                // 计算实际应力需求
                val actualStress = if (block.stressGrowth) {
                    // 应力需求随转速增长
                    block.stressMinNeeded * (1 + angularVelocity / MAX_ANGULAR_VELOCITY)
                } else {
                    // 应力需求恒定
                    block.stressMinNeeded
                }
                // 功率 = 应力 × 角速度
                actualStress * angularVelocity
            }
        }

    /** 网络可用功率 */
    val powerAvailable: Float
        get() = if (angularVelocity <= 0) {
            0f
        } else {
            producers.sumf {
                val force = it.force
                force.stress * force.angularVelocity * calculateEfficiency(it)
            }
        }

    /** 网络功率平衡率 */
    val powerBalance: Float
        get() = if (powerNeeded > 0) powerAvailable / powerNeeded else 1f

    /** 网络负载率 */
    val loadFactor: Float
        get() = if (powerAvailable > 0) powerNeeded / powerAvailable else 0f

    /**
     * 计算节点传输效率
     */
    private fun calculateEfficiency(build: KineticBuild): Float {
        var efficiency = 1f
        // 根据距离和连接数计算效率损失
        val distanceFactor = calculateDistanceFactor(build)
        efficiency *= (1 - EFFICIENCY_LOSS * distanceFactor)
        return efficiency.coerceIn(0.1f, 1f)
    }

    /**
     * 计算距离因子（基于网络拓扑）
     */
    private fun calculateDistanceFactor(build: KineticBuild): Float {
        // 简化实现：基于节点在生产者列表中的位置
        val index = producers.indexOf(build)
        return if (index >= 0) index.toFloat() / producers.size else 1f
    }

    /**
     * 更新网络状态
     */
    fun update() {
        // 更新网络统计
        updateNetworkStats()

        // 计算网络应力和功率
        networkStress = calculateNetworkStress()
        networkPower = angularVelocity * networkStress

        // 更新网络效率
        networkEfficiency = calculateNetworkEfficiency().toFloat()

        // 检查过载状态
        isOverloaded = loadFactor > 0.95f

        // 更新所有节点的力模块
        updateKineticModules()

        lastUpdateTime = Time.time
    }


    /**
     * 计算网络应力
     */
    private fun calculateNetworkStress(): Float {
        if (angularVelocity <= 0) return 0f

        val producerStress = producers.sumf { it.force.stress }
        val consumerStress = consumers.sumf {
            val block = it.block as KineticBlock
            // 计算实际应力需求
            if (block.stressGrowth) {
                // 应力需求随转速增长
                block.stressMinNeeded * (1 + angularVelocity / MAX_ANGULAR_VELOCITY)
            } else {
                // 应力需求恒定
                block.stressMinNeeded
            }
        }

        return (producerStress - consumerStress).coerceAtLeast(0f)
    }

    /**
     * 计算网络效率
     */
    private fun calculateNetworkEfficiency(): Double {
        if (transmitters.isEmpty) return 1.0

        val baseEfficiency = 1f - (transmitters.size * EFFICIENCY_LOSS * 0.1f)
        val distanceEfficiency = 1f - (averagePathLength * EFFICIENCY_LOSS * 0.05f)

        return (baseEfficiency * distanceEfficiency).coerceIn(0.5, 1.0)
    }

    /**
     * 更新网络统计信息
     */
    private fun updateNetworkStats() {
        totalNodes = all.size
        totalConnections = calculateTotalConnections()
        averagePathLength = calculateAveragePathLength()
    }

    /**
     * 计算总连接数
     */
    private fun calculateTotalConnections(): Int {
        // 简化实现：每个节点平均有2个连接
        return totalNodes * 2
    }

    /**
     * 计算平均路径长度
     */
    private fun calculateAveragePathLength(): Double {
        if (totalNodes <= 1) return 0.0
        // 简化实现：基于节点数量的估算
        return sqrt(totalNodes.toDouble())
    }

    /**
     * 更新所有节点的力模块
     */
    private fun updateKineticModules() {
        all.each { build ->
            val force = build.force
            force.angularVelocity = angularVelocity.toShort()
            // 根据节点类型设置应力
            when {
                producers.contains(build) -> {
                    // 生产者：提供应力
                    force.stress = (build.block as KineticBlock).stressOutput
                }

                consumers.contains(build) -> {
                    // 消费者：消耗应力
                    val block = build.block as KineticBlock
                    // 计算实际应力需求
                    val actualStress = if (block.stressGrowth) {
                        // 应力需求随转速增长
                        block.stressMinNeeded * (1 + angularVelocity / MAX_ANGULAR_VELOCITY)
                    } else {
                        // 应力需求恒定
                        block.stressMinNeeded
                    }
                    force.stress = -actualStress
                }

                else -> {
                    // 传输者：传递应力（无损耗）
                    force.stress = 0f
                }
            }
        }
    }

    /**
     * 添加节点到网络
     */
    fun addNode(build: KineticBuild) {
        if (!all.contains(build)) {
            all.add(build)

            val block = build.block as KineticBlock
            when {
                block.isOutputForce -> producers.add(build)
                block.stressMinNeeded > 0 -> consumers.add(build)
                else -> transmitters.add(build)
            }

            // 重新计算网络拓扑
            recalculateTopology()
        }
    }

    /**
     * 从网络移除节点
     */
    fun removeNode(build: KineticBuild) {
        all.remove(build)
        producers.remove(build)
        consumers.remove(build)
        transmitters.remove(build)

        // 重新计算网络拓扑
        recalculateTopology()
    }

    /**
     * 重新计算网络拓扑
     */
    private fun recalculateTopology() {
        // 这里可以实现更复杂的拓扑计算算法
        // 目前使用简化实现
        updateNetworkStats()
    }

    /**
     * 获取网络状态信息
     */
    fun getNetworkInfo(): String {
        return """
            |=== 应力网络状态 ===
            |角速度: ${angularVelocity} RPM
            |目标角速度: ${targetAngularVelocity} RPM
            |网络应力: ${networkStress.format(2)} Stress
            |网络功率: ${networkPower.format(2)} W
            |功率需求: ${powerNeeded.format(2)} W
            |功率供应: ${powerAvailable.format(2)} W
            |功率平衡: ${(powerBalance * 100).format(2)}%
            |网络效率: ${(networkEfficiency * 100).format(2)}%
            |负载率: ${(loadFactor * 100).format(2)}%
            |节点数: $totalNodes
            |连接数: $totalConnections
            |平均路径: ${averagePathLength}
            |状态: ${if (isOverloaded) "过载" else "正常"}
            |===================
        """.trimMargin()
    }

    /**
     * 格式化浮点数显示
     */
    private fun Float.format(digits: Int): String = "%.${digits}f".format(this)

    /**
     * 重置网络状态
     */
    fun reset() {
        angularVelocity = 0
        targetAngularVelocity = 0
        networkStress = 0f
        networkPower = 0f
        networkEfficiency = 1f
        isOverloaded = false
        lastUpdateTime = 0f

        producers.clear()
        consumers.clear()
        transmitters.clear()
        all.clear()
    }
}
