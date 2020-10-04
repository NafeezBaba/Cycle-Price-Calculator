package com.cycle.part;

import com.cycle.part.model.CycleModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nash.pricecalculator.model.Cycle;
import com.nash.pricecalculator.model.CycleComponent;
import com.nash.pricecalculator.model.Part;
import com.nash.pricecalculator.calculators.CyclePriceCalculator;
import com.nash.pricecalculator.calculators.PartPriceCalculator;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

public class StartApp {
    public static void main(String[] args) {
        String cycleJsonFile = args[0];
        String partJsonFile = args[1];
        int parallelThreads = Integer.parseInt(args[2]);

        Gson gson = new Gson();
        PartPriceCalculator partPriceCalculator = new JsonPartPriceCalculator(gson, partJsonFile);
        CyclePriceCalculator cyclePriceCalculator = CyclePriceCalculator.getCyclePriceCalculator(partPriceCalculator);

        ForkJoinPool forkJoinPool = new ForkJoinPool(parallelThreads);

        try (Reader reader = new FileReader(cycleJsonFile)) {
            Type listType = new TypeToken<List<CycleModel>>() {
            }.getType();
            List<CycleModel> cycleList = gson.fromJson(reader, listType);

            CountDownLatch countDownLatch = new CountDownLatch(cycleList.size());

            forkJoinPool.submit(() -> cycleList.stream()
                    .parallel()
                    .map(StartApp::mapCycleModelToCycle)
                    .forEach(cycle -> {
                        System.out.println(cycle + " Price: " + cyclePriceCalculator.getPrice(cycle));
                        countDownLatch.countDown();
                    }));
            countDownLatch.await();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            forkJoinPool.shutdown();
        }
    }

    public static Cycle mapCycleModelToCycle(CycleModel cycleModel) {
        List<CycleComponent> cycleComponents = new ArrayList<>();
        cycleComponents.add(new CycleComponent(cycleModel.getFrame().stream().map(StartApp::getPart).collect(Collectors.toList())));
        cycleComponents.add(new CycleComponent(cycleModel.getChain().stream().map(StartApp::getPart).collect(Collectors.toList())));
        cycleComponents.add(new CycleComponent(cycleModel.getHandle().stream().map(StartApp::getPart).collect(Collectors.toList())));
        cycleComponents.add(new CycleComponent(cycleModel.getSeating().stream().map(StartApp::getPart).collect(Collectors.toList())));
        cycleComponents.add(new CycleComponent(cycleModel.getWheel().stream().map(StartApp::getPart).collect(Collectors.toList())));
        return new Cycle(cycleComponents);
    }

    public static Part getPart(String id) {
        return new Part(id);
    }
}
