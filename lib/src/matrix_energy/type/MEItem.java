package matrix_energy.type;

import mindustry.type.Item;

/**
 * @author DG
 */
public class MEItem extends Item {

    public MEItem(final String name) {
        super(name);
        this.color = ColorBundle.getColorFormBundle(this, "color");
    }
}
