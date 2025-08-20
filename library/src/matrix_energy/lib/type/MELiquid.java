package matrix_energy.lib.type;

import mindustry.type.Liquid;

import static matrix_energy.lib.type.ColorBundle.getColorFormBundle;

/**
 * @author DG
 */
public class MELiquid extends Liquid {

    public MELiquid(String name) {
        super(name);
        this.color = getColorFormBundle(this, "color");
    }
}
