# Tuplex

A small, dependency-free tuples library for Java.

It provides immutable tuple types (`Tuple1` … `Tuple8`, plus `TupleN`) and a common `Tuple` interface for convenient iteration, streaming, and conversion to collections/arrays.

## Features

- **Immutable** tuple implementations (records)
- Tuple arities: **1 → 8** + **N** (varargs)
- Common `Tuple` API:
  - `size()`, `get(int index)` (**1-based index**)
  - `toList()`, `toArray()`
  - `stream()`, `iterator()`, `contains(Object)`
- `Tuples.of(...)` factory methods for easy creation
- Element-wise mapping helpers (e.g., `Tuple2#map`, `map1`, `map2`)

## Requirements

- **Java 25+** (the project is configured with Java in Gradle)

## Installation

This project is currently set up as a Gradle build.

For local development/testing, clone the repo and run tests (see below).

## Usage

### Create tuples

```java
import com.nan.tuplex.Tuple;
import com.nan.tuplex.Tuple2;
import com.nan.tuplex.Tuples;

class Example {
  static void demo() {
    Tuple t1 = Tuples.of("hello");              // Tuple1
    Tuple t2 = Tuples.of("left", 42);           // Tuple2
    Tuple t3 = Tuples.of("a", "b", "c");        // Tuple3
    Tuple tn = Tuples.of(1, 2, 3, 4, 5, 6, 7);  // TupleN (varargs overload also exists)

    System.out.println(t2.size());     // 2
    System.out.println(t2.get(1));     // "left" (index is 1-based)
    System.out.println(t2.left());     // "left
    System.out.println(t2.get(2));     // 42
    System.out.println(t2.right());    // 42
  }
}
```

### Iterate / stream / convert

```java
import com.nan.tuplex.Tuple;
import com.nan.tuplex.Tuples;

class Example {
  static void demo() {
    Tuple t = Tuples.of("a", "b", "c");
    for (Object v : t)
      System.out.println(v);

    t.stream().forEach(System.out::println);

    System.out.println(t.toList());  // ["a", "b", "c"]
    System.out.println(t.contains("b")); // true
  }
}
```

### Map elements (example with `Tuple2`)

```java
import com.nan.tuplex.Tuple2;
import com.nan.tuplex.Tuples;

class Example {
  static void demo() {
    var t = (Tuple2<String, Integer>) Tuples.of("age", 41);
    Tuple2<String, String> mapped = t.map(s -> s.toUpperCase(), i -> "value=" + i);
    Tuple2<String, Integer> leftOnly = t.map1(s -> "[" + s + "]");
    Tuple2<String, Integer> rightOnly = t.map2(i -> i + 1);
  }
}
```

## Indexing note

`Tuple#get(int index)` uses **1-based indexing**:

- `get(1)` → first element
- `get(size())` → last element

## Build & test

Build the project:

```shell
./gradlew build
```

Run the test suite:

```shell
./gradlew test
```

## Package

Main package:

- `com.nan.tuplex`

Key entry points:

- `Tuple` (common interface)
- `Tuples` (static factory methods)
- `Tuple1` … `Tuple8`, `TupleN` (tuple implementations)

## License

This project is licensed under the **MIT License** — see the `LICENSE` file for details.
