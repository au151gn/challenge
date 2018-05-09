package com.ey.challenge.gt.model;

import java.time.LocalTime;
import java.util.Objects;

/**
 * This class represents the number of simultaneous visits at
 * the given time.
 * @author Paweł Ryszawa
 */
public class VisitsAtTime {

    private LocalTime time;

    private int noOfVisits;

    /**
     * Constructor.
     * @param time "Point of Time" property.
     * @param noOfVisits "Number of Visits" property.
     */
    public VisitsAtTime(LocalTime time, int noOfVisits) {
        this.time = time;
        this.noOfVisits = noOfVisits;
    }

    /**
     * Getter for property "Point of Time".
     *
     * @return Value for property "Point of Time".
     */
    public LocalTime getTime() {
        return time;
    }

    /**
     * Getter for property "Number of Visits".
     *
     * @return Value for property "Number of Visits".
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
        VisitsAtTime that = (VisitsAtTime) o;
        return noOfVisits == that.noOfVisits &&
                Objects.equals(time, that.time);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(time, noOfVisits);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return time + "; " + noOfVisits;
    }
}
;