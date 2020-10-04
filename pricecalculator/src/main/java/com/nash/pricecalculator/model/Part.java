package com.nash.pricecalculator.model;

public final class Part {
    private final String id;

    public Part(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Part{" +
                "id='" + id + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }
}
