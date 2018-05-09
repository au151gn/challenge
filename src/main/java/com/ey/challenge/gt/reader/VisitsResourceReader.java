package com.ey.challenge.gt.reader;

/**
 * A concrete class that reads the data from resources.
 * @author Paweł Ryszawa
 */
public class VisitsResourceReader extends VisitsReader {

    private Reader reader = new ResourceReader();

    /**
     * {@inheritDoc}
     */
    @Override
    protected Reader getReader() {
        return reader;
    }
}
