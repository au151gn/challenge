package com.ey.challenge.gt.counter;

import com.ey.challenge.gt.Processor;
import com.ey.challenge.gt.model.VisitPeriod;
import com.ey.challenge.gt.model.VisitsAtTime;

import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Finds the point of time with the highest number of visits.
 * @author Paweł Ryszawa
 */
public class MaxVisitFinder implements Processor<Optional<VisitsAtTime>, Stream<VisitPeriod>> {

    private Processor<Stream<VisitsAtTime>, Stream<VisitPeriod>> counter = new VisitsCounter();

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<VisitsAtTime> process(Stream<VisitPeriod> src) {
        Stream<VisitsAtTime> vts = counter.process(src);
        Optional<VisitsAtTime> vtMax = vts.max(Comparator.comparingInt(VisitsAtTime::getNoOfVisits));
        return vtMax;
    }

}
