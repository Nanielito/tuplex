package com.nan.tuplex;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Unit test class for verifying the behavior, structure, and properties of the {@link TupleN} data structure.
 * <p>
 * This test class implements multiple testing contracts, including:
 * - {@link TupleStructureTest} for testing structural properties and behaviors like size, contents, and iteration.
 * - {@link TupleEqualityTest} for verifying equality, hash code correctness, and comparisons with other tuples.
 * - {@link TupleConversionTest} for validating tuple transformations to lists, arrays, and streams while ensuring immutability.
 * - {@link TupleSerializationTest} for ensuring correct serialization and deserialization of tuple instances.
 * <p>
 * The specific implementation for testing the {@link TupleN} involves:
 * - Verifying constraints, such as null values not being allowed during tuple creation.
 */
class TupleNTest extends TupleBaseTest implements
        TupleStructureTest<TupleN>,
        TupleEqualityTest<TupleN>,
        TupleConversionTest<TupleN>,
        TupleSerializationTest<TupleN> {

    private final TestValue testValue = new TestValue("qwerty");

    private final TestValue anotherTestValue = new TestValue("azerty");

    @Override
    public TupleN createTuple() {
        return new TupleN(List.of(42, "foo", true, 3.14, 'c', 10_000L, 1.0f, 0x0A, testValue));
    }

    @Override
    public TupleN createDifferentTuple() {
        return new TupleN(List.of(17, "bar", false, 2.71, 'y', 100_000L, 9.0f, 0x64, anotherTestValue));
    }

    @Override
    public int expectedSize() {
        return 9;
    }

    @Override
    public Object[] expectedValues() {
        return new Object[] { 42, "foo", true, 3.14, 'c', 10_000L, 1.0f, 0x0A, testValue };
    }

    @Override
    public Object expectedValue() {
        return testValue;
    }

    @Override
    public Object unexpectedValue() {
        return 17;
    }

    @Test
    void shouldNotAllowNullValue() {
        assertThrows(
                NullPointerException.class,
                () -> new TupleN(null));

        assertThrows(
                IllegalArgumentException.class,
                () -> new TupleN(List.of()));

        assertThrows(
                NullPointerException.class,
                () -> new TupleN(List.of(null, 42, "foo", true, 3.14, 'c', 10_000L, 1.0f, 0x0A)));

        assertThrows(
                NullPointerException.class,
                () -> new TupleN(List.of(42, null, "foo", true, 3.14, 'c', 10_000L, 1.0f, 0x0A)));

        assertThrows(
                NullPointerException.class,
                () -> new TupleN(List.of(42, "foo", null, true, 3.14, 'c', 10_000L, 1.0f, 0x0A)));

        assertThrows(
                NullPointerException.class,
                () -> new TupleN(List.of(42, "foo", true, null, 3.14, 'c', 10_000L, 1.0f, 0x0A)));

        assertThrows(
                NullPointerException.class,
                () -> new TupleN(List.of(42, "foo", true, 3.14, null, 'c', 10_000L, 1.0f, 0x0A)));

        assertThrows(
                NullPointerException.class,
                () -> new TupleN(List.of(42, "foo", true, 3.14, 'c', null, 10_000L, 1.0f, 0x0A)));

        assertThrows(
                NullPointerException.class,
                () -> new TupleN(List.of(42, "foo", true, 3.14, 'c', 10_000L, null, 1.0f, 0x0A)));

        assertThrows(
                NullPointerException.class,
                () -> new TupleN(List.of(42, "foo", true, 3.14, 'c', 10_000L, 1.0f, null, 0x0A)));

        assertThrows(
                NullPointerException.class,
                () -> new TupleN(List.of(42, "foo", true, 3.14, 'c', 10_000L, 1.0f, 0x0A, null)));
    }

    @Test
    void shouldNotBeEqualToTupleWithSameSizeButDifferentValues() {
        Object[] tnValues = Arrays.copyOf(expectedValues(), 8);
        Object[] tValues = Arrays.copyOf(createDifferentTuple().toArray(), 8);
        Tuple tupleN8 = new TupleN(List.of(tnValues));
        Tuple tuple8 = new Tuple8<>(tValues[0], tValues[1], tValues[2], tValues[3], tValues[4], tValues[5], tValues[6], tValues[7]);
        Tuple tupleN7 = new TupleN(List.of(tnValues[0], tnValues[1], tnValues[2], tnValues[3], tnValues[4], tnValues[5], tnValues[6]));
        Tuple tuple7 = new Tuple7<>(tValues[0], tValues[1], tValues[2], tValues[3], tValues[4], tValues[5], tValues[6]);
        Tuple tupleN6 = new TupleN(List.of(tnValues[0], tnValues[1], tnValues[2], tnValues[3], tnValues[4], tnValues[5]));
        Tuple tuple6 = new Tuple6<>(tValues[0], tValues[1], tValues[2], tValues[3], tValues[4], tValues[5]);
        Tuple tupleN5 = new TupleN(List.of(tnValues[0], tnValues[1], tnValues[2], tnValues[3], tnValues[4]));
        Tuple tuple5 = new Tuple5<>(tValues[0], tValues[1], tValues[2], tValues[3], tValues[4]);
        Tuple tupleN4 = new TupleN(List.of(tnValues[0], tnValues[1], tnValues[2], tnValues[3]));
        Tuple tuple4 = new Tuple4<>(tValues[0], tValues[1], tValues[2], tValues[3]);
        Tuple tupleN3 = new TupleN(List.of(tnValues[0], tnValues[1], tnValues[2]));
        Tuple tuple3 = new Tuple3<>(tValues[0], tValues[1], tValues[2]);
        Tuple tupleN2 = new TupleN(List.of(tnValues[0], tnValues[1]));
        Tuple tuple2 = new Tuple2<>(tValues[0], tValues[1]);
        Tuple tupleN1 = new TupleN(List.of(tnValues[0]));
        Tuple tuple1 = new Tuple1<>(tValues[0]);

        assertNotEquals(tupleN8, tuple8);
        assertNotEquals(tupleN7, tuple7);
        assertNotEquals(tupleN6, tuple6);
        assertNotEquals(tupleN5, tuple5);
        assertNotEquals(tupleN4, tuple4);
        assertNotEquals(tupleN3, tuple3);
        assertNotEquals(tupleN2, tuple2);
        assertNotEquals(tupleN1, tuple1);
    }
}
