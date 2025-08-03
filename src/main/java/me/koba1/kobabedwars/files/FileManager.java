package me.koba1.kobabedwars.files;

import lombok.Getter;
import me.koba1.kobabedwars.Main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Getter
public class FileManager {

    private final KobaFile config;
    private final KobaFile messages;

    private final List<KobaFile> arenas;

    public FileManager() {
        this.arenas = new ArrayList<>();

        this.config = new KobaFile("config.yml");
        this.messages = new KobaFile("messages.yml");

        new KobaFile("arenas", "_example.yml");

        loadArenas();
    }

    public void loadArenas() {
        File f = new File(Main.getInstance().getDataFolder(), "arenas");
        if(f.listFiles() != null) {
            for (File file : f.listFiles()) {
                if(file.getName().startsWith("_")) continue;
                if(!file.getName().endsWith(".yml")) continue;

                this.arenas.add(new KobaFile(file));
            }
        }
    }

    public void reloadAll() {
        this.config.reload();
        this.messages.reload();
    }
}
