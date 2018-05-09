package com.ey.challenge.gt.reader;

import com.ey.challenge.gt.Processor;

import java.util.stream.Stream;

/**
 * A root class for readers. Concrete class should be able to read a named
 * resource/file/etc. and return its content as a stream of lines.
 * @author Paweł Ryszawa
 */
public abstract class Reader implements Processor<Stream<String>, String> {
}
