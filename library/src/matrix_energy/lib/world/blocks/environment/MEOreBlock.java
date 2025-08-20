package matrix_energy.lib.world.blocks.environment;

import mindustry.type.Item;
import mindustry.world.blocks.environment.OreBlock;

/**
 * @author dg
 */
@SuppressWarnings("AlibabaClassNamingShouldBeCamel")
public class MEOreBlock extends OreBlock {

    public MEOreBlock(String name, Item ore) {
        super(name, ore);
    }

    public MEOreBlock(Item ore) {
        super(ore);
    }

    public MEOreBlock(String name) {
        super(name);
    }
}
