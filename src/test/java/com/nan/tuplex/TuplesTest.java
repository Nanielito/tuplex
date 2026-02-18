package com.nan.tuplex;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Unit test class for validating the creation of tuples using the {@code Tuples} utility class.
 * This class extends {@code TupleBaseTest} to reuse its common test structures and utilities.
 * It verifies the correctness of tuple creation for various tuple types, ranging from
 * {@code Tuple1} to {@code TupleN}.
 * <p>
 * The main focus of the tests is to ensure that tuples created using the {@code Tuples.of}
 * method match the expected instances created using the corresponding tuple's static {@code of} factory methods.
 * Each test case checks the structural equality and integrity of tuples with varying numbers of elements.
 * <p>
 * The test cases include:
 * - {@link #shouldCreateTuple1()}: Tests the creation of a tuple with one element.
 * - {@link #shouldCreateTuple2()}: Tests the creation of a tuple with two elements.
 * - {@link #shouldCreateTuple3()}: Tests the creation of a tuple with three elements.
 * - {@link #shouldCreateTuple4()}: Tests the creation of a tuple with four elements.
 * - {@link #shouldCreateTuple5()}: Tests the creation of a tuple with five elements.
 * - {@link #shouldCreateTuple6()}: Tests the creation of a tuple with six elements.
 * - {@link #shouldCreateTuple7()}: Tests the creation of a tuple with seven elements.
 * - {@link #shouldCreateTuple8()}: Tests the creation of a tuple with eight elements.
 * - {@link #shouldCreateTupleN()}: Tests the creation of a tuple with an arbitrary number of elements.
 * <p>
 * The use of {@code TestValue} demonstrates serialization compatibility and supports ensuring
 * that custom objects can be effectively handled within tuple structures.
 */
class TuplesTest extends TupleBaseTest {

    private final TestValue testValue = new TestValue("test");

    @Test
    void shouldCreateTuple1() {
        assertEquals(
                Tuple1.of(42),
                Tuples.of(42));
    }

    @Test
    void shouldCreateTuple2() {
        assertEquals(
                Tuple2.of(42, "foo"),
                Tuples.of(42, "foo"));
    }

    @Test
    void shouldCreateTuple3() {
        assertEquals(
                Tuple3.of(42, "foo", true),
                Tuples.of(42, "foo", true));
    }

    @Test
    void shouldCreateTuple4() {
        assertEquals(
                Tuple4.of(42, "foo", true, 3.14),
                Tuples.of(42, "foo", true, 3.14));
    }

    @Test
    void shouldCreateTuple5() {
        assertEquals(
                Tuple5.of(42, "foo", true, 3.14, 'c'),
                Tuples.of(42, "foo", true, 3.14, 'c'));
    }

    @Test
    void shouldCreateTuple6() {
        assertEquals(
                Tuple6.of(42, "foo", true, 3.14, 'c', 10_000L),
                Tuples.of(42, "foo", true, 3.14, 'c', 10_000L));
    }

    @Test
    void shouldCreateTuple7() {
        assertEquals(
                Tuple7.of(42, "foo", true, 3.14, 'c', 10_000L, 1.0f),
                Tuples.of(42, "foo", true, 3.14, 'c', 10_000L, 1.0f));
    }

    @Test
    void shouldCreateTuple8() {
        assertEquals(
                Tuple8.of(42, "foo", true, 3.14, 'c', 10_000L, 1.0f, 0x0A),
                Tuples.of(42, "foo", true, 3.14, 'c', 10_000L, 1.0f, 0x0A));
    }

    @Test
    void shouldCreateTupleN() {
        assertEquals(
                TupleN.of(42, "foo", true, 3.14, 'c', 10_000L, 1.0f, 0x0A, testValue),
                Tuples.of(42, "foo", true, 3.14, 'c', 10_000L, 1.0f, 0x0A, testValue));
    }
}
