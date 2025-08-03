package me.koba1.kobabedwars.api;

import me.koba1.kobabedwars.arena.ArenaLocation;

public interface Team {

    String getId();
    String getPrefix();
    String getDisplayName();
    String getColor();

    ArenaLocation getSpawn();
    ArenaLocation getBed();
}
