package matrix_energy.genesis.kinetic

import arc.util.io.Reads
import arc.util.io.Writes

/**
 * @author dg
 */
class Force(
    var stress: Float = 0f,
    var angularVelocity: Float = 0f
) {

    fun write(write: Writes) {
        write.f(stress)
        write.f(angularVelocity)
    }

    fun read(read: Reads, version: Int = read.i()) {
        stress = read.f()
        angularVelocity = read.f()

    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Force) return false

        if (stress != other.stress) return false
        if (angularVelocity != other.angularVelocity) return false

        return true
    }

    override fun hashCode(): Int {
        var result = stress.hashCode()
        result = 31 * result + angularVelocity.hashCode()
        return result
    }

    override fun toString(): String {
        return "Force(stress=$stress, angularVelocity=$angularVelocity)"
    }
}
