package me.koba1.kobabedwars.api;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.List;

public interface GameTeam {

    Team getTeam();
    Location getSpawn();
    Location getBed();
    List<Player> getPlayers();
}
