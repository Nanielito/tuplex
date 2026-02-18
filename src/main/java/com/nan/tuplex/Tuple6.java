package com.nan.tuplex;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

/**
 * Represents a tuple of six elements, each of potentially different types.
 * This tuple is immutable and provides utilities to access its elements,
 * transform its values, convert to a collection or array, and perform
 * equality and hash-based operations.
 *
 * @param <A> the type of the first element
 * @param <B> the type of the second element
 * @param <C> the type of the third element
 * @param <D> the type of the fourth element
 * @param <E> the type of the fifth element
 * @param <F> the type of the sixth element
 */
record Tuple6<A, B, C, D, E, F>(
        A _1,
        B _2,
        C _3,
        D _4,
        E _5,
        F _6) implements Tuple, Serializable {

    Tuple6 {
        Objects.requireNonNull(_1);
        Objects.requireNonNull(_2);
        Objects.requireNonNull(_3);
        Objects.requireNonNull(_4);
        Objects.requireNonNull(_5);
        Objects.requireNonNull(_6);
    }

    @Override
    public int size() {
        return 6;
    }

    @Override
    public Object get(int index) {
        return switch (index) {
            case 1 -> _1;
            case 2 -> _2;
            case 3 -> _3;
            case 4 -> _4;
            case 5 -> _5;
            case 6 -> _6;
            default -> throw new IndexOutOfBoundsException(index);
        };
    }

    @Override
    public List<Object> toList() {
        return List.of(_1, _2, _3, _4, _5, _6);
    }

    @Override
    public Object[] toArray() {
        return new Object[] { _1, _2, _3, _4, _5, _6 };
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
     * Applies the provided mapping functions to each element of the tuple and returns a new tuple
     * containing the results of the mappings.
     *
     * @param f1 the mapping function to apply to the first element of the tuple
     * @param f2 the mapping function to apply to the second element of the tuple
     * @param f3 the mapping function to apply to the third element of the tuple
     * @param f4 the mapping function to apply to the fourth element of the tuple
     * @param f5 the mapping function to apply to the fifth element of the tuple
     * @param f6 the mapping function to apply to the sixth element of the tuple
     * @param <R1> the result type of the mapping function applied to the first element
     * @param <R2> the result type of the mapping function applied to the second element
     * @param <R3> the result type of the mapping function applied to the third element
     * @param <R4> the result type of the mapping function applied to the fourth element
     * @param <R5> the result type of the mapping function applied to the fifth element
     * @param <R6> the result type of the mapping function applied to the sixth element
     * @return a new tuple containing the results of applying the specified mapping functions
     *         to the respective elements of the original tuple
     */
    public <R1, R2, R3, R4, R5, R6> Tuple6<R1, R2, R3, R4, R5, R6> map(
            Function<? super A, ? extends R1> f1,
            Function<? super B, ? extends R2> f2,
            Function<? super C, ? extends R3> f3,
            Function<? super D, ? extends R4> f4,
            Function<? super E, ? extends R5> f5,
            Function<? super F, ? extends R6> f6) {
        Objects.requireNonNull(f1);
        Objects.requireNonNull(f2);
        Objects.requireNonNull(f3);
        Objects.requireNonNull(f4);
        Objects.requireNonNull(f5);
        Objects.requireNonNull(f6);
        return new Tuple6<>(
                f1.apply(_1),
                f2.apply(_2),
                f3.apply(_3),
                f4.apply(_4),
                f5.apply(_5),
                f6.apply(_6));
    }

    /**
     * Maps the first element of the tuple using the provided mapping function and returns a
     * new tuple with the transformed first element while retaining the remaining elements.
     *
     * @param <R>   the result type of the mapping function for the first element
     * @param mapper a function that maps the first element of type {@code A} to a new type {@code R}
     * @return a new tuple with the first element transformed by the given mapping function
     */
    public <R> Tuple6<R, B, C, D, E, F> map1(Function<? super A, ? extends R> mapper) {
        Objects.requireNonNull(mapper);
        return new Tuple6<>(mapper.apply(_1), _2, _3, _4, _5, _6);
    }

    /**
     * Maps the second element of the tuple using the provided mapping function and returns a
     * new tuple with the transformed second element while retaining the remaining elements.
     *
     * @param <R>   the result type of the mapping function for the second element
     * @param mapper a function that maps the second element of type {@code B} to a new type {@code R}
     * @return a new tuple with the second element transformed by the given mapping function
     */
    public <R> Tuple6<A, R, C, D, E, F> map2(Function<? super B, ? extends R> mapper) {
        Objects.requireNonNull(mapper);
        return new Tuple6<>(_1, mapper.apply(_2), _3, _4, _5, _6);
    }

    /**
     * Maps the third element of the tuple using the provided mapping function and returns a
     * new tuple with the transformed third element while retaining the remaining elements.
     *
     * @param <R>    the result type of the mapping function for the third element
     * @param mapper a function that maps the third element of type {@code C} to a new type {@code R}
     * @return a new tuple with the third element transformed by the given mapping function
     */
    public <R> Tuple6<A, B, R, D, E, F> map3(Function<? super C, ? extends R> mapper) {
        Objects.requireNonNull(mapper);
        return new Tuple6<>(_1, _2, mapper.apply(_3), _4, _5, _6);
    }

    /**
     * Maps the fourth element of the tuple using the provided mapping function and returns a
     * new tuple with the transformed fourth element while retaining the remaining elements.
     *
     * @param <R>    the result type of the mapping function for the fourth element
     * @param mapper a function that maps the fourth element of type {@code D} to a new type {@code R}
     * @return a new tuple with the fourth element transformed by the given mapping function
     */
    public <R> Tuple6<A, B, C, R, E, F> map4(Function<? super D, ? extends R> mapper) {
        Objects.requireNonNull(mapper);
        return new Tuple6<>(_1, _2, _3, mapper.apply(_4), _5, _6);
    }

    /**
     * Maps the fifth element of the tuple using the provided mapping function and returns a
     * new tuple with the transformed fifth element while retaining the remaining elements.
     *
     * @param <R>    the result type of the mapping function for the fifth element
     * @param mapper a function that maps the fifth element of type {@code E} to a new type {@code R}
     * @return a new tuple with the fifth element transformed by the given mapping function
     */
    public <R> Tuple6<A, B, C, D, R, F> map5(Function<? super E, ? extends R> mapper) {
        Objects.requireNonNull(mapper);
        return new Tuple6<>(_1, _2, _3, _4, mapper.apply(_5), _6);
    }

    /**
     * Maps the sixth element of the tuple using the provided mapping function and returns a
     * new tuple with the transformed sixth element while retaining the remaining elements.
     *
     * @param <R>    the result type of the mapping function for the sixth element
     * @param mapper a function that maps the sixth element of type {@code F} to a new type {@code R}
     * @return a new tuple with the sixth element transformed by the given mapping function
     */
    public <R> Tuple6<A, B, C, D, E, R> map6(Function<? super F, ? extends R> mapper) {
        Objects.requireNonNull(mapper);
        return new Tuple6<>(_1, _2, _3, _4, _5, mapper.apply(_6));
    }

    static <A, B, C, D, E, F> Tuple6<A, B, C, D, E, F> of(A a, B b, C c, D d, E e, F f) {
        return new Tuple6<>(a, b, c, d, e, f);
    }
}
