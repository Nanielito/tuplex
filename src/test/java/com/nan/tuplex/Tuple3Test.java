package com.nan.tuplex;

import static java.util.function.Function.identity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Unit test class for verifying the behavior, structure, and properties of the {@link Tuple3} data structure.
 * <p>
 * This test class implements multiple testing contracts:
 * - {@link TupleStructureTest} for testing structural properties and behaviors like size, contents, and iteration.
 * - {@link TupleEqualityTest} for verifying equality, hash code correctness, and comparisons with other tuples.
 * - {@link TupleConversionTest} for validating tuple transformations to lists, arrays, and streams while ensuring immutability.
 * - {@link TupleSerializationTest} for ensuring correct serialization and deserialization of tuple instances.
 * <p>
 * The specific implementation for testing the {@link Tuple3} involves:
 * - Verifying constraints, such as null values not being allowed during tuple creation.
 * - Providing concrete test cases for mapping and transforming tuple values.
 */
class Tuple3Test extends TupleBaseTest implements
        TupleStructureTest<Tuple3<?, ?, ?>>,
        TupleEqualityTest<Tuple3<?, ?, ?>>,
        TupleConversionTest<Tuple3<?, ?, ?>>,
        TupleSerializationTest<Tuple3<?, ?, ?>> {

    @Override
    public Tuple3<Integer, String, Boolean> createTuple() {
        return new Tuple3<>(42, "foo", true);
    }

    @Override
    public Tuple3<Integer, String, Boolean> createDifferentTuple() {
        return new Tuple3<>(17, "bar", false);
    }

    @Override
    public int expectedSize() {
        return 3;
    }

    @Override
    public Object[] expectedValues() {
        return new Object[] { 42, "foo", true };
    }

    @Override
    public Object expectedValue() {
        return true;
    }

    @Override
    public Object unexpectedValue() {
        return 3.14;
    }

    @Test
    void shouldNotAllowNullValue() {
        assertThrows(
                NullPointerException.class,
                () -> new Tuple3<>(null, 42, "foo"));

        assertThrows(
                NullPointerException.class,
                () -> new Tuple3<>(42, null, "foo"));

        assertThrows(
                NullPointerException.class,
                () -> new Tuple3<>(42, "foo", null));
    }

    @Test
    void shouldNotMapValuesWhenNullMappers() {
        assertThrows(
                NullPointerException.class,
                () -> createTuple().map(
                        null,
                        identity(),
                        identity()));

        assertThrows(
                NullPointerException.class,
                () -> createTuple().map(
                        identity(),
                        null,
                        identity()));

        assertThrows(
                NullPointerException.class,
                () -> createTuple().map(
                        identity(),
                        identity(),
                        null));

        assertThrows(
                NullPointerException.class,
                () -> createTuple().map1(null));

        assertThrows(
                NullPointerException.class,
                () -> createTuple().map2(null));

        assertThrows(
                NullPointerException.class,
                () -> createTuple().map3(null));
    }

    @Test
    void shouldMapValues() {
        Tuple3<Integer, String, Boolean> tuple = createTuple().map(
                first -> first * 2,
                "%sbar"::formatted,
                third -> !third);

        assertEquals(84, tuple.left());
        assertEquals("foobar", tuple.middle());
        assertEquals(false, tuple.right());
    }

    @Test
    void shouldMapFirstValue() {
        Tuple3<Integer, String, Boolean> tuple = createTuple().map1(value -> value * 2);

        assertEquals(84, tuple.left());
        assertEquals("foo", tuple.middle());
        assertEquals(true, tuple.right());
    }

    @Test
    void shouldMapSecondValue() {
        Tuple3<Integer, String, Boolean> tuple = createTuple().map2("%sbar"::formatted);

        assertEquals(42, tuple.left());
        assertEquals("foobar", tuple.middle());
        assertEquals(true, tuple.right());
    }

    @Test
    void shouldMapThirdValue() {
        Tuple3<Integer, String, Boolean> tuple = createTuple().map3(value -> !value);

        assertEquals(42, tuple.left());
        assertEquals("foo", tuple.middle());
        assertEquals(false, tuple.right());
    }
}
