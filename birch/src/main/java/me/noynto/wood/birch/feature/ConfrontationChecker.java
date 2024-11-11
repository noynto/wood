package me.noynto.wood.birch.feature;

import me.noynto.wood.birch.api.CheckConfrontation;
import me.noynto.wood.cedar.Confrontation;
import me.noynto.wood.cedar.ConfrontationMustConsistOfTwoTeams;
import me.noynto.wood.cedar.TeamsCanNotHaveEqualScores;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class ConfrontationChecker implements CheckConfrontation {
    public static final String CONFRONTATION_IS_REQUIRED_MESSAGE = "The confrontation is required.";
    public static final int VALID_NUMBER_OF_TEAMS_IN_A_CONFRONTATION = 2;

    @Override
    public void numberOfTeamsSupplied(Confrontation confrontation) throws ConfrontationMustConsistOfTwoTeams {
        Objects.requireNonNull(confrontation, CONFRONTATION_IS_REQUIRED_MESSAGE);
        if (confrontation.teams().size() != VALID_NUMBER_OF_TEAMS_IN_A_CONFRONTATION) {
            throw new ConfrontationMustConsistOfTwoTeams(confrontation.teams().size());
        }
    }

    @Override
    public void scoreOfTeamsSupplied(Confrontation confrontation) throws TeamsCanNotHaveEqualScores {
        Objects.requireNonNull(confrontation, CONFRONTATION_IS_REQUIRED_MESSAGE);
        Map<Integer, List<Confrontation.Team>> teamsByScore = confrontation.teams()
                .stream()
                .collect(Collectors.groupingBy(Confrontation.Team::score));
        for (Map.Entry<Integer, List<Confrontation.Team>> entry : teamsByScore.entrySet()) {
            if (entry.getValue().size() > 1) {
                throw new TeamsCanNotHaveEqualScores(entry.getKey());
            }
        }
    }

    /*@Override
    public void uniquenessOfPlayers(Confrontation confrontation) throws SamePlayerCanNotBeInBothTeamDuringConfrontation {
        Objects.requireNonNull(confrontation, CONFRONTATION_IS_REQUIRED_MESSAGE);
        Map<Integer, List<String>> referencesOfPlayerByTeamHashCode = new HashMap<>();
        for (Confrontation.Team team : confrontation.teams()) {
            referencesOfPlayerByTeamHashCode.put(team.hashCode(), team.players().stream().map(Confrontation.Team.Player::reference).collect(Collectors.toList()));
        }
        for (Map.Entry<Integer, List<String>> entry : referencesOfPlayerByTeamHashCode.entrySet()) {

        }
        Map<String, Map<Confrontation.Team.Player, List<Confrontation.Team>>> teamsByPlayerReference = confrontation.teams()
                .stream()
                .collect(Collectors.groupingBy(team -> team.players(), Collectors.groupingBy()));
        for (Map.Entry<String, List<Confrontation.Team.Player>> entry : playersByReference.entrySet()) {
            if (entry.getValue().size() > 1) {
                throw new SamePlayerCanNotBeInBothTeamDuringConfrontation(entry.getKey());
            }
        }
    }*/
}
