package com.dunzo.coffeemachine.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableCoffeeMachineRequest.class)
@JsonDeserialize(as = ImmutableCoffeeMachineRequest.class)
public interface CoffeeMachineRequest {

    Machine machine();
}
