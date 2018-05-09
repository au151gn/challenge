package com.ey.challenge.gt.counter;

import com.ey.challenge.gt.Processor;
import com.ey.challenge.gt.model.VisitPeriod;
import com.ey.challenge.gt.model.VisitsAtTime;
import com.ey.challenge.gt.model.VisitsWithinPeriod;

import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Finds the period with the highest number of visits.
 * @author Paweł Ryszawa
 */
public class MaxVisitFinder implements Processor<Optional<VisitsWithinPeriod>, Stream<VisitPeriod>> {

    private Processor<Stream<VisitsAtTime>, Stream<VisitPeriod>> counter = new VisitsCounter();

    private Processor<Stream<VisitsWithinPeriod>, Stream<VisitsAtTime>> converter = new VisitsToPeriodsProcessorImpl();

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<VisitsWithinPeriod> process(Stream<VisitPeriod> src) {
        Stream<VisitsAtTime> vts = counter.process(src);
        Stream<VisitsWithinPeriod> vps = converter.process(vts);
        Optional<VisitsWithinPeriod> vpMax = vps.max(Comparator.comparingInt(VisitsWithinPeriod::getNoOfVisits));
        return vpMax;
    }

}
