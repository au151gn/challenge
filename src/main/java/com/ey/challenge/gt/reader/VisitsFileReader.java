package com.ey.challenge.gt.reader;

import com.ey.challenge.gt.model.VisitPeriod;
import com.ey.challenge.gt.Converter;
import com.ey.challenge.gt.parser.StreamConverter;
import com.ey.challenge.gt.parser.StreamTextToVisitConverterImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * This class opens a given file, reads its content and returns a stream of visit periods.
 */
public class VisitsFileReader extends VisitsReader {

    private Reader reader = new FileReader();

    /**
     * {@inheritDoc}
     */
    @Override
    protected Reader getReader() {
        return reader;
    }

}
