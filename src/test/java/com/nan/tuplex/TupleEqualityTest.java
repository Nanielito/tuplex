package com.nan.tuplex;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

/**
 * Defines a contract for testing the equality behavior of tuple data structures.
 * <p>
 * Key behaviors verified by this test contract include:
 * - Equality with the same instance.
 * - Inequality with non-tuple objects.
 * - Inequality with tuples of different sizes.
 * - Equality with other tuples that have the same values.
 * - Hash code consistency between tuples that are considered equal.
 *
 * @param <T> the type of tuple being tested, extending from the {@code Tuple} interface
 */
interface TupleEqualityTest<T extends Tuple> extends TupleTest<T> {

    @Test
    default void shouldBeEqualToSameInstance() {
        T tuple = createTuple();

        assertEquals(tuple, tuple);
    }

    @Test
    default void shouldNotBeEqualToNonTupleObject() {
        T tuple = createTuple();

        assertNotEquals(tuple, "not a tuple");
        assertNotEquals(tuple, 123);
        assertNotEquals(tuple, new Object());
    }

    @Test
    default void shouldNotBeEqualToDifferentTuple() {
        assertNotEquals(createTuple(), createDifferentTuple());
    }

    @Test
    default void shouldNotBeEqualToTupleWithDifferentSize() {
        T tuple = createTuple();
        TupleN tupleN = TupleN.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        assertNotEquals(tuple, tupleN);
        assertNotEquals(tupleN, tuple);
    }

    @Test
    default void shouldBeEqualToAnotherTupleWithSameValues() {
        T tuple1 = createTuple();
        T tuple2 = createTuple();

        assertEquals(tuple1, tuple2);
        assertEquals(tuple2, tuple1);
        assertEquals(tuple1.hashCode(), tuple2.hashCode());
    }

    @Test
    default void shouldBeEqualToTupleNWithSameValues() {
        T tuple = createTuple();
        TupleN tupleN = new TupleN(tuple.toList());

        assertEquals(tuple, tupleN);
        assertEquals(tupleN, tuple);
        assertEquals(tuple.hashCode(), tupleN.hashCode());
    }

    @Test
    default void shouldBeEqualToConcreteTuple() {
        TupleN tupleN = new TupleN(List.of(expectedValues()));
        T tuple = createTuple();

        assertEquals(tupleN, tuple);
        assertEquals(tuple, tupleN);
        assertEquals(tupleN.hashCode(), tuple.hashCode());
    }
}
