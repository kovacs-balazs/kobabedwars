package me.koba1.kobabedwars;

import com.infernalsuite.asp.api.AdvancedSlimePaperAPI;
import com.infernalsuite.asp.api.exceptions.CorruptedWorldException;
import com.infernalsuite.asp.api.exceptions.NewerFormatException;
import com.infernalsuite.asp.api.exceptions.UnknownWorldException;
import com.infernalsuite.asp.api.loaders.SlimeLoader;
import com.infernalsuite.asp.api.world.SlimeWorld;
import com.infernalsuite.asp.api.world.SlimeWorldInstance;
import com.infernalsuite.asp.api.world.properties.SlimePropertyMap;
import com.infernalsuite.asp.loaders.file.FileLoader;
import lombok.Getter;
import me.koba1.kobabedwars.arena.ArenaManager;
import me.koba1.kobabedwars.commands.TestCreateCommand;
import me.koba1.kobabedwars.files.FileManager;
import me.koba1.kobabedwars.profile.Profile;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/*
Arena class = World String Name
RunningArena class = SlimeWorld
 */

@Getter
public class Main extends JavaPlugin {
    @Getter private static Main instance;
    private SlimeLoader loader;
    private AdvancedSlimePaperAPI asp = AdvancedSlimePaperAPI.instance();

    private Map<UUID, Profile> profiles;

    private FileManager fileManager;
    private ArenaManager arenaManager;
    private WorldManager worldManager;

    @Override
    public void onLoad() {
        loader = new FileLoader(new File("slime_worlds"));
    }

    @Override
    public void onEnable() {
        instance = this;

        profiles = new HashMap<>();

        fileManager = new FileManager();
//        try {
//            SlimeWorld world = asp.readWorld(loader, "faszfasz", false, new SlimePropertyMap());
//            SlimeWorldInstance instance  = asp.loadWorld(world, false);
//        } catch (UnknownWorldException | NewerFormatException | CorruptedWorldException | IOException e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
//

        worldManager = new WorldManager();

        arenaManager = new ArenaManager();

        getCommand("kobabedwarscreate").setExecutor(new TestCreateCommand());
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
