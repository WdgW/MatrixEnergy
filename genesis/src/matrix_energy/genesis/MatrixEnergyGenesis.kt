package matrix_energy.genesis

import arc.Core
import matrix_energy.genesis.content.Blocks
import mindustry.content.Items
import mindustry.mod.Mod
import mindustry.type.Category
import mindustry.type.ItemStack
import mindustry.world.blocks.environment.OverlayFloor

/**
 * @author DG
 */
class MatrixEnergyGenesis : Mod() {
    override fun init() {
        object : OverlayFloor("matrix-energy-overlay1") {
            init {
                requirements(Category.defense, ItemStack.with(Items.lead, 100))
                region = Core.atlas.find("shielded-wall")
            }
        }
        object : OverlayFloor("matrix-energy-overlay2") {
            init {
                requirements(Category.defense, ItemStack.with(Items.lead, 100))
                region = Core.atlas.find("shielded-wall")
            }
        }
        object : OverlayFloor("matrix-energy-overlay3") {
            init {
                requirements(Category.defense, ItemStack.with(Items.lead, 100))
                region = Core.atlas.find("shielded-wall")
            }
        }
    }

    override fun loadContent() {
        Blocks.load()
    }
}
