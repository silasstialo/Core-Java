# 🏦 Java Multithreaded Bank Transfer Simulation with Locks
This project simulates a concurrent banking system where multiple threads perform random money transfers between accounts in a shared bank instance. It demonstrates advanced thread synchronization using explicit locks (ReentrantLock) and condition variables (Condition) for safe concurrent operations.

## 🚀 Features
- Shared Bank object accessed by multiple threads performing transfers.

- Each account initialized with a balance of 1000.0 units.

- Thread-safe money transfer using explicit locking (ReentrantLock) and condition waiting (Condition.await() / Condition.signalAll()).

- Transfers block when insufficient funds are available, waking when funds are replenished.

- Randomized transfer amounts and delays simulate real-world asynchronous banking operations.

- Proper interruption handling during waiting and sleeping.

## 📁 Components
**Bank.java**
>Uses ReentrantLock for explicit mutual exclusion.

>Uses a Condition object (sufficientFunds) to wait when an account lacks funds.

>transfer(from, to, amount) method safely transfers money with locking and conditional waits.

>Maintains and reports total balance consistency.

**BankTransfer.java**
>Implements Runnable.

>Continuously performs random transfers between different accounts of the shared bank.

>Uses randomized delays and amounts.

**Main.java**
>Creates one shared Bank with 5000 accounts.

>Starts multiple BankTransfer threads operating on the shared bank concurrently.


## 🧵 Thread Safety Details
- Bank uses ReentrantLock for fine-grained control over synchronization.

- Threads wait on Condition when funds are insufficient, avoiding busy waiting.

- Interruptions during waiting and sleeping are handled gracefully to stop threads cleanly.