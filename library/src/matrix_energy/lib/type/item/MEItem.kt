package matrix_energy.lib.type.item

import arc.graphics.Color
import arc.util.io.Reads
import arc.util.io.Writes
import matrix_energy.lib.MatrixEnergyInterfaces.MatrixEnergyItem
import mindustry.type.Item

/**
 * @author DG
 */
open class MEItem @JvmOverloads constructor(
    name: String,
    color: Color = Color.black.cpy()
) : Item(name, color), MatrixEnergyItem {
    
    constructor(name: String, localizedName: String, color: Color = Color.black.cpy()): this(name, color){
        this.localizedName = localizedName
    }

    init {
        this.localizedName = localizedName
//        all.add(this)
    }

    fun read(read: Reads, revision: Byte) {
    }

    fun write(write: Writes) {
    }

    override fun setStats() {
        super.setStats()
    }
}
