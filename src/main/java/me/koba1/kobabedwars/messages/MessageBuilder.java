package me.koba1.kobabedwars.messages;

import lombok.Getter;
import lombok.Setter;
import me.koba1.kobabedwars.profile.Profile;
import me.koba1.kobabedwars.utils.ColorUtils;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Setter
@Getter
public class MessageBuilder {

    private String message;
    private List<String> messages;

    public MessageBuilder(String message) {
        this.message = message;
    }

    public MessageBuilder(List<String> messages) {
        this.messages = messages;
    }

    public List<String> getMessages() {
        if(this.messages.isEmpty() && this.message != null) {
            return Arrays.asList(this.message);
        }
        return this.messages;
    }

    public void replace(String target, String replacement) {
        if(this.message != null) {
            this.message = this.message.replace(target, replacement);
            return;
        }
        this.messages = replaceList(target, replacement);
    }

    private List<String> replaceList(String target, String replacement) {
        List<String> str = new ArrayList<>();
        for (String s : this.messages) {
            str.add(s.replace(target, replacement));
        }
        return str;
    }

    public MessageBuilder setPrefix() {
        this.replace("%prefix%", Messages.PREFIX.getMessage().getMessage());
        return this;
    }

    public MessageBuilder setPlayer(Player player) {
        this.replace("%player%", player.getName());
        return this;
    }

    public MessageBuilder setSeconds(int seconds) {
        this.replace("%seconds%", seconds +"");
        return this;
    }

    public MessageBuilder setWarp(String warpName) {
        this.replace("%warp_name%", warpName);
        return this;
    }

    public MessageBuilder setAmount(int amount) {
        this.replace("%amount%", amount +"");
        return this;
    }

    public MessageBuilder setError(String error) {
        this.replace("%error%", error);
        return this;
    }

    public MessageBuilder setTarget(Player target) {
        this.replace("%target%", target.getName());
        return this;
    }

//    public void send(Player player) {
//        if(this.getMessage() != null) {
//            player.sendMessage(ComponentUtil.formatToComponent(this.message));
//        } else {
//            player.sendMessage(ComponentUtil.formatToComponent(this.messages));
//        }
//    }

    public void send(Profile prof) {
        Player p = Bukkit.getPlayer(prof.getUniqueId());
        if(p == null) return;
        send(p);
    }

    public void send(Player player) {
        if(this.message != null) {
            player.sendMessage(ColorUtils.formatToString(player, this.message));
        } else {
            for (String s : this.messages) {
                player.sendMessage(ColorUtils.formatToString(player, s));
            }
        }
    }

    public void send(CommandSender sender) {
        if(sender instanceof Player p) {
            this.send(p);
            return;
        }

        if(this.message != null) {
            sender.sendMessage(ColorUtils.formatToString(this.message));
        } else {
            for (String s : this.messages) {
                sender.sendMessage(ColorUtils.formatToString(s));
            }
        }
    }

    public void sendActionBar(Player player) {
        if(this.message != null) {
            player.sendActionBar(ColorUtils.format(player, this.message));
        } else {
            for (String s : this.messages) {
                player.sendActionBar(ColorUtils.format(player, s));
            }
        }
    }

    public Component asComponent(Player player) {
        if(this.message != null) {
            return ColorUtils.format(player, this.message);
        } else {
            Component comp = Component.empty();
            boolean first = true;
            for (String s : this.messages) {
                if(!first) {
                    comp = Component.newline();
                }
                comp = comp.append(ColorUtils.format(player, s));
                first = false;
            }
            return comp;
        }
    }
}
