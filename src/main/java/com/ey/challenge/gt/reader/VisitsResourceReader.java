package com.ey.challenge.gt.reader;

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
