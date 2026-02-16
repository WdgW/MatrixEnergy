package matrix_energy.genesis.kinetic

import mindustry.gen.Building
import mindustry.world.Block

/**
 * @author dg
 */
open class KineticBlock(name: String) : Block(name) {
    var hasKinetic = true

    /** 运行所需的最低应力 (Stress) */
    var stressMinNeeded = 0f

    /** 实际所需应力是否随实际转速增长*/
    var stressGrowth = true

    /** 运行所需的最低转速 (RPM) */
    var speedMinNeeded: Int = 1

    /** 是否输出应力 */
    var isOutputForce = false

    /** 输出应力 (Stress) */
    var stressOutput: Float = 0f

    /** 输出转速 (RPM) */
    var speedOutput: Int = 0

    /** 运行所需的最低功率 (W) */
    val powerMinNeeded: Float get() = stressMinNeeded * speedMinNeeded


    //    var Multipliers: Any = TODO()
    open inner class ForceBuild : Building() {
        var force: KineticModule = KineticModule()
    }
}
