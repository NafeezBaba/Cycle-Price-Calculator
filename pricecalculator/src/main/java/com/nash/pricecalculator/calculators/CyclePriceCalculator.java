package com.nash.pricecalculator.calculators;

import com.nash.pricecalculator.model.Cycle;
import com.nash.pricecalculator.model.CycleComponent;
import com.nash.pricecalculator.bo.PriceResult;

public final class CyclePriceCalculator {
    private final ComponentPriceCalculator componentPriceCalculator;

    public CyclePriceCalculator(ComponentPriceCalculator componentPriceCalculator) {
        this.componentPriceCalculator = componentPriceCalculator;
    }

    public static CyclePriceCalculator getCyclePriceCalculator(PartPriceCalculator partPriceCalculator) {
        return new CyclePriceCalculator(
                new ComponentPriceCalculator(
                        partPriceCalculator
                )
        );
    }

    public PriceResult getPrice(final Cycle cycle) {
        PriceResult finalResult = new PriceResult(0.0f);

        for (CycleComponent cycleComponent : cycle.getComponents()) {
            PriceResult componentPrice = componentPriceCalculator.calculatePrice(cycleComponent);
            finalResult = finalResult.add(componentPrice);
        }

        return finalResult;
    }
}
