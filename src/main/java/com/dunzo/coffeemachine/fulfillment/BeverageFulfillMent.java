package com.dunzo.coffeemachine.fulfillment;

import com.dunzo.coffeemachine.model.Ingredients;
import com.dunzo.coffeemachine.model.fulfillment.BeverageFulfillmentResponse;
import com.dunzo.coffeemachine.processor.PourBeverage;

public interface BeverageFulfillMent {

    public BeverageFulfillmentResponse makeBeverage(Ingredients ingredients, PourBeverage pourBeverage);

}
