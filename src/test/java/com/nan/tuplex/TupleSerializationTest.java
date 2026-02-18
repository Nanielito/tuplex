package com.nan.tuplex;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.junit.jupiter.api.Test;

import static com.nan.tuplex.TupleBaseTest.deserialize;
import static com.nan.tuplex.TupleBaseTest.serialize;

/**
 * Defines a contract for testing the serialization and deserialization of tuple data structures.
 *
 * @param <T> the type of tuple being tested, extending from the {@code Tuple} interface
 */
interface TupleSerializationTest<T extends Tuple> extends TupleTest<T> {

    @Test
    default void shouldSerializeAndDeserialize() throws IOException, ClassNotFoundException {
        T original = createTuple();
        byte[] bytes = serialize(original);
        T restored = deserialize(bytes);

        assertInstanceOf(original.getClass(), restored);
        assertEquals(original, restored);
        assertEquals(original.hashCode(), restored.hashCode());
    }
}
