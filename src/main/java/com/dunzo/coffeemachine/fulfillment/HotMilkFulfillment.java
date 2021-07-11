package com.dunzo.coffeemachine.fulfillment;

import com.dunzo.coffeemachine.model.Ingredients;
import com.dunzo.coffeemachine.model.TotalItemsQuantity;
import com.dunzo.coffeemachine.model.ValidityResponse;
import com.dunzo.coffeemachine.model.enums.BeverageType;
import com.dunzo.coffeemachine.model.fulfillment.BeverageFulfillmentResponse;
import com.dunzo.coffeemachine.model.fulfillment.ImmutableBeverageFulfillmentResponse;
import com.dunzo.coffeemachine.processor.CalculateIngredients;
import com.dunzo.coffeemachine.processor.PourBeverage;

import java.util.Collections;

public class HotMilkFulfillment implements BeverageFulfillMent {

    private TotalItemsQuantity totalItemsQuantity;
    private CalculateIngredients calculateIngredients;

    public HotMilkFulfillment(TotalItemsQuantity totalItemsQuantity) {
        this.totalItemsQuantity = totalItemsQuantity;
        this.calculateIngredients = new CalculateIngredients(totalItemsQuantity);
    }

    @Override
    public BeverageFulfillmentResponse makeBeverage(Ingredients ingredients, PourBeverage pourBeverage) {

        ValidityResponse validityResponse = calculateIngredients.getValiDityRespose(ingredients);
        if(validityResponse.ingredients().size() == 0) {
            totalItemsQuantity = calculateIngredients.reduceTotalQuantity(ingredients);
            // some async call to start serving beverage
//            new Thread(() -> {
//                // call the maker -- non blocking
//            }).start();
            pourBeverage.pour(Collections.singleton(BeverageType.hotMilk));
        }

        String message = calculateIngredients.getMessage(BeverageType.hotMilk, validityResponse);

        return ImmutableBeverageFulfillmentResponse.builder()
                .message(message)
                .totalItemsQuantity(totalItemsQuantity)
                .build();
    }
}
