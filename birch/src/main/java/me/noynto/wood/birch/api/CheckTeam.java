package me.noynto.wood.birch.api;

import me.noynto.wood.cedar.AllPositionsDuringConfrontationMustBeFilled;
import me.noynto.wood.cedar.PlayerCanNotFillMoreThanOnePosition;
import me.noynto.wood.cedar.Confrontation;

public interface CheckTeam {
    void playerHasOnlyOnePosition(Confrontation.Team team) throws PlayerCanNotFillMoreThanOnePosition;

    void allPositionsAreFilled(Confrontation.Team team) throws AllPositionsDuringConfrontationMustBeFilled;
}
