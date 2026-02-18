package com.nan.tuplex;

import static java.util.function.Function.identity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Unit test class for verifying the behavior, structure, and properties of the {@link Tuple5} data structure.
 * <p>
 * This test class implements multiple testing contracts:
 * - {@link TupleStructureTest} for testing structural properties and behaviors like size, contents, and iteration.
 * - {@link TupleEqualityTest} for verifying equality, hash code correctness, and comparisons with other tuples.
 * - {@link TupleConversionTest} for validating tuple transformations to lists, arrays, and streams while ensuring immutability.
 * - {@link TupleSerializationTest} for ensuring correct serialization and deserialization of tuple instances.
 * <p>
 * The specific implementation for testing the {@link Tuple5} involves:
 * - Verifying constraints, such as null values not being allowed during tuple creation.
 * - Providing concrete test cases for mapping and transforming tuple values.
 */
class Tuple5Test extends TupleBaseTest implements
        TupleStructureTest<Tuple5<?, ?, ?, ?, ?>>,
        TupleEqualityTest<Tuple5<?, ?, ?, ?, ?>>,
        TupleConversionTest<Tuple5<?, ?, ?, ?, ?>>,
        TupleSerializationTest<Tuple5<?, ?, ?, ?, ?>> {

    @Override
    public Tuple5<Integer, String, Boolean, Double, Character> createTuple() {
        return new Tuple5<>(42, "foo", true, 3.14, 'c');
    }

    @Override
    public Tuple5<Integer, String, Boolean, Double, Character> createDifferentTuple() {
        return new Tuple5<>(17, "bar", false, 2.71, 'x');
    }

    @Override
    public int expectedSize() {
        return 5;
    }

    @Override
    public Object[] expectedValues() {
        return new Object[] { 42, "foo", true, 3.14, 'c' };
    }

    @Override
    public Object expectedValue() {
        return 'c';
    }

    @Override
    public Object unexpectedValue() {
        return 10_000L;
    }

    @Test
    void shouldNotAllowNullValue() {
        assertThrows(
                NullPointerException.class,
                () -> new Tuple5<>(null, 42, "foo", true, 3.14));

        assertThrows(
                NullPointerException.class,
                () -> new Tuple5<>(42, null, "foo", true, 3.14));

        assertThrows(
                NullPointerException.class,
                () -> new Tuple5<>(42, "foo", null, true, 3.14));

        assertThrows(
                NullPointerException.class,
                () -> new Tuple5<>(42, "foo", true, null, 3.14));

        assertThrows(
                NullPointerException.class,
                () -> new Tuple5<>(42, "foo", true, 3.14, null));
    }

    @Test
    void shouldNotMapValuesWhenNullMappers() {
        assertThrows(
                NullPointerException.class,
                () -> createTuple().map(
                        null,
                        identity(),
                        identity(),
                        identity(),
                        identity()));

        assertThrows(
                NullPointerException.class,
                () -> createTuple().map(
                        identity(),
                        null,
                        identity(),
                        identity(),
                        identity()));

        assertThrows(
                NullPointerException.class,
                () -> createTuple().map(
                        identity(),
                        identity(),
                        null,
                        identity(),
                        identity()));

        assertThrows(
                NullPointerException.class,
                () -> createTuple().map(
                        identity(),
                        identity(),
                        identity(),
                        null,
                        identity()));

        assertThrows(
                NullPointerException.class,
                () -> createTuple().map(
                        identity(),
                        identity(),
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

        assertThrows(
                NullPointerException.class,
                () -> createTuple().map4(null));

        assertThrows(
                NullPointerException.class,
                () -> createTuple().map5(null));
    }

    @Test
    void shouldMapValues() {
        Tuple5<Integer, String, Boolean, Double, Character> tuple = createTuple().map(
                first -> first * 2,
                "%sbar"::formatted,
                third -> !third,
                fourth -> fourth + 0.86,
                _ -> 'd');

        assertEquals(84, tuple._1());
        assertEquals("foobar", tuple._2());
        assertEquals(false, tuple._3());
        assertEquals(4, tuple._4());
        assertEquals('d', tuple._5());
    }

    @Test
    void shouldMapFirstValue() {
        Tuple5<Integer, String, Boolean, Double, Character> tuple = createTuple().map1(value -> value * 2);

        assertEquals(84, tuple._1());
        assertEquals("foo", tuple._2());
        assertEquals(true, tuple._3());
        assertEquals(3.14, tuple._4());
        assertEquals('c', tuple._5());
    }

    @Test
    void shouldMapSecondValue() {
        Tuple5<Integer, String, Boolean, Double, Character> tuple = createTuple().map2("%sbar"::formatted);

        assertEquals(42, tuple._1());
        assertEquals("foobar", tuple._2());
        assertEquals(true, tuple._3());
        assertEquals(3.14, tuple._4());
        assertEquals('c', tuple._5());
    }

    @Test
    void shouldMapThirdValue() {
        Tuple5<Integer, String, Boolean, Double, Character> tuple = createTuple().map3(value -> !value);

        assertEquals(42, tuple._1());
        assertEquals("foo", tuple._2());
        assertEquals(false, tuple._3());
        assertEquals(3.14, tuple._4());
        assertEquals('c', tuple._5());
    }

    @Test
    void shouldMapFourthValue() {
        Tuple5<Integer, String, Boolean, Double, Character> tuple = createTuple().map4(value -> value + 0.86);

        assertEquals(42, tuple._1());
        assertEquals("foo", tuple._2());
        assertEquals(true, tuple._3());
        assertEquals(4.0, tuple._4());
        assertEquals('c', tuple._5());
    }

    @Test
    void shouldMapFifthValue() {
        Tuple5<Integer, String, Boolean, Double, Character> tuple = createTuple().map5(_ -> 'd');

        assertEquals(42, tuple._1());
        assertEquals("foo", tuple._2());
        assertEquals(true, tuple._3());
        assertEquals(3.14, tuple._4());
        assertEquals('d', tuple._5());
    }
}
