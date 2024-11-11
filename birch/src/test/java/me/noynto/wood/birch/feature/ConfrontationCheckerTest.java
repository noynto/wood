package me.noynto.wood.birch.feature;

import me.noynto.wood.birch.api.CheckConfrontation;
import me.noynto.wood.cedar.Confrontation;
import me.noynto.wood.cedar.ConfrontationGenerator;
import me.noynto.wood.cedar.ConfrontationMustConsistOfTwoTeams;
import me.noynto.wood.cedar.TeamGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Set;
import java.util.stream.Collectors;

class ConfrontationCheckerTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 3, 4, 5, 6, 7, 8, 9})
    void should_throw_a_confrontation_is_made_up_of_two_teams(int numberOfTeams) {
        // GIVEN
        CheckConfrontation checkConfrontation = new ConfrontationChecker();
        Confrontation confrontation = ConfrontationGenerator.teams(numberOfTeams);
        // THEN
        ConfrontationMustConsistOfTwoTeams confrontationMustConsistOfTwoTeams = Assertions.assertThrows(ConfrontationMustConsistOfTwoTeams.class, () -> {
            checkConfrontation.numberOfTeamsSupplied(confrontation);
        });
        Assertions.assertEquals(numberOfTeams, confrontationMustConsistOfTwoTeams.getNumberOfTeamsSupplied());
    }

    @Test
    void should_passed_the_number_of_teams_supplied_checker() {
        // GIVEN
        CheckConfrontation checkConfrontation = new ConfrontationChecker();
        Set<Confrontation.Team> teams = TeamGenerator.quantity(2).collect(Collectors.toSet());
        Confrontation confrontation = new Confrontation(teams);
        // THEN
        Assertions.assertDoesNotThrow(() -> {
            // WHEN
            checkConfrontation.numberOfTeamsSupplied(confrontation);
        });
    }

}
