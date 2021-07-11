package com.dunzo.coffeemachine.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableOutlets.class)
@JsonDeserialize(as = ImmutableOutlets.class)
public interface Outlets {
    int count_n();
}
