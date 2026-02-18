package com.nan.tuplex;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

/**
 * A record representing a tuple of five elements. A tuple is an immutable data structure
 * that holds a fixed number of elements of potentially different types. The {@code Tuple5} class
 * provides methods to access its elements, convert the tuple to collections or arrays, map its values,
 * and perform comparison operations.
 *
 * @param <A> the type of the first element
 * @param <B> the type of the second element
 * @param <C> the type of the third element
 * @param <D> the type of the fourth element
 * @param <E> the type of the fifth element
 */
record Tuple5<A, B, C, D, E>(
        A _1,
        B _2,
        C _3,
        D _4,
        E _5) implements Tuple, Serializable {

    Tuple5 {
        Objects.requireNonNull(_1);
        Objects.requireNonNull(_2);
        Objects.requireNonNull(_3);
        Objects.requireNonNull(_4);
        Objects.requireNonNull(_5);
    }

    @Override
    public int size() {
        return 5;
    }

    @Override
    public Object get(int index) {
        return switch (index) {
            case 1 -> _1;
            case 2 -> _2;
            case 3 -> _3;
            case 4 -> _4;
            case 5 -> _5;
            default -> throw new IndexOutOfBoundsException(index);
        };
    }

    @Override
    public List<Object> toList() {
        return List.of(_1, _2, _3, _4, _5);
    }

    @Override
    public Object[] toArray() {
        return new Object[] { _1, _2, _3, _4, _5 };
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Tuple other))
            return false;
        if (size() != other.size())
            return false;

        for (int i = 1; i <= size(); i++)
            if (!get(i).equals(other.get(i)))
                return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = 1;
        for (int i = 1; i <= size(); i++)
            result = 31 * result + get(i).hashCode();
        return result;
    }

    /**
     * Transforms all elements of this tuple using the provided mapping functions and
     * returns a new tuple containing the mapped values.
     *
     * @param <R1> the type of the first mapped value
     * @param <R2> the type of the second mapped value
     * @param <R3> the type of the third mapped value
     * @param <R4> the type of the fourth mapped value
     * @param <R5> the type of the fifth mapped value
     * @param f1 the mapping function to apply to the first element (must not be null)
     * @param f2 the mapping function to apply to the second element (must not be null)
     * @param f3 the mapping function to apply to the third element (must not be null)
     * @param f4 the mapping function to apply to the fourth element (must not be null)
     * @param f5 the mapping function to apply to the fifth element (must not be null)
     * @return a new {@code Tuple5} instance containing the values resulting from applying
     *         the mapping functions to the corresponding elements of this tuple
     * @throws NullPointerException if any of the mapping functions are null
     */
    public <R1, R2, R3, R4, R5> Tuple5<R1, R2, R3, R4, R5> map(
            Function<? super A, ? extends R1> f1,
            Function<? super B, ? extends R2> f2,
            Function<? super C, ? extends R3> f3,
            Function<? super D, ? extends R4> f4,
            Function<? super E, ? extends R5> f5) {
        Objects.requireNonNull(f1);
        Objects.requireNonNull(f2);
        Objects.requireNonNull(f3);
        Objects.requireNonNull(f4);
        Objects.requireNonNull(f5);
        return new Tuple5<>(
                f1.apply(_1),
                f2.apply(_2),
                f3.apply(_3),
                f4.apply(_4),
                f5.apply(_5));
    }

    /**
     * Transforms the first element of this tuple using the specified mapping function
     * and returns a new tuple with the mapped first element and the unchanged remaining elements.
     *
     * @param <R> the type of the mapped first element
     * @param mapper the mapping function to apply to the first element (must not be null)
     * @return a new {@code Tuple5} instance with the mapped first element and unchanged remaining elements
     * @throws NullPointerException if the mapping function is null
     */
    public <R> Tuple5<R, B, C, D, E> map1(Function<? super A, ? extends R> mapper) {
        Objects.requireNonNull(mapper);
        return new Tuple5<>(mapper.apply(_1), _2, _3, _4, _5);
    }

    /**
     * Transforms the second element of this tuple using the specified mapping function
     * and returns a new tuple with the mapped second element and the unchanged remaining elements.
     *
     * @param <R> the type of the mapped second element
     * @param mapper the mapping function to apply to the second element (must not be null)
     * @return a new {@code Tuple5} instance with the mapped second element and unchanged remaining elements
     * @throws NullPointerException if the mapping function is null
     */
    public <R> Tuple5<A, R, C, D, E> map2(Function<? super B, ? extends R> mapper) {
        Objects.requireNonNull(mapper);
        return new Tuple5<>(_1, mapper.apply(_2), _3, _4, _5);
    }

    /**
     * Transforms the third element of this tuple using the specified mapping function
     * and returns a new tuple with the mapped third element and the unchanged remaining elements.
     *
     * @param <R> the type of the mapped third element
     * @param mapper the mapping function to apply to the third element (must not be null)
     * @return a new {@code Tuple5} instance with the mapped third element and unchanged remaining elements
     * @throws NullPointerException if the mapping function is null
     */
    public <R> Tuple5<A, B, R, D, E> map3(Function<? super C, ? extends R> mapper) {
        Objects.requireNonNull(mapper);
        return new Tuple5<>(_1, _2, mapper.apply(_3), _4, _5);
    }

    /**
     * Transforms the fourth element of this tuple using the specified mapping function
     * and returns a new tuple with the mapped fourth element and the unchanged remaining elements.
     *
     * @param <R> the type of the mapped fourth element
     * @param mapper the mapping function to apply to the fourth element (must not be null)
     * @return a new {@code Tuple5} instance with the mapped fourth element and unchanged remaining elements
     * @throws NullPointerException if the mapping function is null
     */
    public <R> Tuple5<A, B, C, R, E> map4(Function<? super D, ? extends R> mapper) {
        Objects.requireNonNull(mapper);
        return new Tuple5<>(_1, _2, _3, mapper.apply(_4), _5);
    }

    /**
     * Transforms the fifth element of this tuple using the specified mapping function
     * and returns a new tuple with the mapped fifth element and the unchanged remaining elements.
     *
     * @param <R> the type of the mapped fifth element
     * @param mapper the mapping function to apply to the fifth element (must not be null)
     * @return a new {@code Tuple5} instance with the mapped fifth element and unchanged remaining elements
     * @throws NullPointerException if the mapping function is null
     */
    public <R> Tuple5<A, B, C, D, R> map5(Function<? super E, ? extends R> mapper) {
        Objects.requireNonNull(mapper);
        return new Tuple5<>(_1, _2, _3, _4, mapper.apply(_5));
    }

    static <A, B, C, D, E> Tuple5<A, B, C, D, E> of(A a, B b, C c, D d, E e) {
        return new Tuple5<>(a, b, c, d, e);
    }
}
