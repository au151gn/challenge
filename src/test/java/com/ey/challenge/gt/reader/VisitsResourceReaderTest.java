package com.ey.challenge.gt.reader;

import com.ey.challenge.gt.model.VisitPeriod;
import org.junit.Test;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class VisitsResourceReaderTest {

    @Test
    public void testReadResource() {
        VisitsResourceReader reader = new VisitsResourceReader();
        Stream<VisitPeriod> stream = reader.process("com/ey/challenge/gt/visiting-times.txt");
        List<VisitPeriod> lst = stream.collect(Collectors.toList());
        assertEquals(239, lst.size());
        assertEquals(new VisitPeriod(LocalTime.of(13, 00), LocalTime.of(13, 05)), lst.get(0));
    }

}