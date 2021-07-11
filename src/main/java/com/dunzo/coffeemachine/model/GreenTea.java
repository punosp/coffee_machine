package com.dunzo.coffeemachine.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableGreenTea.class)
@JsonDeserialize(as = ImmutableGreenTea.class)
public interface GreenTea extends Ingredients {
}
