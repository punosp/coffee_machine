package com.dunzo.coffeemachine.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableHotCoffee.class)
@JsonDeserialize(as = ImmutableHotCoffee.class)
public interface HotCoffee extends Ingredients {
}
