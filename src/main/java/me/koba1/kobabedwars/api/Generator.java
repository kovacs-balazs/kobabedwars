package me.koba1.kobabedwars.api;

import me.koba1.kobabedwars.arena.ArenaLocation;
import org.bukkit.inventory.ItemStack;

public interface Generator {

    String getName();

    ArenaLocation getArenaLocation();
    ItemStack getItem();

    int getStart();
    int getDelay();
    int getMaxStack();
}
