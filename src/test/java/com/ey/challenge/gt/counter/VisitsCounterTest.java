package com.ey.challenge.gt.counter;

import com.ey.challenge.gt.util.HeadAndTail;
import com.ey.challenge.gt.model.VisitPeriod;
import com.ey.challenge.gt.model.VisitsAtTime;
import com.ey.challenge.gt.util.StreamUtil;
import org.junit.Test;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class VisitsCounterTest {

    @Test
    public void testOneVisitor() {
        VisitsCounter counter = new VisitsCounter();
        Stream<VisitPeriod> src = Stream.of(new VisitPeriod(LocalTime.of(11,35), LocalTime.of(12,01)));
        Stream<VisitsAtTime> dst = counter.process(src);
        HeadAndTail<VisitsAtTime> headAndTail = StreamUtil.consumeFirst(dst);
        assertTrue(headAndTail.getHead().isPresent());
        assertEquals(new VisitsAtTime(LocalTime.of(11, 35), 1), headAndTail.getHead().get());
        HeadAndTail<VisitsAtTime> headAndTail2 = StreamUtil.consumeFirst(headAndTail.getTail());
        assertTrue(headAndTail2.getHead().isPresent());
        assertEquals(new VisitsAtTime(LocalTime.of(12, 02), 0), headAndTail2.getHead().get());
        assertEquals(0, headAndTail2.getTail().count());
    }

    @Test
    public void testTwoVisitors() {
        VisitsCounter counter = new VisitsCounter();
        Stream<VisitPeriod> src = Stream.of(
                new VisitPeriod(LocalTime.of(12,30), LocalTime.of(13,59)),
                new VisitPeriod(LocalTime.of(11,35), LocalTime.of(12,01))
        );
        List<VisitsAtTime> dst = counter.process(src).collect(Collectors.toList());
        assertEquals(4, dst.size());
        assertEquals(new VisitsAtTime(LocalTime.of(11, 35), 1), dst.get(0));
        assertEquals(new VisitsAtTime(LocalTime.of(12, 02), 0), dst.get(1));
        assertEquals(new VisitsAtTime(LocalTime.of(12, 30), 1), dst.get(2));
        assertEquals(new VisitsAtTime(LocalTime.of(14, 00), 0), dst.get(3));
    }

    @Test
    public void testTwoCrossVisitors() {
        VisitsCounter counter = new VisitsCounter();
        Stream<VisitPeriod> src = Stream.of(
                new VisitPeriod(LocalTime.of(11,35), LocalTime.of(13,59)),
                new VisitPeriod(LocalTime.of(12,01), LocalTime.of(12,30))
        );
        List<VisitsAtTime> dst = counter.process(src).collect(Collectors.toList());
        assertEquals(4, dst.size());
        assertEquals(new VisitsAtTime(LocalTime.of(11, 35), 1), dst.get(0));
        assertEquals(new VisitsAtTime(LocalTime.of(12, 01), 2), dst.get(1));
        assertEquals(new VisitsAtTime(LocalTime.of(12, 31), 1), dst.get(2));
        assertEquals(new VisitsAtTime(LocalTime.of(14, 00), 0), dst.get(3));
    }

    @Test
    public void testTwoConsecutiveVisitors() {
        VisitsCounter counter = new VisitsCounter();
        Stream<VisitPeriod> src = Stream.of(
                new VisitPeriod(LocalTime.of(11,00), LocalTime.of(11,59)),
                new VisitPeriod(LocalTime.of(12,00), LocalTime.of(12,59))
        );
        List<VisitsAtTime> dst = counter.process(src).collect(Collectors.toList());
        assertEquals(2, dst.size());
        assertEquals(new VisitsAtTime(LocalTime.of(11, 00), 1), dst.get(0));
        assertEquals(new VisitsAtTime(LocalTime.of(13, 00), 0), dst.get(1));
    }

    @Test
    public void testTwoNonConsecutiveVisitors() {
        VisitsCounter counter = new VisitsCounter();
        Stream<VisitPeriod> src = Stream.of(
                new VisitPeriod(LocalTime.of(11,00), LocalTime.of(11,59)),
                new VisitPeriod(LocalTime.of(12,01), LocalTime.of(12,59))
        );
        List<VisitsAtTime> dst = counter.process(src).collect(Collectors.toList());
        assertEquals(4, dst.size());
        assertEquals(new VisitsAtTime(LocalTime.of(11, 00), 1), dst.get(0));
        assertEquals(new VisitsAtTime(LocalTime.of(12, 00), 0), dst.get(1));
        assertEquals(new VisitsAtTime(LocalTime.of(12, 01), 1), dst.get(2));
        assertEquals(new VisitsAtTime(LocalTime.of(13, 00), 0), dst.get(3));
    }

    @Test
    public void testThreeVisitors() {
        VisitsCounter counter = new VisitsCounter();
        Stream<VisitPeriod> src = Stream.of(
                new VisitPeriod(LocalTime.of(11,35), LocalTime.of(13,59)),
                new VisitPeriod(LocalTime.of(12,00), LocalTime.of(12,30)),
                new VisitPeriod(LocalTime.of(12,31), LocalTime.of(13,00))
        );
        List<VisitsAtTime> dst = counter.process(src).collect(Collectors.toList());
        assertEquals(4, dst.size());
        assertEquals(new VisitsAtTime(LocalTime.of(11, 35), 1), dst.get(0));
        assertEquals(new VisitsAtTime(LocalTime.of(12, 00), 2), dst.get(1));
        assertEquals(new VisitsAtTime(LocalTime.of(13, 01), 1), dst.get(2));
        assertEquals(new VisitsAtTime(LocalTime.of(14, 00), 0), dst.get(3));
    }

    @Test
    public void testTwoOverlapped() {
        VisitsCounter counter = new VisitsCounter();
        Stream<VisitPeriod> src = Stream.of(
                new VisitPeriod(LocalTime.of(11,00), LocalTime.of(11,30)),
                new VisitPeriod(LocalTime.of(11,30), LocalTime.of(11,59))
        );
        List<VisitsAtTime> dst = counter.process(src).collect(Collectors.toList());
        assertEquals(4, dst.size());
        assertEquals(new VisitsAtTime(LocalTime.of(11, 00), 1), dst.get(0));
        assertEquals(new VisitsAtTime(LocalTime.of(11, 30), 2), dst.get(1));
        assertEquals(new VisitsAtTime(LocalTime.of(11, 31), 1), dst.get(2));
        assertEquals(new VisitsAtTime(LocalTime.of(12, 00), 0), dst.get(3));
    }

    @Test
    public void testTwoParallel() {
        VisitsCounter counter = new VisitsCounter();
        Stream<VisitPeriod> src = Stream.of(
                new VisitPeriod(LocalTime.of(11,00), LocalTime.of(11,30)),
                new VisitPeriod(LocalTime.of(11,00), LocalTime.of(11,30))
        );
        List<VisitsAtTime> dst = counter.process(src).collect(Collectors.toList());
        assertEquals(2, dst.size());
        assertEquals(new VisitsAtTime(LocalTime.of(11, 00), 2), dst.get(0));
        assertEquals(new VisitsAtTime(LocalTime.of(11, 31), 0), dst.get(1));
    }

    @Test
    public void testTwoStartingParallel() {
        VisitsCounter counter = new VisitsCounter();
        Stream<VisitPeriod> src = Stream.of(
                new VisitPeriod(LocalTime.of(11,00), LocalTime.of(11,30)),
                new VisitPeriod(LocalTime.of(11,00), LocalTime.of(12,00))
        );
        List<VisitsAtTime> dst = counter.process(src).collect(Collectors.toList());
        assertEquals(3, dst.size());
        assertEquals(new VisitsAtTime(LocalTime.of(11, 00), 2), dst.get(0));
        assertEquals(new VisitsAtTime(LocalTime.of(11, 31), 1), dst.get(1));
        assertEquals(new VisitsAtTime(LocalTime.of(12, 01), 0), dst.get(2));
    }

    @Test
    public void testTwoEndingParallel() {
        VisitsCounter counter = new VisitsCounter();
        Stream<VisitPeriod> src = Stream.of(
                new VisitPeriod(LocalTime.of(11,00), LocalTime.of(12,00)),
                new VisitPeriod(LocalTime.of(11,30), LocalTime.of(12,00))
        );
        List<VisitsAtTime> dst = counter.process(src).collect(Collectors.toList());
        assertEquals(3, dst.size());
        assertEquals(new VisitsAtTime(LocalTime.of(11, 00), 1), dst.get(0));
        assertEquals(new VisitsAtTime(LocalTime.of(11, 30), 2), dst.get(1));
        assertEquals(new VisitsAtTime(LocalTime.of(12, 01), 0), dst.get(2));
    }

}