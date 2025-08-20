package matrix_energy.lib.type;

import arc.graphics.Color;
import arc.struct.Seq;
import arc.util.Nullable;
import mindustry.type.Item;

/**
 * @author DG
 */
public class MEItem extends Item {
    public static Seq<MEItem> all = new Seq<>();
    public @Nullable Item imitateItemIcon;

    public MEItem(String name) {
        this(name, Color.black.cpy(), name, null);
    }

    public MEItem(String name, Color color, String localizedName, Item imitateItemIcon) {
        super(name, color);
        this.localizedName = localizedName;
        this.imitateItemIcon = imitateItemIcon;
        all.add(this);

    }

    public MEItem(String name, Color color) {
        this(name, color, name, null);
    }

    public MEItem(String name, Color color, String localizedName) {
        this(name, color, localizedName, null);
    }

    @Override
    public void loadIcon() {
        super.loadIcon();
        if (imitateItemIcon != null) {
            fullIcon = imitateItemIcon.fullIcon != null ? imitateItemIcon.fullIcon : fullIcon;
            uiIcon = imitateItemIcon.uiIcon != null ? imitateItemIcon.uiIcon : uiIcon;
        }
    }
}
