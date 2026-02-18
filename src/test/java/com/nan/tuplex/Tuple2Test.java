package com.nan.tuplex;

import static java.util.function.Function.identity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Unit test class for verifying the behavior, structure, and properties of the {@link Tuple2} data structure.
 * <p>
 * This test class implements multiple testing contracts:
 * - {@link TupleStructureTest} for testing structural properties and behaviors like size, contents, and iteration.
 * - {@link TupleEqualityTest} for verifying equality, hash code correctness, and comparisons with other tuples.
 * - {@link TupleConversionTest} for validating tuple transformations to lists, arrays, and streams while ensuring immutability.
 * - {@link TupleSerializationTest} for ensuring correct serialization and deserialization of tuple instances.
 * <p>
 * The specific implementation for testing the {@link Tuple2} involves:
 * - Verifying constraints, such as null values not being allowed during tuple creation.
 * - Providing concrete test cases for mapping and transforming tuple values.
 */
class Tuple2Test extends TupleBaseTest implements
        TupleStructureTest<Tuple2<?, ?>>,
        TupleEqualityTest<Tuple2<?, ?>>,
        TupleConversionTest<Tuple2<?, ?>>,
        TupleSerializationTest<Tuple2<?, ?>> {

    @Override
    public Tuple2<Integer, String> createTuple() {
        return new Tuple2<>(42, "foo");
    }

    @Override
    public Tuple2<Integer, String> createDifferentTuple() {
        return new Tuple2<>(17, "bar");
    }

    @Override
    public int expectedSize() {
        return 2;
    }

    @Override
    public Object[] expectedValues() {
        return new Object[] { 42, "foo" };
    }

    @Override
    public Object expectedValue() {
        return "foo";
    }

    @Override
    public Object unexpectedValue() {
        return true;
    }

    @Test
    void shouldNotAllowNullValue() {
        assertThrows(
                NullPointerException.class,
                () -> new Tuple2<>(null, 42));

        assertThrows(
                NullPointerException.class,
                () -> new Tuple2<>(42, null));
    }

    @Test
    void shouldNotMapValuesWhenNullMappers() {
        assertThrows(
                NullPointerException.class,
                () -> createTuple().map(
                        null,
                        identity()));

        assertThrows(
                NullPointerException.class,
                () -> createTuple().map(
                        identity(),
                        null));

        assertThrows(
                NullPointerException.class,
                () -> createTuple().map1(null));

        assertThrows(
                NullPointerException.class,
                () -> createTuple().map2(null));
    }

    @Test
    void shouldMapValues() {
        Tuple2<Integer, String> tuple = createTuple().map(
                first -> first * 2,
                "%sbar"::formatted);

        assertEquals(84, tuple.left());
        assertEquals("foobar", tuple.right());
    }

    @Test
    void shouldMapFirstValue() {
        Tuple2<Integer, String> tuple = createTuple().map1(value -> value * 2);

        assertEquals(84, tuple.left());
        assertEquals("foo", tuple.right());
    }

    @Test
    void shouldMapSecondValue() {
        Tuple2<Integer, String> tuple = createTuple().map2("%sbar"::formatted);

        assertEquals(42, tuple.left());
        assertEquals("foobar", tuple.right());
    }
}
