package com.ey.challenge.gt.parser;

/**
 * An exception throw on parsing errors.
 * @author Paweł Ryszawa
 */
public class ParserException extends RuntimeException {

    /**
     * Constructor.
     * @param message A message describing the exception cause.
     */
    public ParserException(String message) {
        super(message);
    }

}
