package com.dunzo.coffeemachine.model.enums;

public enum IngredientType {

    hotWater("hot water"),
    hotMilk(" hot milk"),
    gingerSyrup("ginger syrup"),
    sugarSyrup("sugar syrup"),
    teaLeavesSyrup("tea leaves syrup"),
    greenMixture("green mixture");

    private String value;

    private IngredientType(String value) {
        this.value = value;
    }
}
