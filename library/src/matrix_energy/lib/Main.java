package matrix_energy.lib;

import matrix_energy.lib.content.MEBlocks;
import matrix_energy.lib.content.MEItems;
import matrix_energy.lib.content.MEPlanets;
import mindustry.mod.Mod;

/**
 * @author DG
 */
public class Main extends Mod {

    public Main() {}

    @Override
    public void loadContent() {
        MEItems.load();
        MEBlocks.load();
        MEPlanets.load();


    }
}
