# 🔍 Parallel Word Search in Files using ExecutorCompletionService

This Java project demonstrates how to search for a specific word across multiple files in parallel using Java's `ExecutorCompletionService` and `Optional<String>` to efficiently collect results as tasks complete.

---

## 📌 Overview

- Each file in a target directory is searched for a specific word using a separate task (`SearchWordsTask`).
- All tasks return an `Optional<String>` containing the file path **only if** the word is found.
- Results are processed in the **order they complete**, not in the order they were submitted.
- No exceptions are thrown for "not found" cases—`Optional.empty()` is returned instead.

---

## 📁 Project Structure

project-root/
├── res/
│ └── dir0/
│ ├── file1.txt
│ ├── file2.txt
│ └── ...
├── Main.java
├── SearchWordsTask.java
└── README.md

yaml
Copy
Edit

---

## ⚙️ How It Works

1. **Collect all files** from the specified directory (`res/dir0/`).
2. **Create a task** for each file to search for the target word (e.g., `"network"`).
3. Submit all tasks to an `ExecutorCompletionService`.
4. Retrieve and process results **as tasks complete** using `CompletionService.take().get()`.
5. Use `Optional` to filter out unsuccessful matches.
6. Print only the paths of files where the word was found.

---

## ▶️ Running the Project

### ✅ Prerequisites

- Java 8 or higher
- A folder named `res/dir0/` containing text files to search

### 🛠️ Compile

```bash
javac Main.java SearchWordsTask.ja
