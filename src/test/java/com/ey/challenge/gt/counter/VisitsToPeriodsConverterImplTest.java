package com.ey.challenge.gt.counter;

import com.ey.challenge.gt.model.VisitsAtTime;
import com.ey.challenge.gt.model.VisitsWithinPeriod;
import org.junit.Test;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class VisitsToPeriodsConverterImplTest {

    @Test
    public void testThreePeriods() {
        VisitsToPeriodsConverterImpl converter = new VisitsToPeriodsConverterImpl();
        Stream<VisitsAtTime> src = Stream.of(
                new VisitsAtTime(LocalTime.of(11, 35), 1),
                new VisitsAtTime(LocalTime.of(12, 30), 2),
                new VisitsAtTime(LocalTime.of(13, 19), 1),
                new VisitsAtTime(LocalTime.of(14, 01), 0)
        );
        List<VisitsWithinPeriod> dst = converter.convert(src).collect(Collectors.toList());
        assertEquals(3, dst.size());
        assertEquals(
                new VisitsWithinPeriod(LocalTime.of(11, 35), LocalTime.of(12, 29), 1),
                dst.get(0)
        );
        assertEquals(
                new VisitsWithinPeriod(LocalTime.of(12, 30), LocalTime.of(13, 18), 2),
                dst.get(1)
        );
        assertEquals(
                new VisitsWithinPeriod(LocalTime.of(13, 19), LocalTime.of(14, 00), 1),
                dst.get(2)
        );
    }

    @Test
    public void testNoPeriods() {
        VisitsToPeriodsConverterImpl converter = new VisitsToPeriodsConverterImpl();
        Stream<VisitsAtTime> src = Stream.of(
                new VisitsAtTime(LocalTime.of(14, 01), 0)
        );
        List<VisitsWithinPeriod> dst = converter.convert(src).collect(Collectors.toList());
        assertEquals(0, dst.size());
    }

}