package me.koba1.kobabedwars.profile;

import lombok.Getter;
import lombok.Setter;
import me.koba1.kobabedwars.Main;
import me.koba1.kobabedwars.game.GameImpl;
import org.bukkit.entity.Player;

import java.util.UUID;

@Getter
public class Profile {

    private final UUID uniqueId;

    @Setter private GameImpl game;

    public Profile(UUID uniqueId) {
        this.uniqueId = uniqueId;

        this.game = null;

        Main.getInstance().getProfiles().put(uniqueId, this);
    }

    public static Profile getProfile(UUID uniqueId) {
        if(Main.getInstance().getProfiles().containsKey(uniqueId))
            return Main.getInstance().getProfiles().get(uniqueId);
        return new Profile(uniqueId);
    }

    public static Profile getProfile(Player player) {
        return getProfile(player.getUniqueId());
    }
}
