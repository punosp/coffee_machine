package com.dunzo.coffeemachine.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.util.Optional;

@Value.Immutable
@JsonSerialize(as = ImmutableIngredients.class)
@JsonDeserialize(as = ImmutableIngredients.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public interface Ingredients {

    Optional<Integer> hot_water();

    Optional<Integer> hot_milk();

    Optional<Integer> ginger_syrup();

    Optional<Integer> sugar_syrup();

    Optional<Integer> tea_leaves_syrup();

    Optional<Integer> green_mixture();

}
