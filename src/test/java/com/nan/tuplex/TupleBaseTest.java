package com.nan.tuplex;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Base test class for verifying the behavior and properties of tuple data structures.
 * This class provides reusable serialization and deserialization utilities, along with
 * common constructs for concrete tuple test extensions.
 * <p>
 * Classes extending this base class typically:
 * - Test the structure, equality, conversion, and serialization specifics of tuple data structures.
 * - Implement detailed behavior tests that involve constraints like null handling and value mapping.
 * <p>
 * Key features of this class include:
 * - The `TestValue` record for encapsulating simple test data used in tuple validation.
 * - Utility methods for serializing and deserializing objects, ensuring tuples conform to expected
 *   serialization standards.
 * - Compatibility with various tuple sizes and types by offering an abstraction for common operations.
 * <p>
 * Subclasses of this test class implement specific tuple tests, such as for single-element or multi-element
 * tuples, and inherit or extend its testing utilities for verifying tuple behavior.
 */
public abstract class TupleBaseTest {

    protected record TestValue(String value) implements Serializable {}

    static <T> byte[] serialize(T obj) throws IOException {
        try (var bos = new ByteArrayOutputStream();
             var oos = new ObjectOutputStream(bos)) {
            oos.writeObject(obj);
            return bos.toByteArray();
        }
    }

    @SuppressWarnings("unchecked")
    static <T> T deserialize(byte[] data) throws IOException, ClassNotFoundException {
        try (var bis = new ByteArrayInputStream(data);
             var ois = new ObjectInputStream(bis)) {
            return (T) ois.readObject();
        }
    }
}
