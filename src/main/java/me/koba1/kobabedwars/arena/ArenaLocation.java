package me.koba1.kobabedwars.arena;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.koba1.kobabedwars.api.Game;
import me.koba1.kobabedwars.game.GameImpl;
import org.bukkit.Location;

@Getter
@AllArgsConstructor
public class ArenaLocation {

    private double x;
    private double y;
    private double z;
    private float yaw;
    private float pitch;

    public ArenaLocation(double x, double y, double z) {
        this(x, y, z, 0.0F, 0.0F);
    }

    public Location getLocation(Game game) {
        return new Location(game.getSlimeWorldInstance().getBukkitWorld(), x, y, z, yaw, pitch);
    }
}
