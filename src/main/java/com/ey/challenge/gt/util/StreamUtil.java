package com.ey.challenge.gt.util;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A helper class to manipulate streams.
 * @author Paweł Ryszawa
 */
public final class StreamUtil {

    private StreamUtil() { } // This is a helper class and should not be instantiated.

    /**
     * This method retrieves the head and the tail of a stream. Since this is not Java native method,
     * a Scala-like one was implemented here.
     * @param stream Stream to peek the first element from.
     * @param <T> A generic type of the underlying elements.
     * @return A pair of the first element, if existing (head), and a stream with the rest of elements (tail).
     */
    public static <T> HeadAndTail<T> consumeFirst(Stream<T> stream) {
        List<T> lst = stream.collect(Collectors.toList());
        Optional<T> head = lst.size() > 0 ? Optional.of(lst.remove(0)) : Optional.empty();
        return new HeadAndTail<>(head, lst.stream());
    }

}
