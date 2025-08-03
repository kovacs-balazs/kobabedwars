package me.koba1.kobabedwars.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.Arrays;

public class LocationUtils {

    public static Location parse(String string) { // world, x, y, z, yaw, pitch
        if(string == null) return null;
        String[] args = string.split(",");
        if (args.length == 0) return null;
        World world = Bukkit.getWorld(args[0]);

        args = Arrays.copyOfRange(args, 1, args.length);
        if (args.length == 3) {
            return new Location(
                    world,
                    Double.parseDouble(args[0]),
                    Double.parseDouble(args[1]),
                    Double.parseDouble(args[2])
            );
        } else if (args.length == 5) {
            return new Location(
                    world,
                    Double.parseDouble(args[0]),
                    Double.parseDouble(args[1]),
                    Double.parseDouble(args[2]),
                    Float.parseFloat(args[3]),
                    Float.parseFloat(args[4])
            );
        }
        return null;
    }

    public static Location parseSlimeWorld(World world, String string) { // x, y, z, yaw, pitch
        if(string == null) return null;
        String[] args = string.split(",");
        if (args.length == 3) {
            return new Location(
                    world,
                    Double.parseDouble(args[0]),
                    Double.parseDouble(args[1]),
                    Double.parseDouble(args[2])
            );
        } else if (args.length == 5) {
            return new Location(
                    world,
                    Double.parseDouble(args[0]),
                    Double.parseDouble(args[1]),
                    Double.parseDouble(args[2]),
                    Float.parseFloat(args[3]),
                    Float.parseFloat(args[4])
            );
        }
        return null;
    }

    public static String toString(Location location) {
        return new StringBuilder(location.getWorld().getName())
                .append(",")
                .append(location.getX())
                .append(",")
                .append(location.getY())
                .append(",")
                .append(location.getZ())
                .append(",")
                .append(location.getPitch())
                .append(",")
                .append(location.getYaw()).toString();
    }
}
