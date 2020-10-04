package com.nash.pricecalculator.model;

import java.util.List;

public final class CycleComponent {

    private final List<Part> parts;

    public CycleComponent(List<Part> parts) {
        this.parts = parts;
    }

    @Override
    public String toString() {
        return "CycleComponent{" +
                "parts=" + parts +
                '}';
    }

    public final List<Part> getParts() {
        return parts;
    }
}
