package matrix_energy.type;

import arc.Core;
import arc.graphics.Color;
import mindustry.ctype.UnlockableContent;

public class ColorBundle {

    public static Color getColorFormBundle(
        UnlockableContent unlockableContent,
        String field
    ) {
        return Color.valueOf(
            Core.bundle.get(
                unlockableContent.getContentType() +
                "." +
                unlockableContent.name +
                "." +
                field,
                "ffffffff"
            )
        );
    }
}
