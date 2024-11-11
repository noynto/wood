package me.noynto.wood.cedar;

public class TeamsCanNotHaveEqualScores extends Throwable {
    public static final String KEY = "teams_can_not_have_equal_scores";
    private final int tieScoreSupplied;

    public TeamsCanNotHaveEqualScores(int tieScoreSupplied) {
        this.tieScoreSupplied = tieScoreSupplied;
    }

    public int getTieScoreSupplied() {
        return tieScoreSupplied;
    }
}
