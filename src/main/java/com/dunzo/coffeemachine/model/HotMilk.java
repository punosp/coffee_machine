package com.dunzo.coffeemachine.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableHotMilk.class)
@JsonDeserialize(as = ImmutableHotMilk.class)
public interface HotMilk extends Ingredients {
}
