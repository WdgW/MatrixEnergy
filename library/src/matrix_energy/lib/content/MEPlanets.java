package matrix_energy.lib.content;

import arc.Events;
import arc.graphics.Color;
import arc.graphics.g3d.VertexBatch3D;
import arc.util.Log;
import mindustry.Vars;
import mindustry.content.Planets;
import mindustry.game.EventType.ClientLoadEvent;
import mindustry.graphics.g3d.HexMesh;
import mindustry.graphics.g3d.HexSkyMesh;
import mindustry.graphics.g3d.MultiMesh;
import mindustry.graphics.g3d.PlanetRenderer;
import mindustry.maps.planet.SerpuloPlanetGenerator;
import mindustry.type.Planet;

import java.lang.reflect.Field;

/**
 * @author dg
 */
public class MEPlanets {
    public static Planet
            planet;
    public static Field batchField;


    static {
        try {
            batchField = PlanetRenderer.class.getDeclaredField("batch");
            // 获取PlanetRenderer实例中的batch字段
            batchField.setAccessible(true);
        } catch (NoSuchFieldException e) {
            Log.err(e);
        }
    }

    public MEPlanets() throws NoSuchFieldException {}

    public static void load() {
        Events.on(
                ClientLoadEvent.class, e -> {
                    try {
                        // 修改PlanetRenderer的batch字段 防止顶点数量超出限制
                        Log.infoTag("warning", "正在修改PlanetRenderer的batch字段 防止顶点数量超出限制 (maxVertices = 4000000)");
                        // 获取当前的batch实例
                        VertexBatch3D currentBatch = (VertexBatch3D) MEPlanets.batchField.get(Vars.renderer.planets); // rendererInstance是PlanetRenderer的实例
                        // 创建一个新的VertexBatch3D实例，容量更大
                        VertexBatch3D newBatch = new VertexBatch3D(4000000, false, true, 0);
                        // 替换旧的batch实例
                        MEPlanets.batchField.set(Vars.renderer.planets, newBatch);
                        // 释放旧的batch资源
                        currentBatch.dispose();
                    } catch (IllegalAccessException ex) {
                        Log.err(ex);
                    }
                }
                 );


        Log.info("create planet");
        planet = new Planet("planet", Planets.sun, 5f, 4) {{
            generator = new SerpuloPlanetGenerator();
            meshLoader = () -> new HexMesh(this, 8);
            cloudMeshLoader = () -> new MultiMesh(
                    new HexSkyMesh(planet, 4, 0.6f, 0.2f, 6, Color.valueOf("ffcca5").a(0.8f), 8, 0.64f, 0.8f, 0.3f)
            );

            alwaysUnlocked = true;
        }};
    }
}
