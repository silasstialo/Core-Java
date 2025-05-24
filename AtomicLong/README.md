# 🌡️ Concurrent Max Temperature Recorder

---

This Java program demonstrates how to track the maximum temperature recorded by multiple threads using the thread-safe AtomicLong class.

## 🧩 Overview
- Simulates temperature recordings from 3 sensor threads.

- Each thread generates 20 random temperature readings (0 to 100).

- Uses AtomicLong.updateAndGet() to safely store the highest observed temperature across all threads.

- Ensures all threads complete before printing the final max temperature.

## 📦 Technologies Used
- Java 8+

- java.util.concurrent.atomic.AtomicLong

- java.lang.Thread

- java.util.Random

## 🛠️ How It Works
***TempRecorder class:***
>Implements Runnable. Each instance simulates temperature readings and updates the shared AtomicLong with the highest observed value.

***Main class:***

>Creates a shared AtomicLong (maxTemp).

>Starts three threads with TempRecorder instances.

>Waits for all threads to finish using join().

>Prints the final maximum temperature.


## ✅ Key Concepts
***Thread Safety:*** AtomicLong.updateAndGet() ensures atomic updates to shared data.

***Random Number Generation:*** Simulates real-world sensor behavior.

***Concurrency:*** Demonstrates basic thread creation, execution, and synchronization.

## 📌 Notes
- The initial value of AtomicLong is 0 by default.

- The use of (long) Math.max(current, observed) ensures only higher values are retained.
