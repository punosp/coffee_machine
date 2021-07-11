package com.dunzo.coffeemachine.app;

import com.dunzo.coffeemachine.handler.BeverageHandler;
import com.dunzo.coffeemachine.model.CoffeeMachineRequest;
import com.dunzo.coffeemachine.model.CoffeeMachineResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class CoffeeMachineController {

    private final BeverageHandler beverageHandler;

    @Autowired
    public CoffeeMachineController(BeverageHandler beverageHandler) {
        this.beverageHandler = beverageHandler;
    }

    @PostMapping("/beverages")
    public CoffeeMachineResponse makeBeverages(@RequestBody CoffeeMachineRequest coffeeMachineRequest) {
        return beverageHandler.handle(coffeeMachineRequest);
    }
}
