package com.ey.challenge.gt.reader;

/**
 * An exception thrown on file reading error.
 * @author Paweł Ryszawa
 */
public class ReaderException extends RuntimeException {

    /**
     * Constructor.
     * @param message A message describing the problem.
     */
    public ReaderException(String message) {
        super(message);
    }
}
