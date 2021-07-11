package com.dunzo.coffeemachine.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableBlackTea.class)
@JsonDeserialize(as = ImmutableBlackTea.class)
public interface BlackTea extends Ingredients{
}
