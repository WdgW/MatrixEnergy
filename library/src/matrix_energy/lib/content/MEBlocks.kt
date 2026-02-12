package matrix_energy.lib.content

import matrix_energy.lib.world.blocks.environment.MEOreBlock
import mindustry.type.Category
import mindustry.type.ItemStack
import mindustry.world.Block

/**
 * @author dg
 */
object MEBlocks {
    var oreIron: Block? = null

    @JvmStatic
    fun load() {
        oreIron = object : MEOreBlock("ore-iron", MEItems.ironOreItem10) {
            init {
                requirements(Category.distribution, ItemStack.with(MEItems.Fe, 100))
            }
        }
    }
}
