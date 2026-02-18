package com.nan.tuplex;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Provides a contract for testing structural properties and behaviors of tuple data structures.
 *
 * @param <T> the type of tuple being tested, extending from the {@code Tuple} interface
 */
interface TupleStructureTest<T extends Tuple> extends TupleTest<T> {

    @Test
    default void shouldFailWhenGettingValuesOutOfBounds() {
        assertThrows(
                IndexOutOfBoundsException.class,
                () -> createTuple().get(0));

        assertThrows(
                IndexOutOfBoundsException.class,
                () -> createTuple().get(expectedSize() + 1));
    }

    @Test
    default void shouldMatchExpectedSize() {
        assertEquals(expectedSize(), createTuple().size());
    }

    @Test
    default void shouldReturnCorrectValuesWhenGettingValue() {
        Tuple tuple = createTuple();
        Object[] expected = expectedValues();

        for (int i = 1; i < expected.length + 1; i++)
            assertEquals(expected[i - 1], tuple.get(i));
    }

    @Test
    default void shouldContainExpectedValue() {
        assertTrue(createTuple().contains(expectedValue()));
    }

    @Test
    default void shouldNotContainUnexpectedValue() {
        assertFalse(createTuple().contains(unexpectedValue()));
    }

    @Test
    default void shouldNotContainNullValue() {
        assertFalse(createTuple().contains(null));
    }

    @Test
    default void shouldBeIterable() {
        T tuple = createTuple();
        List<Object> iterated = new ArrayList<>();

        for (Object value : tuple)
            iterated.add(value);

        assertEquals(List.of(expectedValues()), iterated);
    }

    @Test
    default void shouldIterateAndRespecOrder() {
        Iterator<Object> iterator = createTuple().iterator();

        for (Object expected : expectedValues()) {
            assertTrue(iterator.hasNext());
            assertEquals(expected, iterator.next());
        }

        assertFalse(iterator.hasNext());
    }
}
