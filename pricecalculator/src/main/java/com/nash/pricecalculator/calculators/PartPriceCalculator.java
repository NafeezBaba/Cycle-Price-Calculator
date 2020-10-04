package com.nash.pricecalculator.calculators;

import com.nash.pricecalculator.model.Part;
import com.nash.pricecalculator.bo.PriceResult;
import com.sun.istack.internal.NotNull;

public interface PartPriceCalculator {
    @NotNull PriceResult getPrice(final Part part);
}
