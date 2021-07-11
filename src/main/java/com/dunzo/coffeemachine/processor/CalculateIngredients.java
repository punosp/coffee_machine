package com.dunzo.coffeemachine.processor;

import com.dunzo.coffeemachine.model.*;
import com.dunzo.coffeemachine.model.enums.BeverageType;
import com.dunzo.coffeemachine.model.enums.IngredientType;
import com.dunzo.coffeemachine.model.enums.ResponseType;

import java.util.*;

public class CalculateIngredients {

    private TotalItemsQuantity totalItemsQuantity;

    public CalculateIngredients(TotalItemsQuantity totalItemsQuantity) {
        this.totalItemsQuantity = totalItemsQuantity;
    }

    public ValidityResponse getValiDityRespose(Ingredients ingredients) {

        List<String> inSufficient = new ArrayList<>();

        List<String> notAvailable = new ArrayList<>();

        ingredients.ginger_syrup()
                .map(ginger ->
                    totalItemsQuantity.ginger_syrup()
                            .map(totalGinger -> {
                                if(totalGinger < ginger) {
                                    inSufficient.add(IngredientType.gingerSyrup.name());
                                }
                                return false;
                            }).orElseGet(() -> notAvailable.add(IngredientType.gingerSyrup.name()))
        );

        ingredients.green_mixture()
                .map(greenMixture ->
                        totalItemsQuantity.green_mixture()
                                .map(totalGreenMixture -> {
                                    if(totalGreenMixture < greenMixture) {
                                        inSufficient.add(IngredientType.greenMixture.name());
                                    }
                                    return false;
                                }).orElseGet(() -> notAvailable.add(IngredientType.greenMixture.name()))
                );

        ingredients.sugar_syrup()
                .map(sugarSyrup ->
                        totalItemsQuantity.sugar_syrup()
                                .map(totalSugarSyrup -> {
                                    if(totalSugarSyrup < sugarSyrup) {
                                        inSufficient.add(IngredientType.sugarSyrup.name());
                                    }
                                    return false;
                                }).orElseGet(() -> notAvailable.add(IngredientType.sugarSyrup.name()))
                );

        ingredients.tea_leaves_syrup()
                .map(teaLeaves ->
                        totalItemsQuantity.tea_leaves_syrup()
                                .map(totalTeaLeaves -> {
                                    if(totalTeaLeaves < teaLeaves) {
                                        inSufficient.add(IngredientType.teaLeavesSyrup.name());
                                    }
                                    return false;
                                }).orElseGet(() -> notAvailable.add(IngredientType.teaLeavesSyrup.name()))
                );

        ingredients.hot_milk()
                .map(hotMilk ->
                        totalItemsQuantity.hot_milk()
                                .map(totalHotMilk -> {
                                    if(totalHotMilk < hotMilk) {
                                        inSufficient.add(IngredientType.hotMilk.name());
                                    }
                                    return false;
                                }).orElseGet(() -> notAvailable.add(IngredientType.hotMilk.name()))
                );

        ingredients.hot_water()
                .map(hotWater ->
                        totalItemsQuantity.hot_water()
                                .map(totalHotWater -> {
                                    if(totalHotWater < hotWater) {
                                        inSufficient.add(IngredientType.hotWater.name());
                                    }
                                    return false;
                                }).orElseGet(() -> notAvailable.add(IngredientType.hotWater.name()))
                );

        Map<ResponseType, List<String>> responseTypeListMap = new HashMap<>();

        if(inSufficient.size() > 0) {
            responseTypeListMap.put(ResponseType.not_sufficient, inSufficient);
        }

        if(notAvailable.size() > 0) {
            responseTypeListMap.put(ResponseType.not_available, notAvailable);
        }

        return ImmutableValidityResponse.builder().ingredients(responseTypeListMap).build();
    }

    public TotalItemsQuantity reduceTotalQuantity(Ingredients ingredients) {

        Optional<Integer> gingerSyrup = ingredients.ginger_syrup()
                .map(ginger -> Optional.of(totalItemsQuantity.ginger_syrup().get() - ginger)).orElse(Optional.empty());

        Optional<Integer> greenMixture = ingredients.green_mixture()
                .map(greenMix -> Optional.of(totalItemsQuantity.green_mixture().get() - greenMix)).orElse(Optional.empty());

        Optional<Integer> sugarSyrup = ingredients.sugar_syrup()
                .map(sugar -> Optional.of(totalItemsQuantity.sugar_syrup().get() - sugar)).orElse(Optional.empty());

        Optional<Integer> teaLeavesSyrup = ingredients.tea_leaves_syrup()
                .map(teaLeaves -> Optional.of(totalItemsQuantity.tea_leaves_syrup().get() - teaLeaves)).orElse(Optional.empty());

        Optional<Integer> hotMilk = ingredients.hot_milk()
                .map(milk -> Optional.of(totalItemsQuantity.hot_milk().get() - milk)).orElse(Optional.empty());

        Optional<Integer> hotWater = ingredients.hot_water()
                .map(water -> Optional.of(totalItemsQuantity.hot_water().get() - water)).orElse(Optional.empty());

        return ImmutableTotalItemsQuantity.builder()
                .ginger_syrup(gingerSyrup)
                .green_mixture(greenMixture)
                .sugar_syrup(sugarSyrup)
                .tea_leaves_syrup(teaLeavesSyrup)
                .hot_milk(hotMilk)
                .hot_water(hotWater)
                .build();


    }

    public String getMessage(BeverageType beverageType, ValidityResponse validityResponse) {

        List<String> constructMessage = new ArrayList<>();
        constructMessage.add(beverageType.name());

        if(validityResponse.ingredients().size() > 0) {
            constructMessage.add("cannot be prepared because");
            if(validityResponse.ingredients().containsKey(ResponseType.not_available)) {
                for(String ind: validityResponse.ingredients().get(ResponseType.not_available)) {
                    constructMessage.add(ind);
                    constructMessage.add("and");
                }
                constructMessage.remove(constructMessage.size() - 1);
                constructMessage.add("are not available");
            }
            if(validityResponse.ingredients().containsKey(ResponseType.not_sufficient)) {
                if(validityResponse.ingredients().containsKey(ResponseType.not_available))
                    constructMessage.add("and");
                for(String ind: validityResponse.ingredients().get(ResponseType.not_sufficient)) {
                    constructMessage.add(ind);
                    constructMessage.add("and");
                }
                constructMessage.remove(constructMessage.size() - 1);
                constructMessage.add("are not sufficient");
            }
        } else {
            constructMessage.add("is prepared");
        }

        return String.join(" ", constructMessage);
    }
}
