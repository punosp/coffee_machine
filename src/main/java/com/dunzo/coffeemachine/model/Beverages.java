package com.dunzo.coffeemachine.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.util.Optional;

@Value.Immutable
@JsonSerialize(as = ImmutableBeverages.class)
@JsonDeserialize(as = ImmutableBeverages.class)
@JsonIgnoreProperties
public interface Beverages {

    Optional<HotTea> hot_tea();

    Optional<HotCoffee> hot_coffee();

    Optional<BlackTea> black_tea();

    Optional<GreenTea> green_tea();

    Optional<HotWater> hot_water();

    Optional<HotMilk> hot_milk();

}
