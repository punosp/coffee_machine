package com.dunzo.coffeemachine.fulfillment;

import com.dunzo.coffeemachine.model.*;
import org.springframework.stereotype.Service;

@Service
public class BeverageFulfillmentFactory {

    public BeverageFulfillMent getFulfillment(Ingredients ingredients, TotalItemsQuantity totalItemsQuantity) {

        if(ingredients == null) return null;

        if(ingredients instanceof BlackTea) {
            return new BlackTeaFulfillment(totalItemsQuantity);
        }

        if(ingredients instanceof GreenTea) {
            return new GreenTeaFulfillment(totalItemsQuantity);
        }

        if(ingredients instanceof HotCoffee) {
            return new HotCoffeeFulfillment(totalItemsQuantity);
        }

        if(ingredients instanceof HotMilk) {
            return new HotMilkFulfillment(totalItemsQuantity);
        }

        if(ingredients instanceof HotWater) {
            return new HotWaterFulfillment(totalItemsQuantity);
        }

        if(ingredients instanceof HotTea) {
            return new HotTeaFulfillment(totalItemsQuantity);
        }

        return null;
    }
}
