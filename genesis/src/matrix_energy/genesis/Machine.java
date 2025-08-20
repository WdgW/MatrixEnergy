package matrix_energy.genesis;

import mindustry.game.Team;
import mindustry.gen.Building;
import mindustry.world.Block;
import mindustry.world.Tile;

/**
 * @author DG
 */
public class Machine extends Block {
    boolean hasForce = false;
    boolean isForceOutput = false;
    boolean isForceInput = false;

    public Machine(String name) {
        super(name);
    }

    class MachineBuild extends Building{
        ForceModule forceModule;

        @Override
        public Building init(Tile tile, Team team, boolean shouldAdd, int rotation) {
            if (this.block instanceof Machine  && ((Machine)this.block).hasForce){
                forceModule = new ForceModule();
            }
            return super.init(tile, team, shouldAdd, rotation);
        }
    }
}
