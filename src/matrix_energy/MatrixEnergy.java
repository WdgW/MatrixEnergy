package matrix_energy;

import matrix_energy.lib.MatrixEnergyLib;
import mindustry.mod.Mod;

/**
 * @author DG
 */
public class MatrixEnergy extends Mod {

    public void MatrixEnergyLib() {

    }

    @Override
    public void loadContent() {
        new MatrixEnergyLib().loadContent();

    }
}
