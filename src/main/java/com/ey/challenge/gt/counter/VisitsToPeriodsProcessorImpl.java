package com.ey.challenge.gt.counter;

import com.ey.challenge.gt.Processor;
import com.ey.challenge.gt.model.VisitsAtTime;
import com.ey.challenge.gt.model.VisitsWithinPeriod;
import com.ey.challenge.gt.util.HeadAndTail;
import com.ey.challenge.gt.util.StreamUtil;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * This class converts a stream of number of visits paireds with the starting point in time
 * into a stream of those numbers paired with corresponding periods.
 * @author Paweł Ryszawa
 */
public class VisitsToPeriodsProcessorImpl implements Processor<Stream<VisitsWithinPeriod>, Stream<VisitsAtTime>> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Stream<VisitsWithinPeriod> process(Stream<VisitsAtTime> src) {
        return process(Optional.empty(), src);
    }

    private Stream<VisitsWithinPeriod> process(Optional<VisitsAtTime> lastVt, Stream<VisitsAtTime> src) {
        HeadAndTail<VisitsAtTime> headAndTail = StreamUtil.consumeFirst(src);
        if (!lastVt.isPresent()) {
            return process(headAndTail.getHead(), headAndTail.getTail());
        } else if (headAndTail.isEmpty()) {
            return Stream.of();
        } else {
            return Stream.concat(
                    Stream.of(new VisitsWithinPeriod(
                            lastVt.get().getTime(),
                            headAndTail.getHead().get().getTime().minusMinutes(1),
                            lastVt.get().getNoOfVisits()
                    )),
                    process(headAndTail.getHead(), headAndTail.getTail())
            );
        }
    }

}
