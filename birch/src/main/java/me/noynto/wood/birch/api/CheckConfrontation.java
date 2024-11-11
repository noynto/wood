package me.noynto.wood.birch.api;

import me.noynto.wood.cedar.Confrontation;
import me.noynto.wood.cedar.ConfrontationMustConsistOfTwoTeams;
import me.noynto.wood.cedar.SamePlayerCanNotBeInBothTeamDuringConfrontation;
import me.noynto.wood.cedar.TeamsCanNotHaveEqualScores;

public interface CheckConfrontation {
    void numberOfTeamsSupplied(Confrontation confrontation) throws ConfrontationMustConsistOfTwoTeams;

    void scoreOfTeamsSupplied(Confrontation confrontation) throws TeamsCanNotHaveEqualScores;

/*
    void uniquenessOfPlayers(Confrontation confrontation) throws SamePlayerCanNotBeInBothTeamDuringConfrontation;
*/
}
