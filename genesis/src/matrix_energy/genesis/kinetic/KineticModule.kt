package matrix_energy.genesis.kinetic

import arc.util.io.Reads
import arc.util.io.Writes
import mindustry.world.modules.BlockModule

/**
 * @author dg
 * 偷点懒
 */
class KineticModule : BlockModule() {

    var graph: KineticGraph? = null

    var isOutput: Boolean = false

    /** 是否为顺时针旋转 */
    var isClockwise = false

    /** 应力 (Stress) */
    var stress: Float = 0f

    /** 实际转速 (RPM) */
    var angularVelocity: Short = 0


    override fun write(write: Writes) {
        write.w(isOutput).w(isClockwise).w(stress).w(angularVelocity)
    }

    override fun read(read: Reads) {
        isOutput = read.bool()
        isClockwise = read.bool()
        stress = read.f()
        angularVelocity = read.s()
    }


    //    fun Writes.w(w: Any): Writes {
//        when (w) {
//            is Float -> f(w)
//            is Short -> s(w)
//            is Int -> i(w)
//            is String -> str(w)
//            is Boolean -> bool(w)
//        }
//        return this
//    }

    fun Writes.w(w: Short): Writes {
        s(w.toInt())
        return this
    }

    fun Writes.w(w: Float): Writes {
        f(w)
        return this
    }

    fun Writes.w(w: Int): Writes {
        i(w)
        return this
    }

    fun Writes.w(w: Boolean): Writes {
        bool(w)
        return this
    }

}
