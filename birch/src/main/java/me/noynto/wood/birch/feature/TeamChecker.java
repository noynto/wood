package me.noynto.wood.birch.feature;

import me.noynto.wood.birch.api.CheckTeam;
import me.noynto.wood.cedar.AllPositionsDuringConfrontationMustBeFilled;
import me.noynto.wood.cedar.Confrontation;
import me.noynto.wood.cedar.PlayerCanNotFillMoreThanOnePosition;

import java.util.*;
import java.util.stream.Collectors;

public class TeamChecker implements CheckTeam {
    public static final String TEAM_REQUIRED_MESSAGE = "The team is required.";

    @Override
    public void playerHasOnlyOnePosition(Confrontation.Team team) throws PlayerCanNotFillMoreThanOnePosition {
        Objects.requireNonNull(team, TEAM_REQUIRED_MESSAGE);
        Map<String, List<Confrontation.Team.Player>> playersByReference = team.players()
                .stream()
                .collect(Collectors.groupingBy(Confrontation.Team.Player::reference));
        for (Map.Entry<String, List<Confrontation.Team.Player>> entry : playersByReference.entrySet()) {
            if (entry.getValue().size() > 1) {
                throw new PlayerCanNotFillMoreThanOnePosition(entry.getKey());
            }
        }
    }

    @Override
    public void allPositionsAreFilled(Confrontation.Team team) throws AllPositionsDuringConfrontationMustBeFilled {
        Objects.requireNonNull(team, TEAM_REQUIRED_MESSAGE);
        List<Confrontation.Team.Player.Position> currentFilledPositions = team.players().stream().map(Confrontation.Team.Player::position).sorted().toList();
        List<Confrontation.Team.Player.Position> requiredPositions = new ArrayList<>(Arrays.stream(Confrontation.Team.Player.Position.values()).toList());
        if (!Objects.equals(currentFilledPositions, requiredPositions)) {
            requiredPositions.removeAll(currentFilledPositions);
            throw new AllPositionsDuringConfrontationMustBeFilled(requiredPositions.stream().map(Enum::name).collect(Collectors.toSet()));
        }
    }
}
