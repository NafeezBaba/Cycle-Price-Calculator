package com.nash.pricecalculator.model;

import java.util.List;

public final class Cycle {

    private final List<CycleComponent> cycleComponents;

    public Cycle(List<CycleComponent> cycleComponents) {
        this.cycleComponents = cycleComponents;
    }

    @Override
    public String toString() {
        return "Cycle{" +
                "cycleComponents=" + cycleComponents +
                '}';
    }

    public List<CycleComponent> getComponents() {
        return cycleComponents;
    }

}
