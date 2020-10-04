package com.cycle.part.model;

import com.sun.istack.internal.NotNull;

import java.util.List;
import java.util.Objects;

public class CycleModel {
    @NotNull
    private final List<String> frame;
    @NotNull
    private final List<String> handle;
    @NotNull
    private final List<String> seating;
    @NotNull
    private final List<String> wheel;
    @NotNull
    private final List<String> chain;

    public CycleModel(
            List<String> frame,
            List<String> handle,
            List<String> seating,
            List<String> wheel,
            List<String> chain
    ) {
        this.frame = frame;
        this.handle = handle;
        this.seating = seating;
        this.wheel = wheel;
        this.chain = chain;
    }

    public List<String> getFrame() {
        return frame;
    }

    public List<String> getHandle() {
        return handle;
    }

    public List<String> getSeating() {
        return seating;
    }

    public List<String> getWheel() {
        return wheel;
    }

    public List<String> getChain() {
        return chain;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CycleModel that = (CycleModel) o;
        return frame.equals(that.frame) &&
                handle.equals(that.handle) &&
                seating.equals(that.seating) &&
                wheel.equals(that.wheel) &&
                chain.equals(that.chain);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frame, handle, seating, wheel, chain);
    }


}
