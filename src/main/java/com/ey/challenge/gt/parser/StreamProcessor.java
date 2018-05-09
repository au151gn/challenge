package com.ey.challenge.gt.parser;

import com.ey.challenge.gt.Processor;

import java.util.stream.Stream;

/**
 * The abstract class of a converter that converts one stream into another, i.e. it converts a stream of source generic
 * type items into a stream of destination generic type items.
 * @param <D> Destination generic type.
 * @param <S> Source generic type.
 * @author Paweł Ryszawa
 */
public abstract class StreamProcessor<D, S> implements Processor<Stream<D>, Stream<S>> {

    protected abstract Processor<D, S> getProcessor();

    /**
     * {@inheritDoc}
     */
    @Override
    public Stream<D> process(Stream<S> src) {
        final Processor<D, S> processor = getProcessor();
        return src.map(item -> processor.process(item));
    }

}
