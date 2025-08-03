package me.koba1.kobabedwars;

import com.infernalsuite.asp.api.world.SlimeWorld;
import com.infernalsuite.asp.api.world.properties.SlimePropertyMap;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class WorldManager {

    private final Map<String, SlimeWorld> worlds;

    public WorldManager() {
        worlds = new HashMap<>();

//        File f = new File(Main.getInstance().getDataFolder(), "template_worlds");
//        if (f.listFiles() != null) {
//            for (File file : f.listFiles()) {
//                try {
//                    SlimeWorld sw = Main.getInstance().getAsp().readVanillaWorld(f, file.getName(), Main.getInstance().getLoader());
//                    Main.getInstance().getAsp().saveWorld(sw);
//                    this.worlds.put(sw.getName(), sw);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }

        // todo load templateworlds
        try {
            worlds.put("bedwars",
                    Main.getInstance().getAsp()
                            .readWorld(Main.getInstance().getLoader(), "faszfasz", false, new SlimePropertyMap()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public SlimeWorld getWorldByName(String name) {
        return worlds.get(name);
    }
}
