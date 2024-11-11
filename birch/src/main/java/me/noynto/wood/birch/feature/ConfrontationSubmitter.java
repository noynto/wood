package me.noynto.wood.birch.feature;

import me.noynto.wood.birch.api.CheckConfrontation;
import me.noynto.wood.birch.api.CheckTeam;
import me.noynto.wood.birch.api.SubmitConfrontation;
import me.noynto.wood.birch.spi.ConfrontationRegistry;
import me.noynto.wood.cedar.*;

import java.util.Objects;
import java.util.logging.Logger;

public class ConfrontationSubmitter implements SubmitConfrontation {

    public static final String CONFRONTATION_IS_REQUIRED_MESSAGE = "The confrontation is required.";
    private static final Logger LOGGER = Logger.getLogger(ConfrontationSubmitter.class.getName());

    private final CheckConfrontation checkConfrontation;
    private final CheckTeam checkTeam;
    private final ConfrontationRegistry confrontationRegistry;

    public ConfrontationSubmitter(CheckConfrontation checkConfrontation, CheckTeam checkTeam, ConfrontationRegistry confrontationRegistry) {
        this.checkConfrontation = checkConfrontation;
        this.checkTeam = checkTeam;
        this.confrontationRegistry = confrontationRegistry;
    }

    @Override
    public String casual(Confrontation confrontation) throws NullPointerException, ConfrontationMustConsistOfTwoTeams, TeamsCanNotHaveEqualScores, PlayerCanNotFillMoreThanOnePosition, AllPositionsDuringConfrontationMustBeFilled {
        Objects.requireNonNull(confrontation, CONFRONTATION_IS_REQUIRED_MESSAGE);
        checkConfrontation.numberOfTeamsSupplied(confrontation);
        checkConfrontation.scoreOfTeamsSupplied(confrontation);
        for (Confrontation.Team team : confrontation.teams()) {
            checkTeam.playerHasOnlyOnePosition(team);
            checkTeam.allPositionsAreFilled(team);
        }
        return confrontationRegistry.push(confrontation);
    }
}
