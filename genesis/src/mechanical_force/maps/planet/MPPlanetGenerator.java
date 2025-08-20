package mechanical_force.maps.planet;

import arc.graphics.Color;
import arc.math.geom.Vec3;
import mindustry.maps.generators.PlanetGenerator;
import mindustry.world.Tiles;

/**
 * @author dg
 */
public class MPPlanetGenerator extends PlanetGenerator {
    public MPPlanetGenerator() {
        super();
    }

    @Override
    public float getHeight(Vec3 position) {
        return super.getHeight(position);
    }

    @Override
    public void getColor(Vec3 position, Color out) {
        super.getColor(position, out);
    }

    @Override
    public void getEmissiveColor(Vec3 position, Color out) {
        super.getEmissiveColor(position, out);
    }

    @Override
    public boolean isEmissive() {
        return super.isEmissive();
    }

    @Override
    public boolean skip(Vec3 position) {
        return super.skip(position);
    }

    @Override
    public void postGenerate(Tiles tiles) {
        super.postGenerate(tiles);
    }
}
