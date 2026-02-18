package com.nan.tuplex;

import static java.util.function.Function.identity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Unit test class for verifying the behavior, structure, and properties of the {@link Tuple6} data structure.
 * <p>
 * This test class implements multiple testing contracts:
 * - {@link TupleStructureTest} for testing structural properties and behaviors like size, contents, and iteration.
 * - {@link TupleEqualityTest} for verifying equality, hash code correctness, and comparisons with other tuples.
 * - {@link TupleConversionTest} for validating tuple transformations to lists, arrays, and streams while ensuring immutability.
 * - {@link TupleSerializationTest} for ensuring correct serialization and deserialization of tuple instances.
 * <p>
 * The specific implementation for testing the {@link Tuple6} involves:
 * - Verifying constraints, such as null values not being allowed during tuple creation.
 * - Providing concrete test cases for mapping and transforming tuple values.
 */
class Tuple6Test extends TupleBaseTest implements
        TupleStructureTest<Tuple6<?, ?, ?, ?, ?, ?>>,
        TupleEqualityTest<Tuple6<?, ?, ?, ?, ?, ?>>,
        TupleConversionTest<Tuple6<?, ?, ?, ?, ?, ?>>,
        TupleSerializationTest<Tuple6<?, ?, ?, ?, ?, ?>> {

    @Override
    public Tuple6<Integer, String, Boolean, Double, Character, Long> createTuple() {
        return new Tuple6<>(42, "foo", true, 3.14, 'c', 10_000L);
    }

    @Override
    public Tuple6<Integer, String, Boolean, Double, Character, Long> createDifferentTuple() {
        return new Tuple6<>(17, "bar", false, 2.71, 'y', 100_000L);
    }

    @Override
    public int expectedSize() {
        return 6;
    }

    @Override
    public Object[] expectedValues() {
        return new Object[] { 42, "foo", true, 3.14, 'c', 10_000L };
    }

    @Override
    public Object expectedValue() {
        return 10_000L;
    }

    @Override
    public Object unexpectedValue() {
        return 1.0f;
    }

    @Test
    void shouldNotAllowNullValue() {
        assertThrows(
                NullPointerException.class,
                () -> new Tuple6<>(null, 42, "foo", true, 3.14, 'c'));

        assertThrows(
                NullPointerException.class,
                () -> new Tuple6<>(42, null, "foo", true, 3.14, 'c'));

        assertThrows(
                NullPointerException.class,
                () -> new Tuple6<>(42, "foo", null, true, 3.14, 'c'));

        assertThrows(
                NullPointerException.class,
                () -> new Tuple6<>(42, "foo", true, null, 3.14, 'c'));

        assertThrows(
                NullPointerException.class,
                () -> new Tuple6<>(42, "foo", true, 3.14, null, 'c'));

        assertThrows(
                NullPointerException.class,
                () -> new Tuple6<>(42, "foo", true, 3.14, 'c', null));
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
                        identity(),
                        identity()));

        assertThrows(
                NullPointerException.class,
                () -> createTuple().map(
                        identity(),
                        null,
                        identity(),
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
                        identity(),
                        identity()));

        assertThrows(
                NullPointerException.class,
                () -> createTuple().map(
                        identity(),
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

        assertThrows(
                NullPointerException.class,
                () -> createTuple().map6(null));
    }

    @Test
    void shouldMapValues() {
        Tuple6<Integer, String, Boolean, Double, Character, Long> tuple = createTuple().map(
                first -> first * 2,
                "%sbar"::formatted,
                third -> !third,
                fourth -> fourth + 0.86,
                fifth -> 'd',
                sixth -> sixth / 2);

        assertEquals(84, tuple._1());
        assertEquals("foobar", tuple._2());
        assertEquals(false, tuple._3());
        assertEquals(4, tuple._4());
        assertEquals('d', tuple._5());
        assertEquals(5_000L, tuple._6());
    }

    @Test
    void shouldMapFirstValue() {
        Tuple6<Integer, String, Boolean, Double, Character, Long> tuple = createTuple().map1(value -> value * 2);

        assertEquals(84, tuple._1());
        assertEquals("foo", tuple._2());
        assertEquals(true, tuple._3());
        assertEquals(3.14, tuple._4());
        assertEquals('c', tuple._5());
        assertEquals(10_000L, tuple._6());
    }

    @Test
    void shouldMapSecondValue() {
        Tuple6<Integer, String, Boolean, Double, Character, Long> tuple = createTuple().map2("%sbar"::formatted);

        assertEquals(42, tuple._1());
        assertEquals("foobar", tuple._2());
        assertEquals(true, tuple._3());
        assertEquals(3.14, tuple._4());
        assertEquals('c', tuple._5());
        assertEquals(10_000L, tuple._6());
    }

    @Test
    void shouldMapThirdValue() {
        Tuple6<Integer, String, Boolean, Double, Character, Long> tuple = createTuple().map3(value -> !value);

        assertEquals(42, tuple._1());
        assertEquals("foo", tuple._2());
        assertEquals(false, tuple._3());
        assertEquals(3.14, tuple._4());
        assertEquals('c', tuple._5());
        assertEquals(10_000L, tuple._6());
    }

    @Test
    void shouldMapFourthValue() {
        Tuple6<Integer, String, Boolean, Double, Character, Long> tuple = createTuple().map4(value -> value + 0.86);

        assertEquals(42, tuple._1());
        assertEquals("foo", tuple._2());
        assertEquals(true, tuple._3());
        assertEquals(4.0, tuple._4());
        assertEquals('c', tuple._5());
        assertEquals(10_000L, tuple._6());
    }

    @Test
    void shouldMapFifthValue() {
        Tuple6<Integer, String, Boolean, Double, Character, Long> tuple = createTuple().map5(value -> 'd');

        assertEquals(42, tuple._1());
        assertEquals("foo", tuple._2());
        assertEquals(true, tuple._3());
        assertEquals(3.14, tuple._4());
        assertEquals('d', tuple._5());
        assertEquals(10_000L, tuple._6());
    }

    @Test
    void shouldMapSixthValue() {
        Tuple6<Integer, String, Boolean, Double, Character, Long> tuple = createTuple().map6(value -> value / 2);

        assertEquals(42, tuple._1());
        assertEquals("foo", tuple._2());
        assertEquals(true, tuple._3());
        assertEquals(3.14, tuple._4());
        assertEquals('c', tuple._5());
        assertEquals(5_000L, tuple._6());
    }
}
