package me.noynto.wood.birch.feature;

import me.noynto.wood.birch.api.CheckTeam;
import me.noynto.wood.cedar.AllPositionsDuringConfrontationMustBeFilled;
import me.noynto.wood.cedar.Confrontation;
import me.noynto.wood.cedar.TeamGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Set;

class TeamCheckerTest {

    @ParameterizedTest
    @CsvSource(value = {"ATTACKER, ATTACKER, DEFENDER", "DEFENDER, DEFENDER, ATTACKER"})
    void should_throw_an_exception_because_each_position_of_the_team_have_not_been_filled(Confrontation.Team.Player.Position position1, Confrontation.Team.Player.Position position2, Confrontation.Team.Player.Position missingPosition) {
        // GIVEN
        CheckTeam checkTeam = new TeamChecker();
        Confrontation.Team team = TeamGenerator.positons(position1, position2);
        // THEN
        AllPositionsDuringConfrontationMustBeFilled allPositionsDuringConfrontationMustBeFilled = Assertions.assertThrows(AllPositionsDuringConfrontationMustBeFilled.class, () -> {
            checkTeam.allPositionsAreFilled(team);
        });
        Assertions.assertNotNull(allPositionsDuringConfrontationMustBeFilled);
        Assertions.assertEquals(Set.of(missingPosition.name()), allPositionsDuringConfrontationMustBeFilled.getMissingPositions());
    }

}
