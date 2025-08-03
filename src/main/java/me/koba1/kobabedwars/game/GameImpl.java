package me.koba1.kobabedwars.game;

import com.infernalsuite.asp.api.world.SlimeWorld;
import com.infernalsuite.asp.api.world.SlimeWorldInstance;
import lombok.Getter;
import me.koba1.kobabedwars.Main;
import me.koba1.kobabedwars.api.Game;
import me.koba1.kobabedwars.api.GameGenerator;
import me.koba1.kobabedwars.arena.BaseArenaImpl;
import me.koba1.kobabedwars.generator.GameGeneratorImpl;
import me.koba1.kobabedwars.generator.GeneratorImpl;
import me.koba1.kobabedwars.profile.Profile;
import me.koba1.kobabedwars.teams.GameTeamImpl;
import me.koba1.kobabedwars.teams.TeamImpl;
import me.koba1.kobabedwars.utils.Utils;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.*;

@Getter
public class GameImpl implements Game {

    private final BaseArenaImpl arena;

    private final SlimeWorld sw;
    private final SlimeWorldInstance swi;

    private final Location lobbyLocation;
    private final Location deathLocation;

    private final Map<String, GameTeamImpl> teams;

    private final List<GameGeneratorImpl> generators;

    public GameImpl(BaseArenaImpl arena) {
        this.arena = arena;

        generators = new ArrayList<>();
        teams = new HashMap<>();

        sw = Main.getInstance().getWorldManager().getWorldByName(arena.getWorldName()).clone(Utils.generateWorldId(this));
        swi = Main.getInstance().getAsp().loadWorld(sw, false);

        lobbyLocation = arena.getLobbyLocation().getLocation(this);
        deathLocation = arena.getDeathLocation().getLocation(this);

        for (Map.Entry<String, TeamImpl> entry : arena.getTeams().entrySet()) {
            teams.put(entry.getKey(), new GameTeamImpl(
                    entry.getValue(),
                    entry.getValue().getSpawn().getLocation(this),
                    entry.getValue().getBed().getLocation(this)
            ));
        }

        for (GeneratorImpl generator : arena.getGenerators()) {
            generators.add(new GameGeneratorImpl(generator, generator.getArenaLocation().getLocation(this)));
        }
    }

    public void join(Player player, GameTeamImpl gameTeam) {
        player.teleport(lobbyLocation);
        gameTeam.getPlayers().add(player);

        Profile prof = Profile.getProfile(player);
        prof.setGame(this);
    }

    public void join(Player player) {
        this.join(player, getFreeTeam());
    }

    public void rejoin(Player player) {

    }

    private GameTeamImpl getFreeTeam() {
        if(arena.getMaxPlayersInTeam() == 1) {
            return this.teams.values().stream().filter(t -> t.getPlayers().isEmpty()).findFirst().orElse(null);
        } else {
            return this.teams.values().stream().min(Comparator.comparingInt(t -> t.getPlayers().size())).orElse(null);
        }
    }

    @Override
    public SlimeWorld getSlimeWorld() {
        return sw;
    }

    @Override
    public SlimeWorldInstance getSlimeWorldInstance() {
        return swi;
    }
}
