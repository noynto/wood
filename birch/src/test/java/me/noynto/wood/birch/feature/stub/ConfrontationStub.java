package me.noynto.wood.birch.feature.stub;

import me.noynto.wood.birch.spi.ConfrontationRegistry;
import me.noynto.wood.cedar.Confrontation;

public class ConfrontationStub implements ConfrontationRegistry {
    @Override
    public String push(Confrontation confrontation) {
        return String.valueOf(confrontation.hashCode());
    }
}
