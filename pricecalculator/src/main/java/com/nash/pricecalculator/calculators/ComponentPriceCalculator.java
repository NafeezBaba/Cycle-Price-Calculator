package com.nash.pricecalculator.calculators;

import com.nash.pricecalculator.model.CycleComponent;
import com.nash.pricecalculator.model.Part;
import com.nash.pricecalculator.bo.PriceResult;

public final class ComponentPriceCalculator {
    private final PartPriceCalculator partPriceCalculator;

    public ComponentPriceCalculator(PartPriceCalculator partPriceCalculator) {
        this.partPriceCalculator = partPriceCalculator;
    }

    public PriceResult calculatePrice(CycleComponent cycleComponent) {
        PriceResult finalPrice = new PriceResult(0.0f);
        for (Part part : cycleComponent.getParts()) {
            PriceResult partPrice = partPriceCalculator.getPrice(part);
            finalPrice = finalPrice.add(partPrice);
        }
        return finalPrice;
    }
}
