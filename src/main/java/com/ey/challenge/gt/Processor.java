package com.ey.challenge.gt;

/**
 * A general interface for classes transforming its input into some output.
 * @param <D> Output generic type.
 * @param <S> Input generic type.
 */
public interface Processor<D, S> {

    /**
     * Converts src of generic type S into an object of generic type D.
     * @param src An object of generic type S.
     * @return An object of generic type D.
     */
    D process(S src);

}
