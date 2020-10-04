package com.nash.pricecalculator.bo;

public final class PriceResult {

    private final Float value;

    public PriceResult(float value) {
        this.value = value;
    }

    public float getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PriceResult that = (PriceResult) o;
        return Float.compare(that.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return "PriceResult{" +
                "value=" + value +
                '}';
    }

    public PriceResult add(PriceResult additionValue) {
        return new PriceResult(this.value + additionValue.value);
    }
}
