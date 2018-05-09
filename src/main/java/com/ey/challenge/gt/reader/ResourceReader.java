package com.ey.challenge.gt.reader;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Stream;

/**
 * A class that converts a resource name into a stream of lines.
 * @author Paweł Ryszawa
 */
public class ResourceReader extends Reader {

    /**
     * {@inheritDoc}
     */
    @Override
    public Stream<String> convert(String src) {
        InputStream inp = ClassLoader.getSystemResourceAsStream(src);
        BufferedReader br = new BufferedReader(new InputStreamReader(inp));
        return br.lines();
    }

}
