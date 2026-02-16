package matrix_energy.lib.type.item

import arc.graphics.Color
import matrix_energy.lib.MatrixEnergyInterfaces.PureSubstance

open class PureSubstanceItem(
    name: String,
    color: Color = Color.black.cpy(),
    localizedName: String = name
) : MEItem(name, color, localizedName), PureSubstance {

}

