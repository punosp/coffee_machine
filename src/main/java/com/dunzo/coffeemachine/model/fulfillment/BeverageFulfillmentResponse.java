package com.dunzo.coffeemachine.model.fulfillment;

import com.dunzo.coffeemachine.model.ImmutableTotalItemsQuantity;
import com.dunzo.coffeemachine.model.TotalItemsQuantity;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableBeverageFulfillmentResponse.class)
@JsonDeserialize(as = ImmutableBeverageFulfillmentResponse.class)
public interface BeverageFulfillmentResponse {

    String message();

    TotalItemsQuantity totalItemsQuantity();

}
