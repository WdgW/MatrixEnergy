package matrix_energy.lib;

import arc.Events;
import arc.files.Fi;
import arc.struct.Seq;
import arc.util.Log;
import matrix_energy.lib.content.MEBlocks;
import matrix_energy.lib.content.MEItems;
import matrix_energy.lib.content.MEPlanets;
import matrix_energy.lib.content.MEUnitTypes;
import matrix_energy.lib.data.ModData;
import matrix_energy.lib.ui.dialogs.AchievementDialog;
import mindustry.Vars;
import mindustry.game.EventType;
import mindustry.gen.Icon;
import mindustry.mod.Mod;
import mindustry.ui.fragments.MenuFragment.MenuButton;

import static mindustry.Vars.*;

/**
 * @author DG
 */
public class MatrixEnergyLib extends Mod {
    public static final String modName = "matrixenergy-lib";
    public static final String modVersion = "0.1.0-dev";
    public static Fi MEDirectory;
    public static AchievementDialog achievementDialog;


    public MatrixEnergyLib() {
        Log.infoTag("ME-Lib", "ME-Lib version " + modVersion + " loaded");
        MEDirectory = Vars.dataDirectory.child("matrixenergy");

        Events.on(
                EventType.ClientLoadEvent.class, e -> {
                    achievementDialog = new AchievementDialog();
                    Vars.ui.menufrag.desktopButtons = Seq.with(
                            new MenuButton(
                                    "@play", Icon.play, () -> {},
                                    new MenuButton("@campaign", Icon.play, () -> checkPlay(ui.planet::show)),
                                    new MenuButton("@joingame", Icon.add, () -> checkPlay(ui.join::show)),
                                    new MenuButton("@customgame", Icon.terrain, () -> checkPlay(ui.custom::show)),
                                    new MenuButton("@loadgame", Icon.download, () -> checkPlay(ui.load::show))
                            ),
                            new MenuButton(
                                    "@database.button", Icon.menu, () -> {},
                                    new MenuButton("@schematics", Icon.paste, ui.schematics::show),
                                    new MenuButton("@database", Icon.book, ui.database::show),
                                    new MenuButton("@about.button", Icon.info, ui.about::show)
                            ),
                            new MenuButton("@achievements", Icon.lock, achievementDialog::show),
                            new MenuButton("@editor", Icon.terrain, () -> checkPlay(ui.maps::show)), steam ? new MenuButton("@workshop", Icon.steam, platform::openWorkshop) : null,
                            new MenuButton("@mods", Icon.book, ui.mods::show),
                            new MenuButton("@settings", Icon.settings, ui.settings::show)
                                                              );
                }
                 );

    }

    public void checkPlay(Runnable run) {
        if (!mods.hasContentErrors()) {
            run.run();
        }
        else {
            ui.showInfo("@mod.noerrorplay");
        }
    }

    @Override
    public void loadContent() {
        MEItems.load();
        MEBlocks.load();
        MEUnitTypes.load();
        MEPlanets.load();
        ModData.load();


    }
}
