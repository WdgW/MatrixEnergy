package matrix_energy.lib.type.item

import arc.graphics.Color
import arc.struct.FloatSeq
import arc.struct.IntSeq
import arc.struct.Seq
import matrix_energy.lib.interfaces.item.Composite
import matrix_energy.lib.world.meta.MEStat
import matrix_energy.lib.world.meta.MEStatValues
import mindustry.type.Item

open class CompositeItem(
    name: String,
    items: Seq<Any>,
    color: Color = Color.black.cpy(),
    localizedName: String = name
) : MEItem(name, color, localizedName), Composite {
    var items: Seq<Item> = Seq<Item>(true, items.size / 2)
    var itemPercentage: FloatSeq = FloatSeq(true, items.size / 2)
    var itemsTotal: Int

    init {
        var ints = IntSeq(true, items.size / 2)
        for (i in 0 until items.size / 2) {
            this.items.add(items[i * 2] as Item)
            ints.add(items[i * 2 + 1] as Int)
        }
        itemsTotal = ints.sum()
        ints.each {
            itemPercentage.add((it * 100 / itemsTotal).toFloat())
        }
    }


    override fun setStats() {
        super.setStats()
        stats.add(MEStat.composition, MEStatValues.composition(this.items, itemPercentage))
    }
}
