# 📊 Parallel Word Frequency Counter in Java

This project efficiently counts word frequencies in a large text file using multithreading in Java. It employs a **producer-consumer model** where the file is split into paragraphs and distributed across multiple threads for parallel processing.

---

## 📌 Overview

- **Producer Thread**: Reads a file and splits it into paragraphs.
- **Consumer Threads**: Count word frequencies from each paragraph concurrently.
- **Blocking Queue**: Acts as a thread-safe task list.
- **ConcurrentHashMap**: Stores word frequencies in a thread-safe manner.
- **Poison Pill**: A special signal to notify consumer threads to stop.


---

## 📁 Project Structure

project-root/
├── res/
│ └── text.txt
├── Main.java
├── CountWordFrequency.java
├── SplitFile.java
└── README.md

---

## ▶️ How It Works

1. **SplitFile (Producer)**:
   - Reads a file line by line.
   - Groups lines into paragraphs (split by empty lines).
   - Cleans and normalizes the text.
   - Adds each paragraph to a `BlockingQueue`.

2. **CountWordFrequency (Consumers)**:
   - Consumes paragraphs from the queue.
   - Splits text into words.
   - Updates a shared `ConcurrentHashMap` with word counts using `merge()`.

3. **Main**:
   - Initializes executors and shared data structures.
   - Starts the producer and consumer threads.
   - Uses a *poison pill* to stop consumers after processing is complete.

---


### ✅ Prerequisites

- Java 8 or above
- A text file at `res/text.txt`

### 🛠️ Compile

```bash
javac Main.java CountWordFrequency.java SplitFile.java
