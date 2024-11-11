package me.noynto.wood.cedar;

public class SamePlayerCanNotBeInBothTeamDuringConfrontation extends Throwable {
    public static final String KEY = "same_player_can_not_be_in_both_team_during_confrontation";
    private final String playerReference;

    public SamePlayerCanNotBeInBothTeamDuringConfrontation(String playerReference) {
        this.playerReference = playerReference;
    }

    public String getPlayerReference() {
        return playerReference;
    }
}
