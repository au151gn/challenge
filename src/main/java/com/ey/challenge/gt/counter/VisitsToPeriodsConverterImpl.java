package com.ey.challenge.gt.counter;

import com.ey.challenge.gt.Converter;
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
public class VisitsToPeriodsConverterImpl implements Converter<Stream<VisitsWithinPeriod>, Stream<VisitsAtTime>> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Stream<VisitsWithinPeriod> convert(Stream<VisitsAtTime> src) {
        return convert(Optional.empty(), src);
    }

    private Stream<VisitsWithinPeriod> convert(Optional<VisitsAtTime> lastVt, Stream<VisitsAtTime> src) {
        HeadAndTail<VisitsAtTime> headAndTail = StreamUtil.consumeFirst(src);
        if (!lastVt.isPresent()) {
            return convert(headAndTail.getHead(), headAndTail.getTail());
        } else if (headAndTail.isEmpty()) {
            return Stream.of();
        } else {
            return Stream.concat(
                    Stream.of(new VisitsWithinPeriod(
                            lastVt.get().getTime(),
                            headAndTail.getHead().get().getTime().minusMinutes(1),
                            lastVt.get().getNoOfVisits()
                    )),
                    convert(headAndTail.getHead(), headAndTail.getTail())
            );
        }
    }

}
