package com.dunzo.coffeemachine.model.enums;

public enum BeverageType {

    hotWater("hot water"),
    hotMilk(" hot milk"),
    hotTea("hot tea"),
    hotCoffee("hot coffee"),
    blackTea("black tea"),
    greenTea("green tea");

    private String value;

    private BeverageType(String value) {
        this.value = value;
    }
}
