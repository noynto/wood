package me.noynto.wood.cedar;

import java.util.Set;

public class AllPositionsDuringConfrontationMustBeFilled extends Throwable {
    public static final String KEY = "all_positions_during_confrontation_must_be_filled";
    public final Set<String> missingPositions;

    public AllPositionsDuringConfrontationMustBeFilled(Set<String> missingPositions) {
        this.missingPositions = missingPositions;
    }

    public Set<String> getMissingPositions() {
        return missingPositions;
    }
}
