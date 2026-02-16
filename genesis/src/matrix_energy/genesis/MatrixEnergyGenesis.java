package matrix_energy.genesis;

import arc.Core;
import mindustry.content.Items;
import mindustry.mod.Mod;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.world.blocks.environment.OverlayFloor;

/**
 * @author DG
 */
public class MatrixEnergyGenesis extends Mod {
    public MatrixEnergyGenesis() {
        super();


    }

    @Override
    public void init() {
        new OverlayFloor("matrix-energy-overlay1") {
            {
                requirements(Category.defense, ItemStack.with(Items.lead, 100));
                region = Core.atlas.find("shielded-wall");
            }
        };
        new OverlayFloor("matrix-energy-overlay2") {
            {
                requirements(Category.defense, ItemStack.with(Items.lead, 100));
                region = Core.atlas.find("shielded-wall");
            }
        };
        new OverlayFloor("matrix-energy-overlay3") {
            {
                requirements(Category.defense, ItemStack.with(Items.lead, 100));
                region = Core.atlas.find("shielded-wall");

            }
        };
    }

    @Override
    public void loadContent() {

    }
}
