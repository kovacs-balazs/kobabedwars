package me.koba1.kobabedwars.teams;

import lombok.Getter;
import me.koba1.kobabedwars.api.GameTeam;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

@Getter
public class GameTeamImpl implements GameTeam {

    private final TeamImpl team;
    private final Location spawn;
    private final Location bed;
    private final List<Player> players;

    public GameTeamImpl(TeamImpl team, Location spawn, Location bed) {
        this.team = team;
        this.spawn = spawn;
        this.bed = bed;
        this.players = new ArrayList<>();
    }
}
