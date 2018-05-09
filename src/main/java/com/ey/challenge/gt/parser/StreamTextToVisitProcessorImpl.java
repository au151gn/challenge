package com.ey.challenge.gt.parser;

import com.ey.challenge.gt.Processor;
import com.ey.challenge.gt.model.VisitPeriod;

/**
 * Converts a stream of strings into a stream of visit periods.
 * @author Paweł Ryszawa
 */
public class StreamTextToVisitProcessorImpl extends StreamProcessor<VisitPeriod, String> {

    private Processor<VisitPeriod, String> processor = new TextToVisitProcessorImpl();

    /**
     * {@inheritDoc}
     */
    @Override
    protected Processor<VisitPeriod, String> getProcessor() {
        return processor;
    }

}
