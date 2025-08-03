package me.koba1.kobabedwars.teams;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.koba1.kobabedwars.api.Team;
import me.koba1.kobabedwars.arena.ArenaLocation;

@Getter
@AllArgsConstructor
public class TeamImpl implements Team {

    private String id;
    private String prefix;
    private String displayName;
    private String color;

    private ArenaLocation spawn;
    private ArenaLocation bed;
}
