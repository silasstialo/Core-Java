# Async Order Processing System (Java + CompletableFuture)

---

This project demonstrates an **asynchronous order processing pipeline** using Java's `CompletableFuture` and a custom `ExecutorService`. It simulates an e-commerce workflow where multiple tasks are coordinated efficiently using concurrency.

## 📦 Project Structure

The application simulates the following steps in an online order process:

1. **Inventory Check** (`InventoryTask`)
2. **Payment Processing** (`PaymentTask`)
3. **Shipment Processing** (`ShipmentTask`)
4. **Confirmation Email** (`ConfirmationEmailTask`)
5. **Product Recommendations Update** (`UpdateRecommendationsTask`)

### 🔄 Execution Flow

- **Parallel Tasks:**
  - Inventory check and payment processing are started immediately and run concurrently.
  - Recommendation updates also start immediately and run independently of the main order flow.

- **Dependent Tasks:**
  - Shipment is triggered **only if** both inventory and payment succeed.
  - Confirmation email is sent **only after** shipment is complete.

- **Finalization:**
  - The system waits for both the email and recommendation update to complete before shutting down.

## 🧠 Key Concepts Used

- `CompletableFuture.supplyAsync` and `runAsync`
- `thenCombine`, `thenAcceptAsync`
- Exception handling with `CompletionException`
- Thread pool management with `ExecutorService`

## 📁 Classes Overview

| Class                      | Description                                      |
|----------------------------|--------------------------------------------------|
| `Main`                     | Entry point and orchestrator                     |
| `InventoryTask`            | Simulates inventory check (returns `Boolean`)    |
| `PaymentTask`              | Simulates payment processing (returns `String`)  |
| `ShipmentTask`             | Simulates shipment (returns `String` tracking ID)|
| `ConfirmationEmailTask`    | Sends confirmation email (implements `Runnable`) |
| `UpdateRecommendationsTask`| Updates user recommendations (independent)       |

## Sample Output
```
Order placed. Order ID : ORDER_8a4f...
Checking the inventory for order: ORDER_8a4f..., Inventory available: true
Processing payment for order: ORDER_8a4f... Transaction ID: TRANSACT_f1a2...
Updating recommendations after order : ORDER_8a4f...
Processing shipment for order : ORDER_8a4f..., Tracking ID: TRACK_d8c3...
Sending confirmation Email for order : ORDER_8a4f..., TRACK_d8c3...
Order processing complete for Order ID: ORDER_8a4f...
```

## Error Handling
If inventory is unavailable or payment fails, the shipment and email stages are skipped, and the error is logged:

```Order processing failed: Inventory not found
```

## Thread Management
The app uses a fixed thread pool *(Executors.newFixedThreadPool(5))* to manage task concurrency. The executor is properly shut down after completion.
