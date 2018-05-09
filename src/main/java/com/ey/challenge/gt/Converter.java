package com.ey.challenge.gt;

public interface Converter<D, S> {

    /**
     * Converts src of generic type S into an object of generic type D.
     * @param src An object of generic type S.
     * @return An object of generic type D.
     */
    D convert(S src);

}
