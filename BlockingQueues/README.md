# Log Processor with BlockingQueue and Thread Pools

---

This project is a Java-based log processing system that demonstrates the use of:
- `BlockingQueue` for thread-safe communication between producer and consumer threads.
- `ExecutorService` for managing a thread pool of producers and a single consumer.
- JSON serialization using Gson.
- File I/O for persisting log data to disk.

---

## 🚀 Features

- **Multi-threaded Producers**: 200 producer jobs are submitted via a thread pool of 7 threads.
- **Single-threaded Consumer**: A single consumer thread continuously removes logs from the queue and writes them to a file.
- **BlockingQueue**: Ensures thread-safe operations and handles backpressure when the queue is full.
- **Poison Pill Pattern**: Used to signal the end of logging once all producer tasks are complete.
- **File Logging**: Logs are written to a file `log.txt` in the user's home directory.

---

## 🛠 Technologies Used

- Java Concurrency (`ExecutorService`, `BlockingQueue`)
- Gson (for converting Java objects to JSON)
- Java I/O (`BufferedWriter`, `FileWriter`)
- Java Enums for log levels

---

## 📋 How It Works

1. The application starts a single consumer thread.
2. Then, 200 producer tasks generate random log entries and place them into a shared `ArrayBlockingQueue`.
3. Once all producers are finished, the main thread inserts a special string (`"done"`) as a **poison pill** to signal the consumer to stop.
4. The consumer reads logs from the queue and writes them to a text file.
5. The program ensures graceful shutdown using `awaitTermination`.

---

## 📄 Output

A file named `log.txt` will be created in the user's home directory. It will contain JSON-formatted log entries, each on a new line.

Example log entry:
```json
{"logID":"a1b2c3d4...","jobLevel":"HIGH","jobTimestamp":"2025-05-27 15:30:25.123"}
```

---
## ✅ Improvements & Considerations
- Use LinkedBlockingQueue for unbounded queue capacity.

- Add more log metadata like thread name or hostname.

- Rotate log files after a threshold (log rotation).

- Unit tests for AddLOgs and RemoveLogs.

---

## ⚠️ Cons
- All logs are buffered in memory (ArrayBlockingQueue) — not optimal for very large-scale production usage.

- Single consumer thread can become a bottleneck under heavy load.

- File I/O is synchronous — writing is blocking and not buffered in batches.




