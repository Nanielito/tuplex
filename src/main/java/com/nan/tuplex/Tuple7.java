package com.nan.tuplex;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

/**
 * A record that represents a tuple containing exactly seven elements.
 * This tuple is immutable and allows storing and manipulating seven values of potentially different types.
 *
 * @param <A> the type of the first element
 * @param <B> the type of the second element
 * @param <C> the type of the third element
 * @param <D> the type of the fourth element
 * @param <E> the type of the fifth element
 * @param <F> the type of the sixth element
 * @param <G> the type of the seventh element
 */
record Tuple7<A, B, C, D, E, F, G>(
        A _1,
        B _2,
        C _3,
        D _4,
        E _5,
        F _6,
        G _7) implements Tuple, Serializable {

    Tuple7 {
        Objects.requireNonNull(_1);
        Objects.requireNonNull(_2);
        Objects.requireNonNull(_3);
        Objects.requireNonNull(_4);
        Objects.requireNonNull(_5);
        Objects.requireNonNull(_6);
        Objects.requireNonNull(_7);
    }

    @Override
    public int size() {
        return 7;
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
            case 7 -> _7;
            default -> throw new IndexOutOfBoundsException(index);
        };
    }

    @Override
    public List<Object> toList() {
        return List.of(_1, _2, _3, _4, _5, _6, _7);
    }

    @Override
    public Object[] toArray() {
        return new Object[] { _1, _2, _3, _4, _5, _6, _7 };
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
     * Transforms the elements of the tuple by applying the provided mapping functions to each element.
     * Each function corresponds to a specific element of the tuple and returns a new value for that position.
     *
     * @param f1 the mapping function to apply to the first element of the tuple
     * @param f2 the mapping function to apply to the second element of the tuple
     * @param f3 the mapping function to apply to the third element of the tuple
     * @param f4 the mapping function to apply to the fourth element of the tuple
     * @param f5 the mapping function to apply to the fifth element of the tuple
     * @param f6 the mapping function to apply to the sixth element of the tuple
     * @param f7 the mapping function to apply to the seventh element of the tuple
     * @return a new {@code Tuple7} instance with the results of the mapping functions applied to the
     *         corresponding elements of the original tuple
     * @throws NullPointerException if any of the mapping functions is {@code null}
     */
    public <R1, R2, R3, R4, R5, R6, R7> Tuple7<R1, R2, R3, R4, R5, R6, R7> map(
            Function<? super A, ? extends R1> f1,
            Function<? super B, ? extends R2> f2,
            Function<? super C, ? extends R3> f3,
            Function<? super D, ? extends R4> f4,
            Function<? super E, ? extends R5> f5,
            Function<? super F, ? extends R6> f6,
            Function<? super G, ? extends R7> f7) {
        Objects.requireNonNull(f1);
        Objects.requireNonNull(f2);
        Objects.requireNonNull(f3);
        Objects.requireNonNull(f4);
        Objects.requireNonNull(f5);
        Objects.requireNonNull(f6);
        Objects.requireNonNull(f7);
        return new Tuple7<>(
                f1.apply(_1),
                f2.apply(_2),
                f3.apply(_3),
                f4.apply(_4),
                f5.apply(_5),
                f6.apply(_6),
                f7.apply(_7));
    }

    /**
     * Transforms the first element of the tuple by applying the provided mapping function.
     *
     * @param <R> the type of the transformed first element
     * @param mapper the mapping function to apply to the first element of the tuple
     * @return a new {@code Tuple7} instance with the first element transformed,
     *         while all other elements remain unchanged
     * @throws NullPointerException if the mapper function is {@code null}
     */
    public <R> Tuple7<R, B, C, D, E, F,G> map1(Function<? super A, ? extends R> mapper) {
        Objects.requireNonNull(mapper);
        return new Tuple7<>(mapper.apply(_1), _2, _3, _4, _5, _6, _7);
    }

    /**
     * Transforms the second element of the tuple by applying the provided mapping function.
     *
     * @param <R> the type of the transformed second element
     * @param mapper the mapping function to apply to the second element of the tuple
     * @return a new {@code Tuple7} instance with the second element transformed,
     *         while all other elements remain unchanged
     * @throws NullPointerException if the mapper function is {@code null}
     */
    public <R> Tuple7<A, R, C, D, E, F, G> map2(Function<? super B, ? extends R> mapper) {
        Objects.requireNonNull(mapper);
        return new Tuple7<>(_1, mapper.apply(_2), _3, _4, _5, _6, _7);
    }

    /**
     * Transforms the third element of the tuple by applying the provided mapping function.
     *
     * @param <R> the type of the transformed third element
     * @param mapper the mapping function to apply to the third element of the tuple
     * @return a new {@code Tuple7} instance with the third element transformed,
     *         while all other elements remain unchanged
     * @throws NullPointerException if the mapper function is {@code null}
     */
    public <R> Tuple7<A, B, R, D, E, F, G> map3(Function<? super C, ? extends R> mapper) {
        Objects.requireNonNull(mapper);
        return new Tuple7<>(_1, _2, mapper.apply(_3), _4, _5, _6, _7);
    }

    /**
     * Transforms the fourth element of the tuple by applying the provided mapping function.
     *
     * @param <R> the type of the transformed fourth element
     * @param mapper the mapping function to apply to the fourth element of the tuple
     * @return a new {@code Tuple7} instance with the fourth element transformed,
     *         while all other elements remain unchanged
     * @throws NullPointerException if the mapper function is {@code null}
     */
    public <R> Tuple7<A, B, C, R, E, F, G> map4(Function<? super D, ? extends R> mapper) {
        Objects.requireNonNull(mapper);
        return new Tuple7<>(_1, _2, _3, mapper.apply(_4), _5, _6, _7);
    }

    /**
     * Transforms the fifth element of the tuple by applying the provided mapping function.
     *
     * @param <R> the type of the transformed fifth element
     * @param mapper the mapping function to apply to the fifth element of the tuple
     * @return a new {@code Tuple7} instance with the fifth element transformed,
     *         while all other elements remain unchanged
     * @throws NullPointerException if the mapper function is {@code null}
     */
    public <R> Tuple7<A, B, C, D, R, F, G> map5(Function<? super E, ? extends R> mapper) {
        Objects.requireNonNull(mapper);
        return new Tuple7<>(_1, _2, _3, _4, mapper.apply(_5), _6, _7);
    }

    /**
     * Transforms the sixth element of the tuple by applying the provided mapping function.
     *
     * @param <R> the type of the transformed sixth element
     * @param mapper the mapping function to apply to the sixth element of the tuple
     * @return a new {@code Tuple7} instance with the sixth element transformed,
     *         while all other elements remain unchanged
     * @throws NullPointerException if the mapper function is {@code null}
     */
    public <R> Tuple7<A, B, C, D, E, R, G> map6(Function<? super F, ? extends R> mapper) {
        Objects.requireNonNull(mapper);
        return new Tuple7<>(_1, _2, _3, _4, _5, mapper.apply(_6), _7);
    }

    /**
     * Transforms the seventh element of the tuple by applying the provided mapping function.
     *
     * @param <R> the type of the transformed seventh element
     * @param mapper the mapping function to apply to the seventh element of the tuple
     * @return a new {@code Tuple7} instance with the seventh element transformed,
     *         while all other elements remain unchanged
     * @throws NullPointerException if the mapper function is {@code null}
     */
    public <R> Tuple7<A, B, C, D, E, F, R> map7(Function<? super G, ? extends R> mapper) {
        Objects.requireNonNull(mapper);
        return new Tuple7<>(_1, _2, _3, _4, _5, _6, mapper.apply(_7));
    }

    static <A, B, C, D, E, F, G> Tuple7<A, B, C, D, E, F, G> of(A a, B b, C c, D d, E e, F f, G g) {
        return new Tuple7<>(a, b, c, d, e, f, g);
    }
}
