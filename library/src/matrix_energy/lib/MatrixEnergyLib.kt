package matrix_energy.lib

import arc.Events
import arc.files.Fi
import arc.struct.Seq
import arc.util.Log
import matrix_energy.lib.content.MEBlocks
import matrix_energy.lib.content.MEItems
import matrix_energy.lib.content.MEPlanets
import matrix_energy.lib.content.MEUnitTypes
import matrix_energy.lib.data.ModData
import matrix_energy.lib.ui.dialogs.AchievementDialog
import mindustry.Vars
import mindustry.game.EventType.ClientLoadEvent
import mindustry.gen.Icon
import mindustry.mod.Mod
import mindustry.ui.fragments.MenuFragment

/**
 * @author DG
 */
class MatrixEnergyLib : Mod() {
    companion object {
        const val modName: String = "matrixenergy-lib"
        const val modVersion: String = "0.1.0-dev"
        val MEDirectory: Fi by lazy { Vars.dataDirectory.child("matrixenergy") }
        val achievementDialog: AchievementDialog by lazy { AchievementDialog() }
    }

    init {
        Log.infoTag("ME-Lib", "ME-Lib version " + modVersion + " loaded")


        Events.on(
            ClientLoadEvent::class.java
        ) { e: ClientLoadEvent? ->
            Vars.ui.menufrag.desktopButtons = Seq.with(
                MenuFragment.MenuButton(
                    "@play", Icon.play, {},
                    MenuFragment.MenuButton(
                        "@campaign",
                        Icon.play
                    ) { checkPlay { Vars.ui.planet.show() } },
                    MenuFragment.MenuButton(
                        "@joingame",
                        Icon.add,
                        { checkPlay({ Vars.ui.join.show() }) }),
                    MenuFragment.MenuButton(
                        "@customgame",
                        Icon.terrain,
                        { checkPlay({ Vars.ui.custom.show() }) }),
                    MenuFragment.MenuButton(
                        "@loadgame",
                        Icon.download,
                        { checkPlay({ Vars.ui.load.show() }) })
                ),
                MenuFragment.MenuButton(
                    "@database.button", Icon.menu, {},
                    MenuFragment.MenuButton("@schematics", Icon.paste, { Vars.ui.schematics.show() }),
                    MenuFragment.MenuButton("@database", Icon.book, { Vars.ui.database.show() }),
                    MenuFragment.MenuButton("@about.button", Icon.info, { Vars.ui.about.show() })
                ),
                MenuFragment.MenuButton("@achievements", Icon.lock, { achievementDialog.show() }),
                MenuFragment.MenuButton(
                    "@editor",
                    Icon.terrain,
                    { checkPlay({ Vars.ui.maps.show() }) }),
                if (Vars.steam) MenuFragment.MenuButton(
                    "@workshop",
                    Icon.steam,
                    { Vars.platform.openWorkshop() }) else null,
                MenuFragment.MenuButton("@mods", Icon.book, { Vars.ui.mods.show() }),
                MenuFragment.MenuButton("@settings", Icon.settings, { Vars.ui.settings.show() })
            )
        }
    }

    fun checkPlay(run: Runnable) {
        if (!Vars.mods.hasContentErrors()) {
            run.run()
        } else {
            Vars.ui.showInfo("@mod.noerrorplay")
        }
    }

    override fun loadContent() {
        MEItems.load()
        MEBlocks.load()
        MEUnitTypes.load()
        MEPlanets.load()
        ModData.load()
    }
}
