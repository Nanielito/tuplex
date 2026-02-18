package com.nan.tuplex;

import static java.util.function.Function.identity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Unit test class for verifying the behavior, structure, and properties of the {@link Tuple8} data structure.
 * <p>
 * This test class implements multiple testing contracts:
 * - {@link TupleStructureTest} for testing structural properties and behaviors like size, contents, and iteration.
 * - {@link TupleEqualityTest} for verifying equality, hash code correctness, and comparisons with other tuples.
 * - {@link TupleConversionTest} for validating tuple transformations to lists, arrays, and streams while ensuring immutability.
 * - {@link TupleSerializationTest} for ensuring correct serialization and deserialization of tuple instances.
 * <p>
 * The specific implementation for testing the {@link Tuple8} involves:
 * - Verifying constraints, such as null values not being allowed during tuple creation.
 * - Providing concrete test cases for mapping and transforming tuple values.
 */
class Tuple8Test extends TupleBaseTest implements
        TupleStructureTest<Tuple8<?, ?, ?, ?, ?, ?, ?, ?>>,
        TupleEqualityTest<Tuple8<?, ?, ?, ?, ?, ?, ?, ?>>,
        TupleConversionTest<Tuple8<?, ?, ?, ?, ?, ?, ?, ?>>,
        TupleSerializationTest<Tuple8<?, ?, ?, ?, ?, ?, ?, ?>> {

    private final TestValue testValue = new TestValue("qwerty");

    @Override
    public Tuple8<Integer, String, Boolean, Double, Character, Long, Float, Integer> createTuple() {
        return new Tuple8<>(42, "foo", true, 3.14, 'c', 10_000L, 1.0f, 0x0A);
    }

    @Override
    public Tuple8<Integer, String, Boolean, Double, Character, Long, Float, Integer> createDifferentTuple() {
        return new Tuple8<>(17, "bar", false, 2.71, 'y', 100_000L, 9.0f, 0x64);
    }

    @Override
    public int expectedSize() {
        return 8;
    }

    @Override
    public Object[] expectedValues() {
        return new Object[] { 42, "foo", true, 3.14, 'c', 10_000L, 1.0f, 0x0A };
    }

    @Override
    public Object expectedValue() {
        return 0x0A;
    }

    @Override
    public Object unexpectedValue() {
        return testValue;
    }

    @Test
    void shouldNotAllowNullValue() {
        assertThrows(
                NullPointerException.class,
                () -> new Tuple8<>(null, 42, "foo", true, 3.14, 'c', 10_000L, 1.0f));

        assertThrows(
                NullPointerException.class,
                () -> new Tuple8<>(42, null, "foo", true, 3.14, 'c', 10_000L, 1.0f));

        assertThrows(
                NullPointerException.class,
                () -> new Tuple8<>(42, "foo", null, true, 3.14, 'c', 10_000L, 1.0f));

        assertThrows(
                NullPointerException.class,
                () -> new Tuple8<>(42, "foo", true, null, 3.14, 'c', 10_000L, 1.0f));

        assertThrows(
                NullPointerException.class,
                () -> new Tuple8<>(42, "foo", true, 3.14, null, 'c', 10_000L, 1.0f));

        assertThrows(
                NullPointerException.class,
                () -> new Tuple8<>(42, "foo", true, 3.14, 'c', null, 10_000L, 1.0f));

        assertThrows(
                NullPointerException.class,
                () -> new Tuple8<>(42, "foo", true, 3.14, 'c', 10_000L, null, 1.0f));

        assertThrows(
                NullPointerException.class,
                () -> new Tuple8<>(42, "foo", true, 3.14, 'c', 10_000L, 1.0f, null));
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

        assertThrows(
                NullPointerException.class,
                () -> createTuple().map8(null));
    }

    @Test
    void shouldMapValues() {
        Tuple8<Integer, String, Boolean, Double, Character, Long, Float, Integer> tuple = createTuple().map(
                first -> first * 2,
                "%sbar"::formatted,
                third -> !third,
                fourth -> fourth + 0.86,
                fifth -> 'd',
                sixth -> sixth / 2,
                seventh -> seventh - 1.0f,
                eighth -> 0x64);

        assertEquals(84, tuple._1());
        assertEquals("foobar", tuple._2());
        assertEquals(false, tuple._3());
        assertEquals(4, tuple._4());
        assertEquals('d', tuple._5());
        assertEquals(5_000L, tuple._6());
        assertEquals(0.0f, tuple._7());
        assertEquals(0x64, tuple._8());
    }

    @Test
    void shouldMapFirstValue() {
        Tuple8<Integer, String, Boolean, Double, Character, Long, Float, Integer> tuple = createTuple().map1(value -> value * 2);

        assertEquals(84, tuple._1());
        assertEquals("foo", tuple._2());
        assertEquals(true, tuple._3());
        assertEquals(3.14, tuple._4());
        assertEquals('c', tuple._5());
        assertEquals(10_000L, tuple._6());
        assertEquals(1.0f, tuple._7());
        assertEquals(0x0A, tuple._8());
    }

    @Test
    void shouldMapSecondValue() {
        Tuple8<Integer, String, Boolean, Double, Character, Long, Float, Integer> tuple = createTuple().map2("%sbar"::formatted);

        assertEquals(42, tuple._1());
        assertEquals("foobar", tuple._2());
        assertEquals(true, tuple._3());
        assertEquals(3.14, tuple._4());
        assertEquals('c', tuple._5());
        assertEquals(10_000L, tuple._6());
        assertEquals(1.0f, tuple._7());
        assertEquals(0x0A, tuple._8());
    }

    @Test
    void shouldMapThirdValue() {
        Tuple8<Integer, String, Boolean, Double, Character, Long, Float, Integer> tuple = createTuple().map3(value -> !value);

        assertEquals(42, tuple._1());
        assertEquals("foo", tuple._2());
        assertEquals(false, tuple._3());
        assertEquals(3.14, tuple._4());
        assertEquals('c', tuple._5());
        assertEquals(10_000L, tuple._6());
        assertEquals(1.0f, tuple._7());
        assertEquals(0x0A, tuple._8());
    }

    @Test
    void shouldMapFourthValue() {
        Tuple8<Integer, String, Boolean, Double, Character, Long, Float, Integer> tuple = createTuple().map4(value -> value + 0.86);

        assertEquals(42, tuple._1());
        assertEquals("foo", tuple._2());
        assertEquals(true, tuple._3());
        assertEquals(4.0, tuple._4());
        assertEquals('c', tuple._5());
        assertEquals(10_000L, tuple._6());
        assertEquals(1.0f, tuple._7());
        assertEquals(0x0A, tuple._8());
    }

    @Test
    void shouldMapFifthValue() {
        Tuple8<Integer, String, Boolean, Double, Character, Long, Float, Integer> tuple = createTuple().map5(value -> 'd');

        assertEquals(42, tuple._1());
        assertEquals("foo", tuple._2());
        assertEquals(true, tuple._3());
        assertEquals(3.14, tuple._4());
        assertEquals('d', tuple._5());
        assertEquals(10_000L, tuple._6());
        assertEquals(1.0f, tuple._7());
        assertEquals(0x0A, tuple._8());
    }

    @Test
    void shouldMapSixthValue() {
        Tuple8<Integer, String, Boolean, Double, Character, Long, Float, Integer> tuple = createTuple().map6(value -> value / 2);

        assertEquals(42, tuple._1());
        assertEquals("foo", tuple._2());
        assertEquals(true, tuple._3());
        assertEquals(3.14, tuple._4());
        assertEquals('c', tuple._5());
        assertEquals(5_000L, tuple._6());
        assertEquals(1.0f, tuple._7());
        assertEquals(0x0A, tuple._8());
    }

    @Test
    void shouldMapSeventhValue() {
        Tuple8<Integer, String, Boolean, Double, Character, Long, Float, Integer> tuple = createTuple().map7(value -> value - 1.0f);

        assertEquals(42, tuple._1());
        assertEquals("foo", tuple._2());
        assertEquals(true, tuple._3());
        assertEquals(3.14, tuple._4());
        assertEquals('c', tuple._5());
        assertEquals(10_000L, tuple._6());
        assertEquals(0.0f, tuple._7());
        assertEquals(0x0A, tuple._8());
    }

    @Test
    void shouldMapEighthValue() {
        Tuple8<Integer, String, Boolean, Double, Character, Long, Float, Integer> tuple = createTuple().map8(value -> 0x64);

        assertEquals(42, tuple._1());
        assertEquals("foo", tuple._2());
        assertEquals(true, tuple._3());
        assertEquals(3.14, tuple._4());
        assertEquals('c', tuple._5());
        assertEquals(10_000L, tuple._6());
        assertEquals(1.0f, tuple._7());
        assertEquals(0x64, tuple._8());
    }
}
