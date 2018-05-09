package com.ey.challenge.gt.reader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * This class transforms a file path into a stream of raw data.
 * @author Paweł Ryszawa
 */
public class FileReader extends Reader {

    /**
     * {@inheritDoc}
     */
    @Override
    public Stream<String> process(String fileName) {
        Path path = Paths.get(fileName);
        if (!Files.exists(path)) {
            throw new ReaderException("File not found: " + path);
        }
        try {
            return Files.lines(path);
        } catch (IOException ex) {
            throw new ReaderException("Error reading file: " + path);
        }
    }

}
