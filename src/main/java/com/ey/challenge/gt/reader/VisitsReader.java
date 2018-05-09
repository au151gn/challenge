package com.ey.challenge.gt.reader;

import com.ey.challenge.gt.Processor;
import com.ey.challenge.gt.model.VisitPeriod;
import com.ey.challenge.gt.parser.StreamProcessor;
import com.ey.challenge.gt.parser.StreamTextToVisitProcessorImpl;

import java.util.stream.Stream;

/**
 * This is an abstract class of readers that process the name of a resource/file/etc. into
 * a stream of significant (i.e. non-null) visit periods.
 * @author Paweł Ryszawa
 */
public abstract class VisitsReader implements Processor<Stream<VisitPeriod>, String> {

    private StreamProcessor<VisitPeriod, String> streamProcessor = new StreamTextToVisitProcessorImpl();

    /**
     * The concrete class should return here a reader that gets the name of a resource/file/etc.
     * converting it into a stream of text lines (strings).
     * @return A concrete reader.
     */
    protected abstract Reader getReader();

    /**
     * {@inheritDoc}
     */
    @Override
    public Stream<VisitPeriod> process(String src) {
        Stream<String> rawData = getReader().process(src);
        return streamProcessor.process(rawData).filter(vp -> vp != null);
    }

}
