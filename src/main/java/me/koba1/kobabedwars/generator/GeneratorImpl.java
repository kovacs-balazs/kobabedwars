package me.koba1.kobabedwars.generator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.koba1.kobabedwars.api.Generator;
import me.koba1.kobabedwars.arena.ArenaLocation;
import org.bukkit.inventory.ItemStack;

@Getter
@AllArgsConstructor
public class GeneratorImpl implements Generator {

    private String name;
    private ArenaLocation arenaLocation;

    private GeneratorImpl next;
    private ItemStack item;
    private int tier;
    private int maxStack;
    private int start;
    private int delay;
    private int amount;
}
