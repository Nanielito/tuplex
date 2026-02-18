package com.nan.tuplex;

import java.util.Iterator;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Represents a generalized tuple that encapsulates a fixed number of elements of potentially
 * different types. A tuple is immutable and provides methods to access its elements, convert
 * to collections or arrays, and perform operations like iteration and streaming.
 * <p>
 * This interface is implemented by specific tuple types such as Tuple1, Tuple2, etc., representing
 * tuples of one or more elements, respectively.
 */
public sealed interface Tuple extends Iterable<Object>
        permits Tuple1, Tuple2, Tuple3, Tuple4, Tuple5, Tuple6, Tuple7, Tuple8, TupleN {

    /**
     * Returns the number of elements in the tuple.
     *
     * @return the size of the tuple as an integer
     */
    int size();

    /**
     * Retrieves the element at the specified position in the tuple.
     *
     * @param index the position of the element to retrieve, where the index starts at 1.
     * @return the object located at the specified position in the tuple.
     * @throws IndexOutOfBoundsException if the specified index is less than 1 or greater than the size of the tuple.
     */
    Object get(int index);

    /**
     * Converts the elements of the tuple into an immutable list.
     * The order of elements in the resulting list corresponds to their positions
     * in the tuple, starting from the first element.
     *
     * @return a list containing all elements of the tuple in their natural order
     */
    List<Object> toList();

    /**
     * Converts the elements of the tuple to an array.
     * The array contains all elements of the tuple in their natural order,
     * starting from the first element.
     *
     * @return an array containing all elements of the tuple in order
     */
    Object[] toArray();

    /**
     * Returns a sequential {@code Stream} of the elements in the tuple.
     * The elements are streamed in natural order, starting from the first element.
     *
     * @return a sequential {@code Stream} containing all elements of the tuple
     */
    default Stream<Object> stream() {
        return IntStream.range(1, size() + 1)
                .mapToObj(this::get);
    }

    /**
     * Checks if the specified value exists as an element within the tuple.
     * The comparison is done using the {@code equals} method of the elements.
     *
     * @param value the object to check for presence in the tuple (must not be null)
     * @return {@code true} if the tuple contains the specified value,
     *         {@code false} if the value is {@code null} or not found in the tuple
     */
    default boolean contains(Object value) {
        if (value == null)
            return false;
        return stream().anyMatch(v -> v.equals(value));
    }

    /**
     * Returns an iterator over the elements in the tuple. The iterator traverses
     * through each element sequentially in the natural order of the tuple, starting
     * from the first element.
     *
     * @return an {@code Iterator} over the elements of the tuple
     */
    default Iterator<Object> iterator() {
        return stream().iterator();
    }
}
