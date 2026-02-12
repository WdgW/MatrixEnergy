package matrix_energy.lib.type.item

import arc.graphics.Color
import arc.struct.Seq

open class CompositeOreItem(
    name: String,
    items: Seq<Any>,
    color: Color = Color.black.cpy(),
    localizedName: String = name
) : CompositeItem(name, items, color, localizedName) {

}
