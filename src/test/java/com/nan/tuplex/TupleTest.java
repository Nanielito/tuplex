package com.nan.tuplex;

/**
 * Defines a contract for testing operations, behaviors, and properties of tuple data structures.
 *
 * @param <T> the type of tuple being tested, extending from the {@code Tuple} interface
 */
interface TupleTest<T> {

    T createTuple();

    T createDifferentTuple();

    int expectedSize();

    Object[] expectedValues();

    Object expectedValue();

    Object unexpectedValue();
}
