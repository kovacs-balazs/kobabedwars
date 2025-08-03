package me.koba1.kobabedwars.generator;

import lombok.Getter;
import me.koba1.kobabedwars.Main;
import me.koba1.kobabedwars.api.GameGenerator;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

@Getter
public class GameGeneratorImpl implements GameGenerator {

    // holo

    private GeneratorImpl generator;
    private Location location;

    // ingame times
    private long nextSpawn;
    private long upgrade;

    private BukkitTask task;

    public GameGeneratorImpl(GeneratorImpl generator, Location location) {
        this.generator = generator;
        this.location = location;
        this.upgrade = 0;
        if(generator.getStart() != 0) {
            this.upgrade = System.currentTimeMillis() + generator.getStart() * 1000L;
        }

        this.nextSpawn = System.currentTimeMillis() + generator.getDelay() * 1000L;

        checker();
    }

    public void upgrade() {
        if(generator.getNext() != null) {
            this.generator = generator.getNext();

            this.upgrade = 0;
            if(this.generator.getStart() != 0) {
                this.upgrade = System.currentTimeMillis() + generator.getStart() * 1000L;
            }
        }
    }

    public void checker() {
        task = new BukkitRunnable() {
            @Override
            public void run() {
                long unixTime = System.currentTimeMillis();
                if(unixTime >= nextSpawn) {
                    nextSpawn = unixTime + generator.getDelay() * 1000L;

                    ItemStack is = generator.getItem().clone();
                    is.setAmount(generator.getAmount());
                    location.getWorld().dropItem(location, is);
                }

                if(unixTime >= upgrade) {
                    upgrade();
                }
            }
        }.runTaskTimer(Main.getInstance(), 0L, 1L);
    }
}
