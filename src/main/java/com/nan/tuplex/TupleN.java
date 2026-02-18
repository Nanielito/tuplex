package com.nan.tuplex;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * A record representing a generic tuple with an arbitrary number of elements.
 * The elements are stored in an immutable list, ensuring immutability of the tuple.
 * TupleN is used when the number of elements in the tuple is not fixed or predetermined.
 * <p>
 * This class provides methods to access tuple elements by index, convert the tuple
 * to different data types such as a {@code List} or an {@code Array}, and perform
 * operations like streaming and equality checks.
 * <p>
 * The tuple's elements are ordered, starting at index 1, and must not be empty
 * (i.e., a TupleN cannot be constructed without elements).
 */
record TupleN(List<?> values) implements Tuple, Serializable {

    TupleN {
        Objects.requireNonNull(values);
        if (values.isEmpty())
            throw new IllegalArgumentException("Tuple N cannot be empty");
        values = List.copyOf(values);
    }

    @Override
    public int size() {
        return values.size();
    }

    @Override
    public Object get(int index) {
        if (index <= 0 || index > values().size())
            throw new IndexOutOfBoundsException(index);
        return values.get(index - 1);
    }

    @Override
    public List<Object> toList() {
        return List.copyOf(values);
    }

    @Override
    public Object[] toArray() {
        return values.toArray();
    }

    @Override
    public Stream<Object> stream() {
        return values.stream()
                .map(value -> (Object) value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Tuple other))
            return false;
        if (size() != other.size())
            return false;
        if (other instanceof TupleN(List<?> values1))
            return values.equals(values1);

        for (int i = 1; i <= size(); i++)
            if (!values.get(i - 1).equals(other.get(i)))
                return false;

        return true;
    }

    @Override
    public int hashCode() {
        return values.hashCode();
    }

    /**
     * Creates a new {@code TupleN} instance using the provided values.
     * This method accepts a variable number of arguments, encapsulating them
     * into an immutable list to form the internal structure of the tuple.
     *
     * @param values the elements to include in the tuple. Must not be null.
     *               The number of elements can vary, but it cannot be zero.
     * @return a {@code TupleN} instance containing the provided values.
     * @throws NullPointerException if the {@code values} array or any of its elements is null.
     * @throws IllegalArgumentException if the {@code values} array is empty.
     */
    public static TupleN of(Object... values) {
        return new TupleN(List.of(values));
    }
}
