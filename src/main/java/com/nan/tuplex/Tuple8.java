package com.nan.tuplex;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

/**
 * Represents an immutable tuple that holds exactly eight elements of potentially different types.
 * This class provides methods to access the elements in the tuple, perform operations on individual elements,
 * and convert the tuple to other representations such as lists or arrays.
 *
 * @param <A> the type of the first element
 * @param <B> the type of the second element
 * @param <C> the type of the third element
 * @param <D> the type of the fourth element
 * @param <E> the type of the fifth element
 * @param <F> the type of the sixth element
 * @param <G> the type of the seventh element
 * @param <H> the type of the eighth element
 */
record Tuple8<A, B, C, D, E, F, G, H>(
        A _1,
        B _2,
        C _3,
        D _4,
        E _5,
        F _6,
        G _7,
        H _8) implements Tuple, Serializable {

    Tuple8 {
        Objects.requireNonNull(_1);
        Objects.requireNonNull(_2);
        Objects.requireNonNull(_3);
        Objects.requireNonNull(_4);
        Objects.requireNonNull(_5);
        Objects.requireNonNull(_6);
        Objects.requireNonNull(_7);
        Objects.requireNonNull(_8);
        Objects.requireNonNull(_8);
    }

    @Override
    public int size() {
        return 8;
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
            case 8 -> _8;
            default -> throw new IndexOutOfBoundsException(index);
        };
    }

    @Override
    public List<Object> toList() {
        return List.of(_1, _2, _3, _4, _5, _6, _7, _8);
    }

    @Override
    public Object[] toArray() {
        return new Object[] { _1, _2, _3, _4, _5, _6, _7, _8 };
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
     * Transforms each element of the tuple using the provided mapping functions and returns a new tuple
     * containing the results of applying these functions to the corresponding elements.
     *
     * @param f1 the mapping function for the first element
     * @param f2 the mapping function for the second element
     * @param f3 the mapping function for the third element
     * @param f4 the mapping function for the fourth element
     * @param f5 the mapping function for the fifth element
     * @param f6 the mapping function for the sixth element
     * @param f7 the mapping function for the seventh element
     * @param f8 the mapping function for the eighth element
     * @return a new {@code Tuple8} instance containing the transformed elements
     * @throws NullPointerException if any of the provided mapping functions is {@code null}
     */
    public <R1, R2, R3, R4, R5, R6, R7, R8> Tuple8<R1, R2, R3, R4, R5, R6, R7, R8> map(
            Function<? super A, ? extends R1> f1,
            Function<? super B, ? extends R2> f2,
            Function<? super C, ? extends R3> f3,
            Function<? super D, ? extends R4> f4,
            Function<? super E, ? extends R5> f5,
            Function<? super F, ? extends R6> f6,
            Function<? super G, ? extends R7> f7,
            Function<? super H, ? extends R8> f8) {
        Objects.requireNonNull(f1);
        Objects.requireNonNull(f2);
        Objects.requireNonNull(f3);
        Objects.requireNonNull(f4);
        Objects.requireNonNull(f5);
        Objects.requireNonNull(f6);
        Objects.requireNonNull(f7);
        Objects.requireNonNull(f8);
        return new Tuple8<>(
                f1.apply(_1),
                f2.apply(_2),
                f3.apply(_3),
                f4.apply(_4),
                f5.apply(_5),
                f6.apply(_6),
                f7.apply(_7),
                f8.apply(_8));
    }

    /**
     * Transforms the first element of the tuple using the provided mapping function
     * and returns a new tuple with the transformed first element, leaving the other
     * elements unchanged.
     *
     * @param <R> the type of the transformed first element
     * @param mapper the mapping function to apply to the first element
     * @return a new {@code Tuple8} instance with the transformed first element and
     *         the same values for the remaining elements
     * @throws NullPointerException if the provided mapping function is {@code null}
     */
    public <R> Tuple8<R, B, C, D, E, F, G, H> map1(Function<? super A, ? extends R> mapper) {
        Objects.requireNonNull(mapper);
        return new Tuple8<>(mapper.apply(_1), _2, _3, _4, _5, _6, _7, _8);
    }

    /**
     * Transforms the second element of the tuple using the provided mapping function
     * and returns a new tuple with the transformed second element, leaving the other
     * elements unchanged.
     *
     * @param <R> the type of the transformed second element
     * @param mapper the mapping function to apply to the second element
     * @return a new {@code Tuple8} instance with the transformed second element and
     *         the same values for the remaining elements
     * @throws NullPointerException if the provided mapping function is {@code null}
     */
    public <R> Tuple8<A, R, C, D, E, F, G, H> map2(Function<? super B, ? extends R> mapper) {
        Objects.requireNonNull(mapper);
        return new Tuple8<>(_1, mapper.apply(_2), _3, _4, _5, _6, _7, _8);
    }

    /**
     * Transforms the third element of the tuple using the provided mapping function
     * and returns a new tuple with the transformed third element, leaving the other
     * elements unchanged.
     *
     * @param <R> the type of the transformed third element
     * @param mapper the mapping function to apply to the third element
     * @return a new {@code Tuple8} instance with the transformed third element and
     *         the same values for the remaining elements
     * @throws NullPointerException if the provided mapping function is {@code null}
     */
    public <R> Tuple8<A, B, R, D, E, F, G, H> map3(Function<? super C, ? extends R> mapper) {
        Objects.requireNonNull(mapper);
        return new Tuple8<>(_1, _2, mapper.apply(_3), _4, _5, _6, _7, _8);
    }

    /**
     * Transforms the fourth element of the tuple using the provided mapping function
     * and returns a new tuple with the transformed fourth element, leaving the other
     * elements unchanged.
     *
     * @param <R> the type of the transformed fourth element
     * @param mapper the mapping function to apply to the fourth element
     * @return a new {@code Tuple8} instance with the transformed fourth element and
     *         the same values for the remaining elements
     * @throws NullPointerException if the provided mapping function is {@code null}
     */
    public <R> Tuple8<A, B, C, R, E, F, G, H> map4(Function<? super D, ? extends R> mapper) {
        Objects.requireNonNull(mapper);
        return new Tuple8<>(_1, _2, _3, mapper.apply(_4), _5, _6, _7, _8);
    }

    /**
     * Transforms the fifth element of the tuple using the provided mapping function and returns
     * a new tuple with the transformed fifth element, leaving the other elements unchanged.
     *
     * @param <R> the type of the transformed fifth element
     * @param mapper the mapping function to apply to the fifth element
     * @return a new {@code Tuple8} instance with the transformed fifth element and
     *         the same values for the remaining elements
     * @throws NullPointerException if the provided mapping function is {@code null}
     */
    public <R> Tuple8<A, B, C, D, R, F, G, H> map5(Function<? super E, ? extends R> mapper) {
        Objects.requireNonNull(mapper);
        return new Tuple8<>(_1, _2, _3, _4, mapper.apply(_5), _6, _7, _8);
    }

    /**
     * Transforms the sixth element of the tuple using the provided mapping function
     * and returns a new tuple with the transformed sixth element, leaving the other
     * elements unchanged.
     *
     * @param <R> the type of the transformed sixth element
     * @param mapper the mapping function to apply to the sixth element
     * @return a new {@code Tuple8} instance with the transformed sixth element and
     *         the same values for the remaining elements
     * @throws NullPointerException if the provided mapping function is {@code null}
     */
    public <R> Tuple8<A, B, C, D, E, R, G, H> map6(Function<? super F, ? extends R> mapper) {
        Objects.requireNonNull(mapper);
        return new Tuple8<>(_1, _2, _3, _4, _5, mapper.apply(_6), _7, _8);
    }

    /**
     * Transforms the seventh element of the tuple using the provided mapping function
     * and returns a new tuple with the transformed seventh element, leaving the other
     * elements unchanged.
     *
     * @param <R> the type of the transformed seventh element
     * @param mapper the mapping function to apply to the seventh element
     * @return a new {@code Tuple8} instance with the transformed seventh element and
     *         the same values for the remaining elements
     * @throws NullPointerException if the provided mapping function is {@code null}
     */
    public <R> Tuple8<A, B, C, D, E, F, R, H> map7(Function<? super G, ? extends R> mapper) {
        Objects.requireNonNull(mapper);
        return new Tuple8<>(_1, _2, _3, _4, _5, _6, mapper.apply(_7), _8);
    }

    /**
     * Transforms the eighth element of the tuple using the provided mapping function
     * and returns a new tuple with the transformed eighth element while keeping the
     * other elements unchanged.
     *
     * @param <R> the type of the transformed eighth element
     * @param mapper the mapping function to apply to the eighth element
     * @return a new {@code Tuple8} instance with the transformed eighth element and
     *         the same values for the remaining elements
     * @throws NullPointerException if the provided mapping function is {@code null}
     */
    public <R> Tuple8<A, B, C, D, E, F, G, R> map8(Function<? super H, ? extends R> mapper) {
        Objects.requireNonNull(mapper);
        return new Tuple8<>(_1, _2, _3, _4, _5, _6, _7, mapper.apply(_8));
    }

    static <A, B, C, D, E, F, G, H> Tuple8<A, B, C, D, E, F, G, H> of(A a, B b, C c, D d, E e, F f, G g, H h) {
        return new Tuple8<>(a, b, c, d, e, f, g, h);
    }
}
