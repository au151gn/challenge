package com.ey.challenge.gt.parser;

import com.ey.challenge.gt.Converter;

import java.util.stream.Stream;

/**
 * The abstract class of a converter that converts one stream into another, i.e. it converts a stream of source generic
 * type items into a stream of destination generic type items.
 * @param <D> Destination generic type.
 * @param <S> Source generic type.
 * @author Paweł Ryszawa
 */
public abstract class StreamConverter<D, S> implements Converter<Stream<D>, Stream<S>> {

    protected abstract Converter<D, S> getConverter();

    @Override
    public Stream<D> convert(Stream<S> src) {
        final Converter<D, S> converter = getConverter();
        return src.map(item -> converter.convert(item));
    }

}
