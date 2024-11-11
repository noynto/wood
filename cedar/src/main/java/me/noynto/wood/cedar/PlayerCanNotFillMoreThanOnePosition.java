package me.noynto.wood.cedar;

public class PlayerCanNotFillMoreThanOnePosition extends Throwable {
    public static final String KEY = "player_can_not_fill_more_than_one_position";
    private final String playerReference;

    public PlayerCanNotFillMoreThanOnePosition(String playerReference) {
        this.playerReference = playerReference;
    }

    public String getPlayerReference() {
        return playerReference;
    }
}
