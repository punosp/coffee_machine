package com.dunzo.coffeemachine.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableTotalItemsQuantity.class)
@JsonDeserialize(as = ImmutableTotalItemsQuantity.class)
public interface TotalItemsQuantity extends Ingredients{

}
