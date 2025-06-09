# Parallel Word Search in Files

---

This Java project demonstrates how to perform a **parallel search** for a specific word in multiple text files using Java's `ExecutorService` and `invokeAny`.

## 🧠 Overview

- Each file in a specified directory is assigned to a `Callable<String>` task (`SearchWordsTask`).
- These tasks search for a given word within the file.
- If the word is found, the task returns the file path.
- If not, the task throws an exception.
- `ExecutorService.invokeAny()` is used to:
  - Return the result of the **first successful task**.
  - Cancel all remaining tasks once a result is found.
  - Throw an `ExecutionException` if **all tasks fail** (i.e., the word is not found in any file).
  
  
