package com.ey.challenge.gt.parser;

import com.ey.challenge.gt.model.VisitPeriod;
import com.ey.challenge.gt.Converter;
import org.junit.Test;

import static org.junit.Assert.*;

public class TextToVisitConverterImplTest {

    @Test
    public void testConvertBoth2Digits() {
        Converter<VisitPeriod, String> converter = new TextToVisitConverterImpl();
        VisitPeriod vp = converter.convert("11:50,12:36");
        assertEquals(11, vp.getFrom().getHour());
        assertEquals(50, vp.getFrom().getMinute());
        assertEquals(12, vp.getTo().getHour());
        assertEquals(36, vp.getTo().getMinute());
    }

    @Test
    public void testConvertEmpty() {
        Converter<VisitPeriod, String> converter = new TextToVisitConverterImpl();
        VisitPeriod vp = converter.convert("");
        assertEquals(null, vp);
    }

}