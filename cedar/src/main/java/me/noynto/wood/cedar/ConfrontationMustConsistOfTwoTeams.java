package me.noynto.wood.cedar;

public class ConfrontationMustConsistOfTwoTeams extends Throwable {
    public static final String KEY = "confrontation_must_consist_of_two_teams";
    private final int numberOfTeamsSupplied;

    public ConfrontationMustConsistOfTwoTeams(int numberOfTeamsSupplied) {
        this.numberOfTeamsSupplied = numberOfTeamsSupplied;
    }

    public int getNumberOfTeamsSupplied() {
        return numberOfTeamsSupplied;
    }
}
