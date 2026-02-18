package com.nan.tuplex;

/**
 * A utility class for creating tuples of varying sizes. This class provides static factory
 * methods that allow for the creation of tuples with a fixed number of elements, ranging from
 * a single element to an arbitrary number of elements using varargs.
 * <p>
 * The tuples created by these methods are immutable and provide access to their elements through
 * their respective tuple types (e.g., Tuple1, Tuple2, etc.). Each tuple type is suited for a specific
 * number of elements and ensures type safety for its components.
 */
public final class Tuples {

    private Tuples() {}

    /**
     * Creates a single-element tuple (Tuple1) containing the specified value.
     *
     * @param <A> the type of the value to be encapsulated within the tuple
     * @param a the value to be included in the tuple (must not be null)
     * @return a Tuple1 instance encapsulating the provided value
     * @throws NullPointerException if the specified value is null
     */
    public static <A> Tuple of(A a) {
        return Tuple1.of(a);
    }

    /**
     * Creates a two-element tuple (Tuple2) with the specified values.
     *
     * @param <A> the type of the first element in the tuple
     * @param <B> the type of the second element in the tuple
     * @param a the value of the first element (must not be null)
     * @param b the value of the second element (must not be null)
     * @return a Tuple2 instance containing the specified values
     * @throws NullPointerException if either of the specified values is null
     */
    public static <A, B> Tuple of(A a, B b) {
        return Tuple2.of(a, b);
    }

    /**
     * Creates a three-element tuple (Tuple3) with the specified values.
     *
     * @param <A> the type of the first element in the tuple
     * @param <B> the type of the second element in the tuple
     * @param <C> the type of the third element in the tuple
     * @param a the value of the first element (must not be null)
     * @param b the value of the second element (must not be null)
     * @param c the value of the third element (must not be null)
     * @return a Tuple3 instance containing the specified values
     * @throws NullPointerException if any of the specified values is null
     */
    public static <A, B, C> Tuple of(A a, B b, C c) {
        return Tuple3.of(a, b, c);
    }

    /**
     * Creates a four-element tuple (Tuple4) with the specified values.
     *
     * @param <A> the type of the first element in the tuple
     * @param <B> the type of the second element in the tuple
     * @param <C> the type of the third element in the tuple
     * @param <D> the type of the fourth element in the tuple
     * @param a the value of the first element (must not be null)
     * @param b the value of the second element (must not be null)
     * @param c the value of the third element (must not be null)
     * @param d the value of the fourth element (must not be null)
     * @return a Tuple4 instance containing the specified values
     * @throws NullPointerException if any of the specified values is null
     */
    public static <A, B, C, D> Tuple of(A a, B b, C c, D d) {
        return Tuple4.of(a, b, c, d);
    }

    /**
     * Creates a five-element tuple (Tuple5) with the specified values.
     *
     * @param <A> the type of the first element in the tuple
     * @param <B> the type of the second element in the tuple
     * @param <C> the type of the third element in the tuple
     * @param <D> the type of the fourth element in the tuple
     * @param <E> the type of the fifth element in the tuple
     * @param a the value of the first element (must not be null)
     * @param b the value of the second element (must not be null)
     * @param c the value of the third element (must not be null)
     * @param d the value of the fourth element (must not be null)
     * @param e the value of the fifth element (must not be null)
     * @return a Tuple5 instance containing the specified values
     * @throws NullPointerException if any of the specified values is null
     */
    public static <A, B, C, D, E> Tuple of(A a, B b, C c, D d, E e) {
        return Tuple5.of(a, b, c, d, e);
    }

    /**
     * Creates a six-element tuple (Tuple6) with the specified values.
     *
     * @param <A> the type of the first element in the tuple
     * @param <B> the type of the second element in the tuple
     * @param <C> the type of the third element in the tuple
     * @param <D> the type of the fourth element in the tuple
     * @param <E> the type of the fifth element in the tuple
     * @param <F> the type of the sixth element in the tuple
     * @param a the value of the first element (must not be null)
     * @param b the value of the second element (must not be null)
     * @param c the value of the third element (must not be null)
     * @param d the value of the fourth element (must not be null)
     * @param e the value of the fifth element (must not be null)
     * @param f the value of the sixth element (must not be null)
     * @return a Tuple6 instance containing the specified values
     * @throws NullPointerException if any of the specified values is null
     */
    public static <A, B, C, D, E, F> Tuple of(A a, B b, C c, D d, E e, F f) {
        return Tuple6.of(a, b, c, d, e, f);
    }

    /**
     * Creates a seven-element tuple (Tuple7) with the specified values.
     *
     * @param <A> the type of the first element in the tuple
     * @param <B> the type of the second element in the tuple
     * @param <C> the type of the third element in the tuple
     * @param <D> the type of the fourth element in the tuple
     * @param <E> the type of the fifth element in the tuple
     * @param <F> the type of the sixth element in the tuple
     * @param <G> the type of the seventh element in the tuple
     * @param a the value of the first element (must not be null)
     * @param b the value of the second element (must not be null)
     * @param c the value of the third element (must not be null)
     * @param d the value of the fourth element (must not be null)
     * @param e the value of the fifth element (must not be null)
     * @param f the value of the sixth element (must not be null)
     * @param g the value of the seventh element (must not be null)
     * @return a Tuple7 instance containing the specified values
     * @throws NullPointerException if any of the specified values is null
     */
    public static <A, B, C, D, E, F, G> Tuple of(A a, B b, C c, D d, E e, F f, G g) {
        return Tuple7.of(a, b, c, d, e, f, g);
    }

    /**
     * Creates an eight-element tuple (Tuple8) with the specified values.
     *
     * @param <A> the type of the first element in the tuple
     * @param <B> the type of the second element in the tuple
     * @param <C> the type of the third element in the tuple
     * @param <D> the type of the fourth element in the tuple
     * @param <E> the type of the fifth element in the tuple
     * @param <F> the type of the sixth element in the tuple
     * @param <G> the type of the seventh element in the tuple
     * @param <H> the type of the eighth element in the tuple
     * @param a the value of the first element (must not be null)
     * @param b the value of the second element (must not be null)
     * @param c the value of the third element (must not be null)
     * @param d the value of the fourth element (must not be null)
     * @param e the value of the fifth element (must not be null)
     * @param f the value of the sixth element (must not be null)
     * @param g the value of the seventh element (must not be null)
     * @param h the value of the eighth element (must not be null)
     * @return a Tuple8 instance containing the specified values
     * @throws NullPointerException if any of the specified values is null
     */
    public static <A, B, C, D, E, F, G, H> Tuple of(A a, B b, C c, D d, E e, F f, G g, H h) {
        return Tuple8.of(a, b, c, d, e, f, g, h);
    }

    /**
     * Creates a tuple with an arbitrary number of elements using the specified values.
     *
     * @param values the values to be included in the tuple (must not be null)
     * @return a TupleN instance containing the specified values
     * @throws NullPointerException if the provided values array is null
     */
    public static Tuple of(Object... values) {
        return TupleN.of(values);
    }
}
