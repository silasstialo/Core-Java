# ThreadLocal User Simulation with ExecutorService in Java

---

## Overview

This project demonstrates the use of **`ThreadLocal`** in Java to maintain thread-confined user data in a multi-threaded environment. Each thread in a fixed thread pool simulates a separate "current user" by generating and storing random user details (`userID`, `username`, and `userAge`) isolated from other threads.

The example uses:

- A **custom `User` class** representing user details.
- A **`ThreadLocal<User>`** to provide thread-isolated user instances.
- A **fixed thread pool (`ExecutorService`)** to run multiple concurrent tasks.
- Proper **shutdown and cleanup** of thread-local data and thread pools.

---

## Project Structure

- `Main.java` — sets up the thread pool and submits tasks.
- `Task.java` — implements `Runnable`, simulates generating random user data, and sets/retrieves user info via `ThreadLocal`.
- `User.java` — immutable POJO class to hold user information (`ID`, `name`, `age`).

---

## How It Works

1. `Main` creates a fixed thread pool of 8 threads.
2. For each name in the static `Task.names` array, it submits a `Task` to the thread pool.
3. Each `Task` generates:
   - A random UUID for user ID.
   - A random name from the `names` array.
   - A random age between 18 and 75.
4. The generated `User` instance is stored in the thread's `ThreadLocal<User>`.
5. The thread prints the user info, demonstrating thread-local isolation.
6. After execution, the thread-local variable is **removed** to avoid memory leaks.
7. The `ExecutorService` is shut down gracefully, waiting up to 1 minute for tasks to complete.

---
