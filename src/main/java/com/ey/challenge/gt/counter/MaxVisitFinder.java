package com.ey.challenge.gt.counter;

import com.ey.challenge.gt.Converter;
import com.ey.challenge.gt.model.VisitPeriod;
import com.ey.challenge.gt.model.VisitsAtTime;

import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Finds the point of time with the highest number of visits.
 * @author Paweł Ryszawa
 */
public class MaxVisitFinder implements Converter<Optional<VisitsAtTime>, Stream<VisitPeriod>> {

    private Converter<Stream<VisitsAtTime>, Stream<VisitPeriod>> counter = new VisitsCounter();

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<VisitsAtTime> convert(Stream<VisitPeriod> src) {
        Stream<VisitsAtTime> vts = counter.convert(src);
        Optional<VisitsAtTime> vtMax = vts.max(Comparator.comparingInt(VisitsAtTime::getNoOfVisits));
        return vtMax;
    }

}
