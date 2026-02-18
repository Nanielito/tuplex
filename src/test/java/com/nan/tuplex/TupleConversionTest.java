package com.nan.tuplex;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Defines a suite of tests for verifying the correctness and behavior of tuple transformations
 * into different data structures while preserving order and immutability.
 *
 * @param <T> the type of tuple being tested, extending from the {@code Tuple} interface
 */
interface TupleConversionTest<T extends Tuple> extends TupleTest<T> {

    @Test
    default void shouldPreserverOrderWhenTransformingToList() {
        List<Object> list = createTuple().toList();
        assertEquals(List.of(expectedValues()), list);
        assertThrows(
                UnsupportedOperationException.class,
                () -> list.add("boom"));
    }

    @Test
    default void shouldPreserveOrderWhenTransformingToArray() {
        assertArrayEquals(expectedValues(), createTuple().toArray());
    }

    @Test
    default void shouldNotExposeInternalArray() {
        T tuple = createTuple();
        Object[] array = tuple.toArray();;

        array[0] = unexpectedValue();

        assertEquals(expectedValues()[0], tuple.get(1));
    }

    @Test
    default void shouldPreserveOrderWhenTransformingToStream() {
        assertArrayEquals(expectedValues(), createTuple().stream().toArray());
    }
}
