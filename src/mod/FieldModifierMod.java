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
import mindustry.mod.Mod;


public class FieldModifierMod extends Mod{

    public FieldModifierMod(){
        Log.info("mod创建.");
    }

    @Override
    public void init() {
        Log.info("mod初始化.");
        Events.on(EventType.ClientLoadEvent.class,e->{
            Core.app.post(()->{
                Vars.ui.settings.addCategory("Filed Modifier",t->{
                    t.checkPref("filed-modifier-only-sandbox-open",true);
                });
            });
        });

        Main.init();
    }
}
