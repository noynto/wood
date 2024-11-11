package me.noynto.wood.birch.feature;

import me.noynto.wood.birch.api.CheckConfrontation;
import me.noynto.wood.birch.api.CheckTeam;
import me.noynto.wood.birch.api.SubmitConfrontation;
import me.noynto.wood.birch.feature.stub.ConfrontationStub;
import me.noynto.wood.birch.spi.ConfrontationRegistry;
import me.noynto.wood.cedar.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

class ConfrontationSubmitterTest {

    private static final Logger LOGGER = Logger.getLogger(ConfrontationSubmitterTest.class.getName());

    @Test
    void should_throw_an_exception_because_the_provided_confrontation_is_null() {
        // GIVEN
        CheckConfrontation checkConfrontation = new ConfrontationChecker();
        CheckTeam checkTeam = new TeamChecker();
        ConfrontationRegistry confrontationRegistry = new ConfrontationStub();
        SubmitConfrontation submitConfrontation = new ConfrontationSubmitter(checkConfrontation, checkTeam, confrontationRegistry);
        Confrontation confrontation = null;
        // THEN
        NullPointerException nullPointerException = Assertions.assertThrows(NullPointerException.class, () -> {
            // WHEN
            submitConfrontation.casual(confrontation);
        });
        Assertions.assertNotNull(nullPointerException);
        Assertions.assertEquals(ConfrontationSubmitter.CONFRONTATION_IS_REQUIRED_MESSAGE, nullPointerException.getMessage());
        LOGGER.log(Level.INFO, "Null pointer exception has been thrown because the confrontation was null.");
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 3, 4})
    void should_throw_an_exception_that_there_is_only_two_teams_in_a_confrontation_exception_because_one_team_has_been_provided(int numberOfTeams) {
        // GIVEN
        CheckConfrontation checkConfrontation = new ConfrontationChecker();
        CheckTeam checkTeam = new TeamChecker();
        ConfrontationRegistry confrontationRegistry = new ConfrontationStub();
        SubmitConfrontation submitConfrontation = new ConfrontationSubmitter(checkConfrontation, checkTeam, confrontationRegistry);
        Confrontation confrontation = ConfrontationGenerator.teams(numberOfTeams);
        // THEN
        ConfrontationMustConsistOfTwoTeams confrontationMustConsistOfTwoTeams = Assertions.assertThrows(ConfrontationMustConsistOfTwoTeams.class, () -> {
            // WHEN
            submitConfrontation.casual(confrontation);
        });
        Assertions.assertNotNull(confrontationMustConsistOfTwoTeams);
        Assertions.assertEquals(numberOfTeams, confrontationMustConsistOfTwoTeams.getNumberOfTeamsSupplied());
        LOGGER.log(Level.INFO, "Confrontation must consist of two teams exception has been thrown because the confrontation contained " + confrontationMustConsistOfTwoTeams.getNumberOfTeamsSupplied() + " teams.");
    }

    @Test
    void should_throw_an_exception_that_the_score_of_both_teams_can_not_be_equals() {
        // GIVEN
        CheckConfrontation checkConfrontation = new ConfrontationChecker();
        CheckTeam checkTeam = new TeamChecker();
        ConfrontationRegistry confrontationRegistry = new ConfrontationStub();
        SubmitConfrontation submitConfrontation = new ConfrontationSubmitter(checkConfrontation, checkTeam, confrontationRegistry);
        Confrontation confrontation = ConfrontationGenerator.sameScore(6);
        // THEN
        TeamsCanNotHaveEqualScores teamsCanNotHaveEqualScores = Assertions.assertThrows(TeamsCanNotHaveEqualScores.class, () -> {
            // WHEN
            submitConfrontation.casual(confrontation);
        });
        Assertions.assertNotNull(teamsCanNotHaveEqualScores);
        Assertions.assertEquals(6, teamsCanNotHaveEqualScores.getTieScoreSupplied());
        LOGGER.log(Level.INFO, "Teams can not have equal scores exception has been thrown because the score of both teams were " + teamsCanNotHaveEqualScores.getTieScoreSupplied() + ".");
    }

    @Test
    void should_throw_an_exception_that_a_player_can_not_be_an_attacker_and_a_defender_at_the_same_time() {
        // GIVEN
        CheckConfrontation checkConfrontation = new ConfrontationChecker();
        CheckTeam checkTeam = new TeamChecker();
        ConfrontationRegistry confrontationRegistry = new ConfrontationStub();
        SubmitConfrontation submitConfrontation = new ConfrontationSubmitter(checkConfrontation, checkTeam, confrontationRegistry);
        Set<Confrontation.Team> teams = TeamGenerator.equalsAttackerAndDefender("F").collect(Collectors.toSet());
        Confrontation confrontation = new Confrontation(teams);
        // THEN
        PlayerCanNotFillMoreThanOnePosition playerCanNotFillMoreThanOnePosition = Assertions.assertThrows(PlayerCanNotFillMoreThanOnePosition.class, () -> {
            // WHEN
            submitConfrontation.casual(confrontation);
        });
        Assertions.assertNotNull(playerCanNotFillMoreThanOnePosition);
        Assertions.assertTrue(playerCanNotFillMoreThanOnePosition.getPlayerReference().contains("F-"));
        LOGGER.log(Level.INFO, "Attacker and defender of team can not be same player exception has been thrown because both references are " + playerCanNotFillMoreThanOnePosition.getPlayerReference() + ".");
    }

    /*@Test
    void should_throw_an_exception_that_a_player_can_not_be_in_two_opposite_teams_at_the_same_time() {
        // GIVEN
        CheckConfrontation checkConfrontation = new ConfrontationChecker();
        CheckTeam checkTeam = new TeamChecker();
        SubmitConfrontation submitConfrontation = new ConfrontationSubmitter(checkConfrontation,checkTeam);
        String playerReference = "P-0";
        Confrontation confrontation = ConfrontationGenerator.samePlayerInBothTeams(playerReference);
        // THEN
        SamePlayerCanNotBeInBothTeamDuringConfrontation samePlayerCanNotBeInBothTeamDuringConfrontation = Assertions.assertThrows(SamePlayerCanNotBeInBothTeamDuringConfrontation.class, () -> {
            // WHEN
            submitConfrontation.casual(confrontation);
        });
        Assertions.assertNotNull(samePlayerCanNotBeInBothTeamDuringConfrontation);
        Assertions.assertEquals(playerReference, samePlayerCanNotBeInBothTeamDuringConfrontation.getPlayerReference());
    }*/

    @Test
    void should_submit_a_confrontation_between_two_teams_of_two_players() {
        // GIVEN
        CheckConfrontation checkConfrontation = new ConfrontationChecker();
        CheckTeam checkTeam = new TeamChecker();
        ConfrontationRegistry confrontationRegistry = new ConfrontationStub();
        SubmitConfrontation submitConfrontation = new ConfrontationSubmitter(checkConfrontation, checkTeam, confrontationRegistry);
        Confrontation confrontation = ConfrontationGenerator.valid();
        // WHEN
        Assertions.assertDoesNotThrow(() -> {
            String confrontationReference = submitConfrontation.casual(confrontation);
            // THEN
            Assertions.assertNotNull(confrontationReference);
            Assertions.assertEquals(String.valueOf(confrontation.hashCode()), confrontationReference);
        });
    }

}
