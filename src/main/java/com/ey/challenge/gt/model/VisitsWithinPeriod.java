package com.ey.challenge.gt.model;

import java.time.LocalTime;
import java.util.Objects;

/**
 * This class represents the number of visits between two hours.
 * @author Paweł Ryszawa
 */
public class VisitsWithinPeriod extends VisitPeriod {

    private int noOfVisits;

    /**
     * Constructor.
     * @param from       The 'from' property.
     * @param to         The 'to' property.
     * @param noOfVisits Number of visits between 'from' and 'to'.
     */
    public VisitsWithinPeriod(LocalTime from, LocalTime to, int noOfVisits) {
        super(from, to);
        this.noOfVisits = noOfVisits;
    }

    /**
     * Getter for property 'noOfVisits'.
     *
     * @return Value for property 'noOfVisits'.
     */
    public int getNoOfVisits() {
        return noOfVisits;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        VisitsWithinPeriod that = (VisitsWithinPeriod) o;
        return this.getNoOfVisits() == that.getNoOfVisits() &&
                Objects.equals(this.getFrom(), that.getFrom()) &&
                Objects.equals(this.getTo(), that.getTo()); // cannot use super.equals as the types are different
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getNoOfVisits());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return super.toString() + "; " + noOfVisits;
    }

}
