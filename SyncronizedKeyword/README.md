# 🏦 Multithreaded Bank Transfer Simulation – Java
This project simulates a simple multithreaded banking system using Java. It models multiple bank accounts where concurrent threads perform random money transfers between accounts.

## 📌 Features
- Multiple threads simulate concurrent transfers between accounts.

- Each account starts with a default balance of 1000.0.

- Thread-safe transfer logic using synchronized, wait(), and notifyAll() to prevent race conditions.

- Randomized delays and amounts mimic real-world asynchronous behavior.

- Automatic balance consistency check after each transfer.

## 🧩 Components
**Bank.java**
>Maintains account balances in an array.

>Provides synchronized transfer and getTotalBalance methods.

>Uses wait() and notifyAll() to handle insufficient funds safely in a multithreaded environment.

**BankTransfer.java**
>Implements Runnable to allow parallel execution in threads.

>Performs randomized money transfers between different accounts.

>Ensures each transfer only proceeds when fromAccount ≠ toAccount.

**Main.java**
>Initializes the bank and spawns multiple threads, each executing BankTransfer.

>Demonstrates concurrent banking behavior in action.

▶

## ⚠️ Thread Safety Notes
- The transfer() method uses synchronized blocks and condition waiting (wait/notifyAll) to ensure data integrity.

- Properly handles InterruptedException to allow clean thread exits.


## 🛠️ Technologies Used
- Java 8+

- Core Java (Threads, Synchronization, wait/notify)

