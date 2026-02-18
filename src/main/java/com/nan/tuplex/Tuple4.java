package com.nan.tuplex;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

/**
 * Represents a tuple of four elements, providing type-safe access to its contents and methods
 * for functional transformations and conversions. This tuple is immutable and serializable,
 * ensuring consistency and thread-safety. The elements can represent values of potentially
 * different types.
 *
 * @param <A> the type of the first element
 * @param <B> the type of the second element
 * @param <C> the type of the third element
 * @param <D> the type of the fourth element
 */
record Tuple4<A, B, C, D>(
        A _1,
        B _2,
        C _3,
        D _4) implements Tuple, Serializable {

    Tuple4 {
        Objects.requireNonNull(_1);
        Objects.requireNonNull(_2);
        Objects.requireNonNull(_3);
        Objects.requireNonNull(_4);
    }

    @Override
    public int size() {
        return 4;
    }

    @Override
    public Object get(int index) {
        return switch (index) {
            case 1 -> _1;
            case 2 -> _2;
            case 3 -> _3;
            case 4 -> _4;
            default -> throw new IndexOutOfBoundsException(index);
        };
    }

    @Override
    public List<Object> toList() {
        return List.of(_1, _2, _3, _4);
    }

    @Override
    public Object[] toArray() {
        return new Object[] { _1, _2, _3, _4 };
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
     * Transforms each element of this tuple using the provided mapping functions and
     * returns a new tuple of the same size containing the transformed elements.
     *
     * @param <R1> the type of the first element after transformation
     * @param <R2> the type of the second element after transformation
     * @param <R3> the type of the third element after transformation
     * @param <R4> the type of the fourth element after transformation
     * @param f1 the function to apply to the first element (must not be null)
     * @param f2 the function to apply to the second element (must not be null)
     * @param f3 the function to apply to the third element (must not be null)
     * @param f4 the function to apply to the fourth element (must not be null)
     * @return a new {@code Tuple4} containing the results of applying the mapping functions
     *         to the corresponding elements of this tuple
     * @throws NullPointerException if any of the mapping functions is null
     */
    public <R1, R2, R3, R4> Tuple4<R1, R2, R3, R4> map(
            Function<? super A, ? extends R1> f1,
            Function<? super B, ? extends R2> f2,
            Function<? super C, ? extends R3> f3,
            Function<? super D, ? extends R4> f4) {
        Objects.requireNonNull(f1);
        Objects.requireNonNull(f2);
        Objects.requireNonNull(f3);
        Objects.requireNonNull(f4);
        return new Tuple4<>(
                f1.apply(_1),
                f2.apply(_2),
                f3.apply(_3),
                f4.apply(_4));
    }

    /**
     * Applies the given mapping function to the first element of this tuple and returns
     * a new tuple with the transformed first element while keeping the other elements unchanged.
     *
     * @param <R> the type of the first element after transformation
     * @param mapper the function to apply to the first element (must not be null)
     * @return a new {@code Tuple4} instance with the first element transformed by the given function
     * @throws NullPointerException if the mapper is null
     */
    public <R> Tuple4<R, B, C, D> map1(Function<? super A, ? extends R> mapper) {
        Objects.requireNonNull(mapper);
        return new Tuple4<>(mapper.apply(_1), _2, _3, _4);
    }

    /**
     * Applies the given mapping function to the second element of this tuple and returns
     * a new tuple with the transformed second element while keeping the other elements unchanged.
     *
     * @param <R> the type of the second element after transformation
     * @param mapper the function to apply to the second element (must not be null)
     * @return a new {@code Tuple4} instance with the second element transformed by the given function
     * @throws NullPointerException if the mapper is null
     */
    public <R> Tuple4<A, R, C, D> map2(Function<? super B, ? extends R> mapper) {
        Objects.requireNonNull(mapper);
        return new Tuple4<>(_1, mapper.apply(_2), _3, _4);
    }

    /**
     * Applies the given mapping function to the third element of this tuple and returns
     * a new tuple with the transformed third element while keeping the other elements unchanged.
     *
     * @param <R> the type of the third element after transformation
     * @param mapper the function to apply to the third element (must not be null)
     * @return a new {@code Tuple4} instance with the third element transformed by the given function
     * @throws NullPointerException if the mapper is null
     */
    public <R> Tuple4<A, B, R, D> map3(Function<? super C, ? extends R> mapper) {
        Objects.requireNonNull(mapper);
        return new Tuple4<>(_1, _2, mapper.apply(_3), _4);
    }

    /**
     * Applies the given mapping function to the fourth element of this tuple and returns
     * a new tuple with the transformed fourth element while keeping the other elements unchanged.
     *
     * @param <R> the type of the fourth element after transformation
     * @param mapper the function to apply to the fourth element (must not be null)
     * @return a new {@code Tuple4} instance with the fourth element transformed by the given function
     * @throws NullPointerException if the mapper is null
     */
    public <R> Tuple4<A, B, C, R> map4(Function<? super D, ? extends R> mapper) {
        Objects.requireNonNull(mapper);
        return new Tuple4<>(_1, _2, _3, mapper.apply(_4));
    }

    static <A, B, C, D> Tuple4<A, B, C, D> of(A a, B b, C c, D d) {
        return new Tuple4<>(a, b, c, d);
    }
}
