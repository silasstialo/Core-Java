# Parallel List Processor
---

A Java implementation of a parallel filtering/counting utility using the Fork/Join framework, capable of processing both numbers and strings with custom predicates.

## Features

- **Generic parallel processing** - Works with `List<T>` of any type (Integers, Strings, etc.)
- **Fork/Join optimization** - Efficiently splits work across available CPU cores
- **Customizable predicates** - Count elements matching any condition
- **Configurable threshold** - Control when to switch from parallel to sequential processing

## Usage

### Basic Example

```java
List<Integer> numbers = Arrays.asList(30, 12, 900, 43, 77, ...);
List<String> words = Arrays.asList("PostgreSQL", "server", "concurrent", ...);

// Count numbers < 100
int smallNumbers = new CountIf<>(
    numbers, 
    0, 
    numbers.size(), 
    n -> n < 100, 
    5
).compute();

// Count long words (length ≥ 7)
int longWords = new CountIf<>(
    words,
    0,
    words.size(),
    word -> word.length() >= 7,
    10
).compute(); 
```

## Constructor Parameters
Parameter	    |Type	        |Description
----------------|---------------|----
list	        | List<T>	    | Input list to process
start	        | int	        | Starting index (inclusive)
end	            | int	        | Ending index (exclusive)
filterFunction	| Predicate<T>	| Condition to test elements
threshold	    | int	        | Minimum size for parallel processing

## Performance Considerations
**Threshold tuning:**

> *Smaller threshold* = more parallelism but more overhead

> *Good default:* list.size() / (Runtime.getRuntime().availableProcessors() * 4)

> *Best for CPU-intensive tasks:* The overhead may not justify use for simple operations on small lists

## Build and Run
 - Requires Java 8+

 - No external dependencies
 
## Design Notes
- Uses RecursiveTask<Integer> from java.util.concurrent

- Implements work stealing algorithm via fork()/join()

- Thread-safe as each task operates on its own sub-range

## Limitations
- Not optimized for primitive arrays (uses boxing for numbers)

- Fixed splitting strategy (always divides in half)
