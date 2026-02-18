package com.nan.tuplex;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

/**
 * A record representing a tuple with two elements. This tuple is immutable, generic,
 * and allows storing elements of potentially different types. Provides methods for
 * accessing elements, converting to collections, and performing element-wise mapping.
 *
 * @param <A> the type of the first element (left)
 * @param <B> the type of the second element (right)
 */
record Tuple2<A, B>(
        A left,
        B right) implements Tuple, Serializable {

    Tuple2 {
        Objects.requireNonNull(left);
        Objects.requireNonNull(right);
    }

    @Override
    public int size() {
        return 2;
    }

    @Override
    public Object get(int index) {
        return switch (index) {
            case 1 -> left;
            case 2 -> right;
            default -> throw new IndexOutOfBoundsException(index);
        };
    }

    @Override
    public List<Object> toList() {
        return List.of(left, right);
    }

    @Override
    public Object[] toArray() {
        return new Object[] { left, right };
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
     * Transforms the elements of this tuple using the provided mapping functions and returns
     * a new tuple with the transformed elements.
     *
     * @param <R1> the type of the first element in the resulting tuple
     * @param <R2> the type of the second element in the resulting tuple
     * @param f1 the mapping function to transform the first element (left)
     * @param f2 the mapping function to transform the second element (right)
     * @return a new {@code Tuple2} containing the results of applying the provided
     *         mapping functions to the corresponding elements of this tuple
     * @throws NullPointerException if {@code f1} or {@code f2} is null
     */
    public <R1, R2> Tuple2<R1, R2> map(
            Function<? super A, ? extends R1> f1,
            Function<? super B, ? extends R2> f2) {
        Objects.requireNonNull(f1);
        Objects.requireNonNull(f2);
        return new Tuple2<>(
                f1.apply(left),
                f2.apply(right));
    }

    /**
     * Transforms the first (left) element of this tuple using the provided mapping function
     * and returns a new tuple with the transformed first element and the same second element.
     *
     * @param <R> the type of the transformed first element
     * @param mapper the function to apply to the first element (must not be null)
     * @return a new {@code Tuple2} instance with the transformed first element and the unchanged second element
     * @throws NullPointerException if the {@code mapper} is null
     */
    public <R> Tuple2<R, B> map1(Function<? super A, ? extends R> mapper) {
        Objects.requireNonNull(mapper);
        return new Tuple2<>(mapper.apply(left), right);
    }

    /**
     * Transforms the second (right) element of this tuple using the provided mapping function
     * and returns a new tuple with the transformed second element and the same first element.
     *
     * @param <R> the type of the transformed second element
     * @param mapper the function to apply to the second element (must not be null)
     * @return a new {@code Tuple2} instance with the unchanged first element and the transformed second element
     * @throws NullPointerException if the {@code mapper} is null
     */
    public <R> Tuple2<A, R> map2(Function<? super B, ? extends R> mapper) {
        Objects.requireNonNull(mapper);
        return new Tuple2<>(left, mapper.apply(right));
    }

    static <A, B> Tuple2<A, B> of(A a, B b) {
        return new Tuple2<>(a, b);
    }
}
