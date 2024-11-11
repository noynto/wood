package me.noynto.wood.cedar;

import java.util.Set;
import java.util.stream.Stream;

public class TeamGenerator {

    public static Confrontation.Team valid(Integer playerReferenceNumber, Integer score) {
        Confrontation.Team.Player attacker = new Confrontation.Team.Player("A-" + playerReferenceNumber, Confrontation.Team.Player.Position.ATTACKER);
        Confrontation.Team.Player defender = new Confrontation.Team.Player("D-" + playerReferenceNumber, Confrontation.Team.Player.Position.DEFENDER);
        return new Confrontation.Team(score, Set.of(attacker, defender));
    }

    public static Stream<Confrontation.Team> quantity(int numberOfTeams) {
        return Stream.iterate(1, i -> i + 1)
                .limit(numberOfTeams)
                .map(integer -> {
                    Confrontation.Team.Player attacker = new Confrontation.Team.Player("A-" + integer, Confrontation.Team.Player.Position.ATTACKER);
                    Confrontation.Team.Player defender = new Confrontation.Team.Player("D-" + integer, Confrontation.Team.Player.Position.DEFENDER);
                    return new Confrontation.Team(integer, Set.of(attacker, defender));
                });
    }

    public static Stream<Confrontation.Team> equalsScore(int score) {
        return Stream.iterate(1, i -> i + 1)
                .limit(2)
                .map(integer -> {
                    Confrontation.Team.Player attacker = new Confrontation.Team.Player("A-" + integer, Confrontation.Team.Player.Position.ATTACKER);
                    Confrontation.Team.Player defender = new Confrontation.Team.Player("D-" + integer, Confrontation.Team.Player.Position.DEFENDER);
                    return new Confrontation.Team(score, Set.of(attacker, defender));
                });
    }

    public static Stream<Confrontation.Team> equalsAttackerAndDefender(String prefix) {
        return Stream.iterate(1, i -> i + 1)
                .limit(2)
                .map(integer -> {
                    Confrontation.Team.Player attacker = new Confrontation.Team.Player(prefix + "-" + integer, Confrontation.Team.Player.Position.ATTACKER);
                    Confrontation.Team.Player defender = new Confrontation.Team.Player(prefix + "-" + integer, Confrontation.Team.Player.Position.DEFENDER);
                    return new Confrontation.Team(integer, Set.of(attacker, defender));
                });
    }

    public static Confrontation.Team positons(Confrontation.Team.Player.Position position1, Confrontation.Team.Player.Position position2) {
        Confrontation.Team.Player p1 = new Confrontation.Team.Player("P-1", position1);
        Confrontation.Team.Player p2 = new Confrontation.Team.Player("P-2", position2);
        return new Confrontation.Team(6, Set.of(p1, p2));
    }

}
