package com.ey.challenge.gt.counter;

import com.ey.challenge.gt.util.HeadAndTail;
import com.ey.challenge.gt.model.VisitPeriod;
import com.ey.challenge.gt.model.VisitsAtTime;
import com.ey.challenge.gt.Converter;
import com.ey.challenge.gt.util.StreamUtil;

import java.time.LocalTime;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * This class traces visit periods and produces the numbers of concurrent visits at
 * the significant points of time.
 * @author Paweł Ryszawa
 */
public class VisitsCounter implements Converter<Stream<VisitsAtTime>, Stream<VisitPeriod>> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Stream<VisitsAtTime> convert(Stream<VisitPeriod> src) {
        return convert(Stream.of(), src);
    }

    private Stream<VisitsAtTime> convert(Stream<VisitsAtTime> dst, Stream<VisitPeriod> src) {
        HeadAndTail<VisitPeriod> headAndTail = StreamUtil.consumeFirst(src);
        if (headAndTail.isEmpty()) {
            return dst; // This is the end of recursion.
        }
        VisitPeriod vp = headAndTail.getHead().get();
        Stream<VisitsAtTime> extDst = insertTime(insertTime(dst, vp.getFrom(), 0), vp.getTo().plusMinutes(1), 0);
        Stream<VisitsAtTime> newDst = extDst.map(
                vt -> (vt.getTime().isBefore(vp.getFrom()) || vt.getTime().isAfter(vp.getTo()))
                        ? vt
                        : new VisitsAtTime(vt.getTime(), vt.getNoOfVisits() + 1)
        );
        Stream<VisitPeriod> tail = headAndTail.getTail();
        return convert(compact(newDst), tail);
    }

    private Stream<VisitsAtTime> insertTime(Stream<VisitsAtTime> stream, LocalTime time, int lastNoOfVisits) {
        HeadAndTail<VisitsAtTime> headAndTail = StreamUtil.consumeFirst(stream);
        if (headAndTail.isEmpty()) {
            return Stream.of(new VisitsAtTime(time, lastNoOfVisits)); // Stream is empty
        }
        VisitsAtTime vt = headAndTail.getHead().get();
        Stream<VisitsAtTime> tail = headAndTail.getTail();
        if (vt.getTime().equals(time)) {
            return Stream.concat(Stream.of(vt), tail);
        } else if (vt.getTime().isBefore(time)) {
            return Stream.concat(Stream.of(vt), insertTime(tail, time, vt.getNoOfVisits()));
        } else {
            return Stream.concat(Stream.of(new VisitsAtTime(time, lastNoOfVisits), vt), tail);
        }
    }

    private Stream<VisitsAtTime> compact(Stream<VisitsAtTime> src) {
        return compact(Optional.empty(), src);
    }

    private Stream<VisitsAtTime> compact(Optional<VisitsAtTime> lastVt, Stream<VisitsAtTime> src) {
        HeadAndTail<VisitsAtTime> headAndTail = StreamUtil.consumeFirst(src);
        if (!lastVt.isPresent()) {
            return compact(headAndTail.getHead(), headAndTail.getTail());
        } else {
            if (headAndTail.isEmpty()) {
                return Stream.of(lastVt.get());
            } else if (lastVt.get().getNoOfVisits() == headAndTail.getHead().get().getNoOfVisits()) {
                return compact(lastVt, headAndTail.getTail());
            } else {
                return Stream.concat(
                        Stream.of(lastVt.get()),
                        compact(headAndTail.getHead(), headAndTail.getTail())
                );
            }
        }
    }

}
