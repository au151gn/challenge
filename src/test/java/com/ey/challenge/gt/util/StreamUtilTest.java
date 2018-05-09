package com.ey.challenge.gt.util;

import org.junit.Test;

import java.util.stream.Stream;

import static org.junit.Assert.*;

public class StreamUtilTest {

    @Test
    public void testConsumeFirst() {
        Stream<String> stream = Stream.of("A", "B");
        HeadAndTail<String> headAndTail = StreamUtil.consumeFirst(stream);
        assertEquals("A", headAndTail.getHead().get());
        assertEquals(1, headAndTail.getTail().count());
    }

}