# 🚀 Asynchronous Task Execution with Future and Callable in Java

This project demonstrates how to execute a task asynchronously using `Callable`, `Future`, and `ExecutorService` in Java. It simulates a long-running task and shows how to retrieve its result using a future with a timeout mechanism.

---

## 📌 Overview

- ✅ Uses `Callable` to define a task that returns a result.
- ✅ Uses `Future` to hold the result of the asynchronous computation.
- ✅ Manages task execution via `ExecutorService`.
- ✅ Demonstrates timeout and graceful shutdown handling.

---

## 📁 Project Structure

project-root/
├── Main.java
├── MyCallable.java
└── README.md


---

## 🧱 Components

### `MyCallable.java`

Implements the `Callable<String>` interface and simulates a task that takes 10 seconds to complete using `Thread.sleep()`.

```java
@Override
public String call() throws Exception {
    Thread.sleep(10000);
    return "Done";
}
