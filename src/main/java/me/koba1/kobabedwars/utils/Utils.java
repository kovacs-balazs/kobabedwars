package me.koba1.kobabedwars.utils;

import me.koba1.kobabedwars.game.GameImpl;

import java.util.Random;

public class Utils {

    public static String generateWorldId(GameImpl game) {
        long currentTimestamp = System.currentTimeMillis();
        Random random = new Random();
        int randomNumber = random.nextInt(9999);
        currentTimestamp += randomNumber;
        return String.format("%s_%d", game.getArena().getKey(), currentTimestamp);
    }
}
