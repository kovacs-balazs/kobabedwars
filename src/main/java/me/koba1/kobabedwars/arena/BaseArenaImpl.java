package me.koba1.kobabedwars.arena;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import me.koba1.kobabedwars.api.BaseArena;
import me.koba1.kobabedwars.files.KobaFile;
import me.koba1.kobabedwars.generator.GeneratorImpl;
import me.koba1.kobabedwars.teams.TeamImpl;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Setter(AccessLevel.PACKAGE)
@Getter
public class BaseArenaImpl implements BaseArena {

    private String key;
    private KobaFile file;

    private String displayName;
    private String worldName;

    private ArenaLocation lobbyLocation;
    private ArenaLocation deathLocation;

    private Map<String, TeamImpl> teams;

//    private Map<Team, ArenaLocation> teamLocations;
//    private Map<Team, ArenaLocation> bedLocations; // if locations mapsize < teamCount throw error

    private List<GeneratorImpl> generators;

    private int teamCount;
    private int teamSize;

    private int maxPlayersInTeam;

    private int minY;
    private int maxY;

    public BaseArenaImpl(KobaFile file) {
        this.file = file;

        this.teams = new HashMap<>();

        reload();
    }

    public void reload() {
        FileConfiguration c =  file.getConfig();
        this.key = c.getString("name");

        this.displayName = c.getString("display_name");
        this.worldName = c.getString("world");

        this.minY = c.getInt("build_limit.min");
        this.maxY = c.getInt("build_limit.max");

        this.teams.clear();

    }
}
