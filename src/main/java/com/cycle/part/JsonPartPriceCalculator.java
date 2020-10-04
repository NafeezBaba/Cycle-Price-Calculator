package com.cycle.part;

import com.cycle.part.exceptions.PartNotFoundException;
import com.cycle.part.model.CyclePart;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nash.pricecalculator.model.Part;
import com.nash.pricecalculator.bo.PriceResult;
import com.nash.pricecalculator.calculators.PartPriceCalculator;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonPartPriceCalculator implements PartPriceCalculator {

    private final Map<String, CyclePart> cyclePartMap;

    public JsonPartPriceCalculator(final Gson gson, final String filePath) {
        cyclePartMap = new HashMap<>();

        try (Reader reader = new FileReader(filePath)) {
            Type listType = new TypeToken<List<CyclePart>>() {
            }.getType();
            List<CyclePart> partList = gson.fromJson(reader, listType);

            for (CyclePart part : partList) {
                cyclePartMap.put(part.getId(), part);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public PriceResult getPrice(Part part) throws PartNotFoundException {
        if (!cyclePartMap.containsKey(part.getId())) {
            throw new PartNotFoundException();
        }

        return new PriceResult(cyclePartMap.get(part.getId()).getPrice());
    }
}
