package com.nan.tuplex;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Unit test class for verifying the behavior, structure, and properties of the {@link Tuple1} data structure.
 * <p>
 * This test class implements multiple testing contracts:
 * - {@link TupleStructureTest} for testing structural properties and behaviors like size, contents, and iteration.
 * - {@link TupleEqualityTest} for verifying equality, hash code correctness, and comparisons with other tuples.
 * - {@link TupleConversionTest} for validating tuple transformations to lists, arrays, and streams while ensuring immutability.
 * - {@link TupleSerializationTest} for ensuring correct serialization and deserialization of tuple instances.
 * <p>
 * The specific implementation for testing the {@link Tuple1} involves:
 * - Verifying constraints, such as null values not being allowed during tuple creation.
 * - Providing concrete test cases for mapping and transforming tuple values.
 */
class Tuple1Test extends TupleBaseTest implements
        TupleStructureTest<Tuple1<?>>,
        TupleEqualityTest<Tuple1<?>>,
        TupleConversionTest<Tuple1<?>>,
        TupleSerializationTest<Tuple1<?>> {

    @Override
    public Tuple1<Integer> createTuple() {
        return new Tuple1<>(42);
    }

    @Override
    public Tuple1<Integer> createDifferentTuple() {
        return new Tuple1<>(17);
    }

    @Override
    public int expectedSize() {
        return 1;
    }

    @Override
    public Object[] expectedValues() {
        return new Object[] { 42 };
    }

    @Override
    public Object expectedValue() {
        return 42;
    }

    @Override
    public Object unexpectedValue() {
        return "foo";
    }

    @Test
    void shouldNotAllowNullValue() {
        assertThrows(
                NullPointerException.class,
                () -> new Tuple1<>(null));
    }

    @Test
    void shouldNotMapValuesWhenNullMapper() {
        assertThrows(
                NullPointerException.class,
                () -> createTuple().map(null));
    }

    @Test
    void shouldMapValue() {
        Tuple1<Integer> tuple = createTuple().map(value -> value * 2);

        assertEquals(84, tuple.get());
    }
}
