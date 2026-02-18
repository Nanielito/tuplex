package com.nan.tuplex;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

/**
 * Represents an immutable tuple containing three elements, which can be of potentially
 * different types. This class implements the {@code Tuple} interface, allowing access
 * to its elements, conversion into collections or arrays, and other tuple-related operations.
 *
 * @param <A> the type of the first element in the tuple
 * @param <B> the type of the second element in the tuple
 * @param <C> the type of the third element in the tuple
 */
record Tuple3<A, B, C>(
        A left,
        B middle,
        C right) implements Tuple, Serializable {

    Tuple3 {
        Objects.requireNonNull(left);
        Objects.requireNonNull(middle);
        Objects.requireNonNull(right);
    }

    @Override
    public int size() {
        return 3;
    }

    @Override
    public Object get(int index) {
        return switch (index) {
            case 1 -> left;
            case 2 -> middle;
            case 3 -> right;
            default -> throw new IndexOutOfBoundsException(index);
        };
    }

    @Override
    public List<Object> toList() {
        return List.of(left, middle, right);
    }

    @Override
    public Object[] toArray() {
        return new Object[] { left, middle, right };
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
     * Transforms the elements of this tuple using the provided mapping functions.
     * Each function is applied to the corresponding element of the tuple, producing
     * a new tuple with the transformed elements.
     *
     * @param f1 the function to transform the first element
     * @param f2 the function to transform the second element
     * @param f3 the function to transform the third element
     * @param <R1> the resulting type of the first transformed element
     * @param <R2> the resulting type of the second transformed element
     * @param <R3> the resulting type of the third transformed element
     * @return a new tuple containing the transformed elements
     * @throws NullPointerException if any of the provided functions is {@code null}
     */
    public <R1, R2, R3> Tuple3<R1, R2, R3> map(
            Function<? super A, ? extends R1> f1,
            Function<? super B, ? extends R2> f2,
            Function<? super C, ? extends R3> f3) {
        Objects.requireNonNull(f1);
        Objects.requireNonNull(f2);
        Objects.requireNonNull(f3);
        return new Tuple3<>(
                f1.apply(left),
                f2.apply(middle),
                f3.apply(right));
    }

    /**
     * Transforms the first element of this tuple using the provided mapping function
     * and returns a new tuple with the transformed value as the first element, while
     * keeping the other elements unchanged.
     *
     * @param <R> the type of the resulting transformed first element
     * @param mapper the function to apply to the first element of this tuple (must not be null)
     * @return a new {@code Tuple3} instance with the transformed first element, and the original
     *         second and third elements
     * @throws NullPointerException if the mapper is null
     */
    public <R> Tuple3<R, B, C> map1(Function<? super A, ? extends R> mapper) {
        Objects.requireNonNull(mapper);
        return new Tuple3<>(mapper.apply(left), middle, right);
    }

    /**
     * Transforms the second element of this tuple using the provided mapping function
     * and returns a new tuple with the transformed value as the second element, while
     * keeping the other elements unchanged.
     *
     * @param <R> the type of the resulting transformed second element
     * @param mapper the function to apply to the second element of this tuple (must not be null)
     * @return a new {@code Tuple3} instance with the transformed second element, and the original
     *         first and third elements
     * @throws NullPointerException if the mapper is null
     */
    public <R> Tuple3<A, R, C> map2(Function<? super B, ? extends R> mapper) {
        Objects.requireNonNull(mapper);
        return new Tuple3<>(left, mapper.apply(middle), right);
    }

    /**
     * Transforms the third element of this tuple using the provided mapping function
     * and returns a new tuple with the transformed value as the third element, while
     * keeping the other elements unchanged.
     *
     * @param <R> the type of the resulting transformed third element
     * @param mapper the function to apply to the third element of this tuple (must not be null)
     * @return a new {@code Tuple3} instance with the transformed third element, and the original
     *         first and second elements
     * @throws NullPointerException if the mapper is null
     */
    public <R> Tuple3<A, B, R> map3(Function<? super C, ? extends R> mapper) {
        Objects.requireNonNull(mapper);
        return new Tuple3<>(left, middle, mapper.apply(right));
    }

    static <A, B, C> Tuple3<A, B, C> of(A a, B b, C c) {
        return new Tuple3<>(a, b, c);
    }
}
