package matrix_energy.lib.type.item

import arc.graphics.Color
import matrix_energy.lib.MatrixEnergyInterfaces.PureSubstance

open class PureSubstanceItem(
    name: String,
    color: Color = Color.black.cpy(),
) : MEItem(name, color), PureSubstance {
    constructor(name: String, localizedName: String,color: Color = Color.black.cpy()): this(name, color){
    this.localizedName = localizedName
    }
}

