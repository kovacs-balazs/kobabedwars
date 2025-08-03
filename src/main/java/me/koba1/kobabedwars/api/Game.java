package me.koba1.kobabedwars.api;

import com.infernalsuite.asp.api.world.SlimeWorld;
import com.infernalsuite.asp.api.world.SlimeWorldInstance;
import org.bukkit.Location;

public interface Game {

    BaseArena getArena();

    SlimeWorld getSlimeWorld();
    SlimeWorldInstance getSlimeWorldInstance();
    Location getLobbyLocation();
    Location getDeathLocation();
}
