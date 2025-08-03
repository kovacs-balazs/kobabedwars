package me.koba1.kobabedwars.api;

import me.koba1.kobabedwars.arena.ArenaLocation;
import me.koba1.kobabedwars.files.KobaFile;

public interface BaseArena {

    String getKey();
    KobaFile getFile();

    String getWorldName();
    String getDisplayName();

    ArenaLocation getLobbyLocation();
    ArenaLocation getDeathLocation();

    int getMinY();
    int getMaxY();
}
