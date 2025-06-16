# Asynchronous Order Processing with CompletableFuture

---

This project illustrates how to use Java's `CompletableFuture` to implement a non-blocking, asynchronous workflow for order processing. The system simulates an e-commerce scenario where various tasks are executed concurrently to optimize performance and responsiveness.

## 📦 Features

- Asynchronous task execution using `CompletableFuture`
- Custom `ExecutorService` for thread control
- Graceful error handling with `CompletionException`
- Parallel execution of independent tasks
- Functional chaining of dependent tasks

## 🛒 Workflow

1. **Order Placement**  
   A random order ID is generated.

2. **Parallel Tasks**  
   - `checkPaymentAsync` (returns a transaction ID)  
   - `checkInventoryAsync` (returns availability status)  
   Both are started in parallel.

3. **Dependent Shipment Task**  
   `processShipment` is only triggered if:
   - Inventory is available  
   - Payment was successful  
   It returns a tracking ID.

4. **Confirmation Email**  
   Sent after shipment completes, using the tracking ID.

5. **Recommendation Update**  
   Executed independently and does not block other tasks.
   
   
## 🛠️ Technologies

- Java 8+
- `java.util.concurrent.CompletableFuture`
- `ExecutorService` for thread management

## 🧵 Threading Model

- All asynchronous tasks are dispatched using a fixed thread pool (`Executors.newFixedThreadPool(5)`).
- `runAsync` and `supplyAsync` are used to offload computation.
- Blocking calls like `join()` are only used for final task synchronization.

## 🚨 Error Handling

- If either payment or inventory check fails, the system throws a `CompletionException` and stops further processing.
- Errors are logged, and the executor service is shut down gracefully.


## 📌 Notes

- All delays are simulated using `Thread.sleep(...)` wrapped in a utility method.
- To test failure scenarios, randomness is injected into payment and inventory logic.
- You can enhance the system by replacing simulated logic with real service calls (e.g., payment gateways, inventory DB, email API).







