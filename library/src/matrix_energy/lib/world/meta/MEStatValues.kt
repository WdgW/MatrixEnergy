package matrix_energy.lib.world.meta

import arc.struct.FloatSeq
import arc.struct.Seq
import arc.util.Scaling
import mindustry.type.Item
import mindustry.ui.Styles
import mindustry.world.meta.StatUnit
import mindustry.world.meta.StatValue
import mindustry.world.meta.StatValues
import mindustry.world.meta.StatValues.withTooltip

object MEStatValues {
    fun composition(items: Seq<Item>, itemPercentage: FloatSeq): StatValue {
        return StatValue {
            it.row()
            it.left().top().defaults();
            items.forEachIndexed() { i, item ->
                it.row()
                it.table(Styles.grayPanel) { tb ->
                    tb.image(item.uiIcon).size(32f).padRight(4f).left().scaling(Scaling.fit).with { i ->
                        withTooltip(
                            i,
                            item, false
                        )
                    }
                    tb.add("${item.localizedName} 占比").padRight(10f).left()
                    StatValues.number(itemPercentage[i], StatUnit.percent).display(tb)
                }.padLeft(5f).padTop(5f).left().top()
            }
        }
    }
}
