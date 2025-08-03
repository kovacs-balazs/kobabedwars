package me.koba1.kobabedwars.commands;

import com.infernalsuite.asp.api.world.SlimeWorld;
import com.infernalsuite.asp.api.world.SlimeWorldInstance;
import com.infernalsuite.asp.api.world.properties.SlimeProperties;
import me.koba1.kobabedwars.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TestCreateCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (command.getName().equalsIgnoreCase("kobabedwarscreate")) {

            SlimeWorld sw = Main.getInstance().getWorldManager().getWorldByName("bedwars");
            if (sw != null) {
                SlimeWorldInstance swi = Main.getInstance().getAsp().loadWorld(sw.clone("bw-123"), false);
                System.out.println("teleport");
                ((Player) commandSender).teleport(swi.getBukkitWorld().getSpawnLocation());

                System.out.println(((Player) commandSender).getWorld().getName());
                System.out.println(((Player) commandSender).getWorld().getUID());
            } else {
                System.out.println("fasz");
            }
        }
        return false;
    }
}
