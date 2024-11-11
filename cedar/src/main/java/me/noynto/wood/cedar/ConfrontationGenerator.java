package me.noynto.wood.cedar;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConfrontationGenerator {

    public static Confrontation teams(Integer quantity) {
        return new Confrontation(
                Stream.iterate(1, i -> i + 1)
                        .limit(quantity)
                        .map(number -> TeamGenerator.valid(number, number))
                        .collect(Collectors.toSet())
        );
    }

    public static Confrontation sameScore(Integer amount) {
        return new Confrontation(
                Stream.iterate(1, i -> i + 1)
                        .limit(2)
                        .map(number -> TeamGenerator.valid(number, amount))
                        .collect(Collectors.toSet())
        );
    }

    public static Confrontation valid() {
        return new Confrontation(
                Stream.iterate(1, i -> i + 1)
                        .limit(2)
                        .map(number -> TeamGenerator.valid(number, number))
                        .collect(Collectors.toSet())
        );
    }

    public static Confrontation samePlayerInBothTeams(String playerReference) {
        String randomPlayerReferenceOfTeam1 = "VALID-1";
        String randomPlayerReferenceOfTeam2 = "VALID-2";
        Confrontation.Team.Player attacker1 = new Confrontation.Team.Player(playerReference, Confrontation.Team.Player.Position.ATTACKER);
        Confrontation.Team.Player defender1 = new Confrontation.Team.Player(randomPlayerReferenceOfTeam1, Confrontation.Team.Player.Position.DEFENDER);
        Confrontation.Team team1 = new Confrontation.Team(5, Set.of(attacker1, defender1));
        Confrontation.Team.Player attacker2 = new Confrontation.Team.Player(randomPlayerReferenceOfTeam2, Confrontation.Team.Player.Position.ATTACKER);
        Confrontation.Team.Player defender2 = new Confrontation.Team.Player(playerReference, Confrontation.Team.Player.Position.DEFENDER);
        Confrontation.Team team2 = new Confrontation.Team(7, Set.of(attacker2, defender2));
        return new Confrontation(Set.of(team1, team2));
    }

}
