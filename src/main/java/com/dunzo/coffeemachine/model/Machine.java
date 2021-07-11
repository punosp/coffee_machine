package com.dunzo.coffeemachine.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableMachine.class)
@JsonDeserialize(as = ImmutableMachine.class)

public interface Machine {
    Outlets outlets();

    TotalItemsQuantity total_items_quantity();

    Beverages beverages();

}
