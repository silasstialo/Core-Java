# 🔄 Concurrent Map Bulk Operations and Modifications in Java

This project demonstrates advanced usage of `ConcurrentHashMap` in Java by concurrently modifying the map and performing bulk operations (like `search`, `reduce`, and `forEach`) using multithreading.

---

## 📌 Overview

- 🧠 Uses `ConcurrentHashMap` for thread-safe key-value operations.
- 🔁 Simultaneously modifies the map and runs bulk read operations.
- 🧵 Uses multiple threads: one for modifying the map and one scheduled to perform bulk operations periodically.
- 🕰 Demonstrates Java 8+ features like `forEach`, `searchValues`, and `reduceValues`.


---

## 🧱 Components

### 🔹 `Main.java`

- Initializes the map with 100 randomly generated UUID keys, each mapped to a value of 100.
- Launches two executor services:
  - `modifyMapExecutors`: Updates values in the map.
  - `periodicBulkOperatorExecutor`: Periodically (every 1 second) performs bulk operations.
- Prevents main thread from exiting using `CountDownLatch`.

---

### 🔹 `ModifyMap.java`

Continuously modifies the values of the map using `merge`:

```java
mapToModify.forEachKey(1, key -> 
    mapToModify.merge(key, 0, (oldVal, ignored) ->
        oldVal + ThreadLocalRandom.current().nextInt(-100, 101))
);
