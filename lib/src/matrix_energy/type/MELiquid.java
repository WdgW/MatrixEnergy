package matrix_energy.type;

import static matrix_energy.type.ColorBundle.getColorFormBundle;

import mindustry.type.Liquid;

/**
 * @author DG
 */
public class MELiquid extends Liquid {

    public MELiquid(String name) {
        super(name);
        this.color = getColorFormBundle(this, "color");
    }
}
