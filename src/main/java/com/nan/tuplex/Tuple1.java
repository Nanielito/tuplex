package com.nan.tuplex;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

/**
 * Represents a tuple containing a single element. This class provides methods
 * to access the value, operate on it, and convert the tuple into collections or arrays.
 * The tuple is immutable once created.
 *
 * @param <A> the type of the single value held in this tuple
 */
record Tuple1<A>(
        A value) implements Tuple, Serializable {

    Tuple1 {
        Objects.requireNonNull(value);
    }

    @Override
    public int size() {
        return 1;
    }

    @Override
    public Object get(int index) {
        if (index == 1)
            return value;
        throw new IndexOutOfBoundsException(index);
    }

    public A get() {
        return value();
    }

    @Override
    public List<Object> toList() {
        return List.of(value);
    }

    @Override
    public Object[] toArray() {
        return new Object[] { value };
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
     * Transforms the single value in this tuple using the provided mapping function
     * and returns a new tuple containing the mapped value.
     *
     * @param <R> the type of the result after mapping
     * @param mapper the function to apply to the single value in this tuple (must not be null)
     * @return a new {@code Tuple1} instance containing the transformed value
     * @throws NullPointerException if the mapper is null
     */
    public <R> Tuple1<R> map(Function<? super A, ? extends R> mapper) {
        Objects.requireNonNull(mapper);
        return new Tuple1<>(mapper.apply(value));
    }

    static <A> Tuple1<A> of(A a) {
        return new Tuple1<>(a);
    }
}
