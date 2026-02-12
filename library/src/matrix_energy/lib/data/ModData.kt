package matrix_energy.lib.data

import arc.files.Fi
import matrix_energy.lib.MatrixEnergyLib
import matrix_energy.lib.MatrixEnergyLib.modVersion
import matrix_energy.lib.data.serialization.KryoPool

object ModData {
    val MEDirectory: Fi = MatrixEnergyLib.MEDirectory
    val DataDirectory: Fi = MEDirectory.child(modVersion)

    @JvmStatic
    fun load() {
        DataDirectory.mkdirs()
        KryoPool.load()
    }
}
