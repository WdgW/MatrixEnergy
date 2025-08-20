package matrix_energy.lib.content;

import matrix_energy.lib.world.blocks.environment.MEOreBlock;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.world.Block;

/**
 * @author dg
 */
public class MEBlocks {
    public static Block
        oreIron;
    public static void load(){
        oreIron = new MEOreBlock("ore-iron",MEItems.ironOreItems){{
            requirements(Category.distribution, ItemStack.with(MEItems.iron, 100));
        }};

    }
}
