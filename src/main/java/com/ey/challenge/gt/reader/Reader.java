package com.ey.challenge.gt.reader;

import com.ey.challenge.gt.Converter;

import java.util.stream.Stream;

/**
 * A root class for readers. Concrete class should be able to read a named
 * resource/file/etc. and return its content as a stream of lines.
 */
public abstract class Reader implements Converter<Stream<String>, String> {
}
