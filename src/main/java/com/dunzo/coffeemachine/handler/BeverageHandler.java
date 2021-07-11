package com.dunzo.coffeemachine.handler;

import com.dunzo.coffeemachine.fulfillment.BeverageFulfillmentFactory;
import com.dunzo.coffeemachine.model.*;
import com.dunzo.coffeemachine.model.fulfillment.BeverageFulfillmentResponse;
import com.dunzo.coffeemachine.processor.PourBeverage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BeverageHandler {

    @Autowired
    private BeverageFulfillmentFactory beverageFulfillmentFactory;

    @Autowired
    private PourBeverage pourBeverage;

    // Using builder and factory design pattern
    public CoffeeMachineResponse handle(CoffeeMachineRequest coffeeMachineRequest) {

        int freeOutlets = coffeeMachineRequest.machine().outlets().count_n();

        pourBeverage.setOutlets(freeOutlets);

        Beverages beverages = coffeeMachineRequest.machine().beverages();

        TotalItemsQuantity totalItemsQuantity = coffeeMachineRequest.machine().total_items_quantity();


        List<Ingredients> beveragesList = new ArrayList<>();

        List<String> messages = new ArrayList<>();

        beverages.hot_tea().map(hotTea -> beveragesList.add(hotTea));
        beverages.hot_coffee().map(hotCoffee -> beveragesList.add(hotCoffee));
        beverages.hot_milk().map(hotMilk -> beveragesList.add(hotMilk));
        beverages.green_tea().map(greenTea -> beveragesList.add(greenTea));
        beverages.black_tea().map(blackTea -> beveragesList.add(blackTea));
        beverages.hot_water().map(hotWater -> beveragesList.add(hotWater));

        for(Ingredients ingredients: beveragesList) {
            BeverageFulfillmentResponse beverageFulfillmentResponse =
                    beverageFulfillmentFactory.getFulfillment(ingredients, totalItemsQuantity).makeBeverage(ingredients, pourBeverage);
            // factory design pattern
            messages.add(beverageFulfillmentResponse.message());

            totalItemsQuantity = beverageFulfillmentResponse.totalItemsQuantity();

        }

        return ImmutableCoffeeMachineResponse.builder()
                .messages(messages).build();

    }
}
