package me.koba1.kobabedwars.arena;

import me.koba1.kobabedwars.Main;
import me.koba1.kobabedwars.files.KobaFile;

import java.util.HashMap;
import java.util.Map;

public class ArenaManager {

    private final Map<String, BaseArenaImpl> arenas;

    public ArenaManager() {
        arenas = new HashMap<>();

        load();
    }

    public void load() {
        for (KobaFile arenaFile : Main.getInstance().getFileManager().getArenas()) {
            BaseArenaImpl arena = new BaseArenaImpl(arenaFile);
            this.arenas.put(arena.getKey(), arena);
        }
    }

    public void reload() {
        for (Map.Entry<String, BaseArenaImpl> arena : new HashMap<>(this.arenas).entrySet()) {
            if(!arena.getValue().getFile().exists()) {
                this.arenas.remove(arena.getKey());
            }

            arena.getValue().reload();
        }
    }
}
