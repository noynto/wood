package me.noynto.wood.birch.api;

import me.noynto.wood.cedar.*;

public interface SubmitConfrontation {
    String casual(Confrontation confrontation) throws NullPointerException, ConfrontationMustConsistOfTwoTeams, TeamsCanNotHaveEqualScores, PlayerCanNotFillMoreThanOnePosition, AllPositionsDuringConfrontationMustBeFilled;
}
