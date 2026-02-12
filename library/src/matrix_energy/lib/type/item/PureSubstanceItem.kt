package matrix_energy.lib.type.item

import arc.graphics.Color

open class PureSubstanceItem(
    name: String,
    color: Color = Color.black.cpy(),
    localizedName: String = name
) : MEItem(name, color, localizedName) {

}

