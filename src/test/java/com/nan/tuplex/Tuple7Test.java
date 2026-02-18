package com.nan.tuplex;

import static java.util.function.Function.identity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Unit test class for verifying the behavior, structure, and properties of the {@link Tuple7} data structure.
 * <p>
 * This test class implements multiple testing contracts:
 * - {@link TupleStructureTest} for testing structural properties and behaviors like size, contents, and iteration.
 * - {@link TupleEqualityTest} for verifying equality, hash code correctness, and comparisons with other tuples.
 * - {@link TupleConversionTest} for validating tuple transformations to lists, arrays, and streams while ensuring immutability.
 * - {@link TupleSerializationTest} for ensuring correct serialization and deserialization of tuple instances.
 * <p>
 * The specific implementation for testing the {@link Tuple7} involves:
 * - Verifying constraints, such as null values not being allowed during tuple creation.
 * - Providing concrete test cases for mapping and transforming tuple values.
 */
class Tuple7Test extends TupleBaseTest implements
        TupleStructureTest<Tuple7<?, ?, ?, ?, ?, ?, ?>>,
        TupleEqualityTest<Tuple7<?, ?, ?, ?, ?, ?, ?>>,
        TupleConversionTest<Tuple7<?, ?, ?, ?, ?, ?, ?>>,
        TupleSerializationTest<Tuple7<?, ?, ?, ?, ?, ?, ?>> {

    @Override
    public Tuple7<Integer, String, Boolean, Double, Character, Long, Float> createTuple() {
        return new Tuple7<>(42, "foo", true, 3.14, 'c', 10_000L, 1.0f);
    }

    @Override
    public Tuple7<Integer, String, Boolean, Double, Character, Long, Float> createDifferentTuple() {
        return new Tuple7<>(17, "bar", false, 2.71, 'y', 100_000L, 9.0f);
    }

    @Override
    public int expectedSize() {
        return 7;
    }

    @Override
    public Object[] expectedValues() {
        return new Object[] { 42, "foo", true, 3.14, 'c', 10_000L, 1.0f };
    }

    @Override
    public Object expectedValue() {
        return 1.0f;
    }

    @Override
    public Object unexpectedValue() {
        return 0x0A;
    }

    @Test
    void shouldNotAllowNullValue() {
        assertThrows(
                NullPointerException.class,
                () -> new Tuple7<>(null, 42, "foo", true, 3.14, 'c', 10_000L));

        assertThrows(
                NullPointerException.class,
                () -> new Tuple7<>(42, null, "foo", true, 3.14, 'c', 10_000L));

        assertThrows(
                NullPointerException.class,
                () -> new Tuple7<>(42, "foo", null, true, 3.14, 'c', 10_000L));

        assertThrows(
                NullPointerException.class,
                () -> new Tuple7<>(42, "foo", true, null, 3.14, 'c', 10_000L));

        assertThrows(
                NullPointerException.class,
                () -> new Tuple7<>(42, "foo", true, 3.14, null, 'c', 10_000L));

        assertThrows(
                NullPointerException.class,
                () -> new Tuple7<>(42, "foo", true, 3.14, 'c', null, 10_000L));

        assertThrows(
                NullPointerException.class,
                () -> new Tuple7<>(42, "foo", true, 3.14, 'c', 10_000L, null));
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
                        identity(),
                        identity()));

        assertThrows(
                NullPointerException.class,
                () -> createTuple().map(
                        identity(),
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

        assertThrows(
                NullPointerException.class,
                () -> createTuple().map7(null));
    }

    @Test
    void shouldMapValues() {
        Tuple7<Integer, String, Boolean, Double, Character, Long, Float> tuple = createTuple().map(
                first -> first * 2,
                "%sbar"::formatted,
                third -> !third,
                fourth -> fourth + 0.86,
                _ -> 'd',
                sixth -> sixth / 2,
                seventh -> seventh - 1.0f);

        assertEquals(84, tuple._1());
        assertEquals("foobar", tuple._2());
        assertEquals(false, tuple._3());
        assertEquals(4, tuple._4());
        assertEquals('d', tuple._5());
        assertEquals(5_000L, tuple._6());
        assertEquals(0.0f, tuple._7());
    }

    @Test
    void shouldMapFirstValue() {
        Tuple7<Integer, String, Boolean, Double, Character, Long, Float> tuple = createTuple().map1(value -> value * 2);

        assertEquals(84, tuple._1());
        assertEquals("foo", tuple._2());
        assertEquals(true, tuple._3());
        assertEquals(3.14, tuple._4());
        assertEquals('c', tuple._5());
        assertEquals(10_000L, tuple._6());
        assertEquals(1.0f, tuple._7());
    }

    @Test
    void shouldMapSecondValue() {
        Tuple7<Integer, String, Boolean, Double, Character, Long, Float> tuple = createTuple().map2("%sbar"::formatted);

        assertEquals(42, tuple._1());
        assertEquals("foobar", tuple._2());
        assertEquals(true, tuple._3());
        assertEquals(3.14, tuple._4());
        assertEquals('c', tuple._5());
        assertEquals(10_000L, tuple._6());
        assertEquals(1.0f, tuple._7());
    }

    @Test
    void shouldMapThirdValue() {
        Tuple7<Integer, String, Boolean, Double, Character, Long, Float> tuple = createTuple().map3(value -> !value);

        assertEquals(42, tuple._1());
        assertEquals("foo", tuple._2());
        assertEquals(false, tuple._3());
        assertEquals(3.14, tuple._4());
        assertEquals('c', tuple._5());
        assertEquals(10_000L, tuple._6());
        assertEquals(1.0f, tuple._7());
    }

    @Test
    void shouldMapFourthValue() {
        Tuple7<Integer, String, Boolean, Double, Character, Long, Float> tuple = createTuple().map4(value -> value + 0.86);

        assertEquals(42, tuple._1());
        assertEquals("foo", tuple._2());
        assertEquals(true, tuple._3());
        assertEquals(4.0, tuple._4());
        assertEquals('c', tuple._5());
        assertEquals(10_000L, tuple._6());
        assertEquals(1.0f, tuple._7());
    }

    @Test
    void shouldMapFifthValue() {
        Tuple7<Integer, String, Boolean, Double, Character, Long, Float> tuple = createTuple().map5(_ -> 'd');

        assertEquals(42, tuple._1());
        assertEquals("foo", tuple._2());
        assertEquals(true, tuple._3());
        assertEquals(3.14, tuple._4());
        assertEquals('d', tuple._5());
        assertEquals(10_000L, tuple._6());
        assertEquals(1.0f, tuple._7());
    }

    @Test
    void shouldMapSixthValue() {
        Tuple7<Integer, String, Boolean, Double, Character, Long, Float> tuple = createTuple().map6(value -> value / 2);

        assertEquals(42, tuple._1());
        assertEquals("foo", tuple._2());
        assertEquals(true, tuple._3());
        assertEquals(3.14, tuple._4());
        assertEquals('c', tuple._5());
        assertEquals(5_000L, tuple._6());
        assertEquals(1.0f, tuple._7());
    }

    @Test
    void shouldMapSeventhValue() {
        Tuple7<Integer, String, Boolean, Double, Character, Long, Float> tuple = createTuple().map7(value -> value - 1.0f);

        assertEquals(42, tuple._1());
        assertEquals("foo", tuple._2());
        assertEquals(true, tuple._3());
        assertEquals(3.14, tuple._4());
        assertEquals('c', tuple._5());
        assertEquals(10_000L, tuple._6());
        assertEquals(0.0f, tuple._7());
    }
}
