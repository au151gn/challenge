package com.ey.challenge.gt.parser;

import com.ey.challenge.gt.Converter;
import com.ey.challenge.gt.model.VisitPeriod;

/**
 * Converts a stream of strings into a stream of visit periods.
 * @author Paweł Ryszawa
 */
public class StreamTextToVisitConverterImpl extends StreamConverter<VisitPeriod, String> {

    private Converter<VisitPeriod, String> converter = new TextToVisitConverterImpl();

    /**
     * {@inheritDoc}
     */
    @Override
    protected Converter<VisitPeriod, String> getConverter() {
        return converter;
    }

}
