package com.ey.challenge.gt.reader;

/**
 * This class opens a given file, reads its content and returns a stream of visit periods.
 * @author Paweł Ryszawa
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
