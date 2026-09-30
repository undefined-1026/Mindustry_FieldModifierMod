package mod;

import arc.Core;
import arc.Events;
import arc.input.KeyBind;
import arc.input.KeyCode;
import arc.math.geom.Rect;
import arc.util.Log;
import mindustry.Vars;
import mindustry.entities.Units;
import mindustry.game.EventType;
import mindustry.gen.Building;
import mindustry.gen.Groups;

public class Main {
    public static final KeyBind debug = KeyBind.add("reflection_debugging", KeyCode.backtick,"filed-modifier");

    public static Rect range = new Rect(0,0,3,3);
    public static Object hover = null;
    public static float dst = -1;
    public static ObjectInspector inspector = new ObjectInspector();

    public static void init() {

        inspector.setPosition(40, 40); // 左下角

        Events.run(EventType.Trigger.update, () -> {
            if(Core.input.keyRelease(debug) && (Vars.state.rules.infiniteResources || !Core.settings.getBool("filed-modifier-only-sandbox-open"))){
                hover = null;
                dst = -1;
                float x = Core.input.mouseWorldX(), y= Core.input.mouseWorldY();
                range.setCenter(x,y);
                Units.nearby(range, u->{
                    if(u.dst2(x,y) < dst || dst < 0){
                        hover = u;
                    }
                });
                if(hover == null){
                    hover = Vars.world.buildWorld(x,y);
                    if(hover!=null){
                        var build = (Building)hover;
                    }
                }
                if(hover == null){
                    dst = -1;
                    Groups.bullet.intersect(range.x,range.y,range.width,range.height, b->{
                        if(b.dst2(x,y) < dst || dst < 0){
                            hover = b;
                        }
                    });
                }
                if(hover != null){
                    inspector.inspect(hover);
                    if(!inspector.hasParent()){
                        if(Core.scene!=null)Core.scene.add(inspector);
                    }
                }
            }
        });

    }
}
