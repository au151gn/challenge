package com.ey.challenge.gt.model;

import java.time.LocalTime;
import java.util.Objects;

/**
 * This class represents the time boundaries of some visit.
 * @author Paweł Ryszawa
 */
public class VisitPeriod {

    private LocalTime from;

    private LocalTime to;

    /**
     * Constructor.
     * @param from The 'from' property.
     * @param to The 'to' property.
     */
    public VisitPeriod(LocalTime from, LocalTime to) {
        this.from = from;
        this.to = to;
    }

    /**
     * Getter for property 'from'.
     *
     * @return Value for property 'from'.
     */
    public LocalTime getFrom() {
        return from;
    }

    /**
     * Getter for property 'to'.
     *
     * @return Value for property 'to'.
     */
    public LocalTime getTo() {
        return to;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VisitPeriod that = (VisitPeriod) o;
        return Objects.equals(from, that.from) &&
                Objects.equals(to, that.to);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(from, to);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return from + " - " + to;
    }

}
