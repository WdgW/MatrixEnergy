package matrix_energy.lib.type.item

import arc.graphics.Color
import arc.struct.Seq

open class CompositeOreItem(
    name: String,
    items: Seq<Any>,
    localizedName: String = name,
    color: Color = Color.black.cpy()
) : CompositeItem(name, items, localizedName, color) {

}
