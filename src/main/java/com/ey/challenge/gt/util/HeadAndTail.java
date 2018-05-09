package com.ey.challenge.gt.util;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * A class representing head element together with a tail as a stream.
 * @param <T> Generic type of the underlying elements.
 * @author Paweł Ryszawa
 */
public class HeadAndTail<T> {

    private Optional<T> head;

    private Stream<T> tail;

    /**
     * Constructor.
     * @param head Head element.
     * @param tail Tail stream.
     */
    public HeadAndTail(Optional<T> head, Stream<T> tail) {
        this.head = head;
        this.tail = tail;
    }

    /**
     * Returns the head.
     *
     * @return Head value.
     */
    public Optional<T> getHead() {
        return head;
    }

    /**
     * Getter for property 'tail'.
     *
     * @return Value for property 'tail'.
     */
    public Stream<T> getTail() {
        return tail;
    }

    /**
     * Returns true if this represents an empty head-and-tail.
     * @return true, if empty
     */
    public boolean isEmpty() {
        return !head.isPresent();
    }

}
