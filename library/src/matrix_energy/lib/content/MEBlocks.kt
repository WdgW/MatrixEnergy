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
        oreIron = MEOreBlock("ore-iron", MEItems.ironOreItem10).apply {
            requirements(Category.distribution, ItemStack.with(MEItems.Fe, 100))

        }
    }
}
