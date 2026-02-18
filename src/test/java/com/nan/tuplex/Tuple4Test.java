package com.nan.tuplex;

import static java.util.function.Function.identity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Unit test class for verifying the behavior, structure, and properties of the {@link Tuple4} data structure.
 * <p>
 * This test class implements multiple testing contracts:
 * - {@link TupleStructureTest} for testing structural properties and behaviors like size, contents, and iteration.
 * - {@link TupleEqualityTest} for verifying equality, hash code correctness, and comparisons with other tuples.
 * - {@link TupleConversionTest} for validating tuple transformations to lists, arrays, and streams while ensuring immutability.
 * - {@link TupleSerializationTest} for ensuring correct serialization and deserialization of tuple instances.
 * <p>
 * The specific implementation for testing the {@link Tuple4} involves:
 * - Verifying constraints, such as null values not being allowed during tuple creation.
 * - Providing concrete test cases for mapping and transforming tuple values.
 */
class Tuple4Test extends TupleBaseTest implements
        TupleStructureTest<Tuple4<?, ?, ?, ?>>,
        TupleEqualityTest<Tuple4<?, ?, ?, ?>>,
        TupleConversionTest<Tuple4<?, ?, ?, ?>>,
        TupleSerializationTest<Tuple4<?, ?, ?, ?>> {

    @Override
    public Tuple4<Integer, String, Boolean, Double> createTuple() {
        return new Tuple4<>(42, "foo", true, 3.14);
    }

    @Override
    public Tuple4<Integer, String, Boolean, Double> createDifferentTuple() {
        return new Tuple4<>(17, "bar", false, 2.71);
    }

    @Override
    public int expectedSize() {
        return 4;
    }

    @Override
    public Object[] expectedValues() {
        return new Object[] { 42, "foo", true, 3.14 };
    }

    @Override
    public Object expectedValue() {
        return 3.14;
    }

    @Override
    public Object unexpectedValue() {
        return 'c';
    }

    @Test
    void shouldNotAllowNullValue() {
        assertThrows(
                NullPointerException.class,
                () -> new Tuple4<>(null, 42, "foo", true));

        assertThrows(
                NullPointerException.class,
                () -> new Tuple4<>(42, null, "foo", true));

        assertThrows(
                NullPointerException.class,
                () -> new Tuple4<>(42, "foo", null, true));

        assertThrows(
                NullPointerException.class,
                () -> new Tuple4<>(42, "foo", true, null));
    }

    @Test
    void shouldNotMapValuesWhenNullMappers() {
        assertThrows(
                NullPointerException.class,
                () -> createTuple().map(
                        null,
                        identity(),
                        identity(),
                        identity()));

        assertThrows(
                NullPointerException.class,
                () -> createTuple().map(
                        identity(),
                        null,
                        identity(),
                        identity()));

        assertThrows(
                NullPointerException.class,
                () -> createTuple().map(
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
    }

    @Test
    void shouldMapValues() {
        Tuple4<Integer, String, Boolean, Double> tuple = createTuple().map(
                first -> first * 2,
                "%sbar"::formatted,
                third -> !third,
                fourth -> fourth + 0.86);

        assertEquals(84, tuple._1());
        assertEquals("foobar", tuple._2());
        assertEquals(false, tuple._3());
        assertEquals(4, tuple._4());
    }

    @Test
    void shouldMapFirstValue() {
        Tuple4<Integer, String, Boolean, Double> tuple = createTuple().map1(value -> value * 2);

        assertEquals(84, tuple._1());
        assertEquals("foo", tuple._2());
        assertEquals(true, tuple._3());
        assertEquals(3.14, tuple._4());
    }

    @Test
    void shouldMapSecondValue() {
        Tuple4<Integer, String, Boolean, Double> tuple = createTuple().map2("%sbar"::formatted);

        assertEquals(42, tuple._1());
        assertEquals("foobar", tuple._2());
        assertEquals(true, tuple._3());
        assertEquals(3.14, tuple._4());
    }

    @Test
    void shouldMapThirdValue() {
        Tuple4<Integer, String, Boolean, Double> tuple = createTuple().map3(value -> !value);

        assertEquals(42, tuple._1());
        assertEquals("foo", tuple._2());
        assertEquals(false, tuple._3());
        assertEquals(3.14, tuple._4());
    }

    @Test
    void shouldMapFourthValue() {
        Tuple4<Integer, String, Boolean, Double> tuple = createTuple().map4(value -> value + 0.86);

        assertEquals(42, tuple._1());
        assertEquals("foo", tuple._2());
        assertEquals(true, tuple._3());
        assertEquals(4.0, tuple._4());
    }
}
