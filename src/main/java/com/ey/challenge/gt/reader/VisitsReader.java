package com.ey.challenge.gt.reader;

import com.ey.challenge.gt.Converter;
import com.ey.challenge.gt.model.VisitPeriod;
import com.ey.challenge.gt.parser.StreamConverter;
import com.ey.challenge.gt.parser.StreamTextToVisitConverterImpl;

import java.util.stream.Stream;

/**
 * This is an abstract class of readers that convert the name of a resource/file/etc. into
 * a stream of significant (i.e. non-null) visit periods.
 * @author Paweł Ryszawa
 */
public abstract class VisitsReader implements Converter<Stream<VisitPeriod>, String> {

    private StreamConverter<VisitPeriod, String> streamConverter = new StreamTextToVisitConverterImpl();

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
    public Stream<VisitPeriod> convert(String src) {
        Stream<String> rawData = getReader().convert(src);
        return streamConverter.convert(rawData).filter(vp -> vp != null);
    }

}
