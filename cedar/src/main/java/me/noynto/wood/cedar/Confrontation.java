package me.noynto.wood.cedar;

import java.util.Objects;
import java.util.Set;

public record Confrontation(Set<Team> teams) {

    public Confrontation {
        Objects.requireNonNull(teams, "The teams are required.");
    }

    public record Team(
        Integer score,
        Set<Player> players
    ) {

        public Team {
            Objects.requireNonNull(score, "The score is required.");
            Objects.requireNonNull(players, "The players are required.");
        }

        public record Player(
            String reference,
            Position position
        ) {
            public Player {
                Objects.requireNonNull(reference, "The reference of the player is required.");
                Objects.requireNonNull(position, "The position of the player is required.");
            }

            public enum Position {
                ATTACKER,
                DEFENDER
            }
        }

    }

}
