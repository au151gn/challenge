package com.ey.challenge.gt.parser;

import com.ey.challenge.gt.model.VisitPeriod;
import com.ey.challenge.gt.Processor;
import org.junit.Test;

import static org.junit.Assert.*;

public class TextToVisitProcessorImplTest {

    @Test
    public void testConvertBoth2Digits() {
        Processor<VisitPeriod, String> processor = new TextToVisitProcessorImpl();
        VisitPeriod vp = processor.process("11:50,12:36");
        assertEquals(11, vp.getFrom().getHour());
        assertEquals(50, vp.getFrom().getMinute());
        assertEquals(12, vp.getTo().getHour());
        assertEquals(36, vp.getTo().getMinute());
    }

    @Test
    public void testConvertEmpty() {
        Processor<VisitPeriod, String> processor = new TextToVisitProcessorImpl();
        VisitPeriod vp = processor.process("");
        assertEquals(null, vp);
    }

}