package matrix_energy.genesis.content

import matrix_energy.genesis.kinetic.KineticShaft
import mindustry.world.meta.BuildVisibility

object Blocks {

    @JvmStatic
    fun load() {

        /**
         * 动能传动轴测试实例
         *
         * 用于测试KineticShaft类的功能和性能
         * 特性：
         * - 基础应力传递功能
         * - 支持横向和纵向放置
         * - 自动连接管理
         * - 应力状态显示
         */
        val kineticShaftTest = KineticShaft("kinetic-shaft-test", "动能传动轴测试版").apply {
            // 基础属性配置
            description = "用于测试动能传动轴功能的测试装置"

            // 物理属性
            size = 1
            health = 200

            // 应力系统配置
            stressMinNeeded = 1f
            stressGrowth = false

            // 旋转配置
            rotate = true
            update = false
            hasShadow = false
            underBullets = true
            canOverdrive = false
            destructible = true

            // 构建配置
            buildVisibility = BuildVisibility.shown
//            category = Category.production

            // 研究配置
            researchCostMultiplier = 1f

            // 构建需求
            requirements = mindustry.type.ItemStack.with(
                mindustry.content.Items.copper, 10,
                mindustry.content.Items.lead, 5
            )
        }
    }
}
