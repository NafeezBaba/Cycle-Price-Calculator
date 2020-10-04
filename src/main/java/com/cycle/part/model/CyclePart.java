package com.cycle.part.model;

import com.sun.istack.internal.NotNull;

import java.util.Objects;

public class CyclePart {
    @NotNull
    private final String id;
    @NotNull
    private final String startDate;
    @NotNull
    private final float price;
    @NotNull
    private final String endDate;

    public CyclePart(String id, float price, String startDate, String endDate) {
        this.id = id;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getId() {
        return id;
    }

    public String getStartDate() {
        return startDate;
    }

    public float getPrice() {
        return price;
    }

    public String getEndDate() {
        return endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CyclePart cyclePart = (CyclePart) o;
        return Float.compare(cyclePart.price, price) == 0 &&
                id.equals(cyclePart.id) &&
                startDate.equals(cyclePart.startDate) &&
                endDate.equals(cyclePart.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, startDate, endDate);
    }

    @Override
    public String toString() {
        return "CyclePart{" +
                "id='" + id + '\'' +
                ", startDate='" + startDate + '\'' +
                ", price=" + price +
                ", endDate='" + endDate + '\'' +
                '}';
    }
}
