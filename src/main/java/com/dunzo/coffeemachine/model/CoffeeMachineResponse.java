package com.dunzo.coffeemachine.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
@JsonSerialize(as = ImmutableCoffeeMachineResponse.class)
@JsonDeserialize(as = ImmutableCoffeeMachineResponse.class)
public interface CoffeeMachineResponse {

    List<String> messages();
}
