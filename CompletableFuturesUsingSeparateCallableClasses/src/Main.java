import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static final ExecutorService executor = Executors.newFixedThreadPool(5);
    public static void main(String[] args) {
        /*
        - The payment processing and inventory check can be done at the same time (concurrently).

        - Shipping the order can only be done after both payment and inventory check are successful.

        - Sending the confirmation email can only be done after the order is shipped.

        - **Update Recommendations**: After the order is placed, update the customer's product recommendations (this can be done independently and doesn't affect the order)
            can be started as soon as the order is placed and doesn't need to wait for anything else.
         */

        //random transaction ID
        String orderID = "ORDER_" + UUID.randomUUID();

        System.out.println("Order placed. Order ID : " + orderID);

        //completable future for the inventory task
        CompletableFuture<Boolean> inventoryFuture = CompletableFuture.supplyAsync(() ->{
            try {
                return new InventoryTask(orderID).call();
            } catch (Exception e) {
                throw new CompletionException(e);
            }
        }, executor);

        //completable future for the payment task
        CompletableFuture<String> paymentFuture = CompletableFuture.supplyAsync(() ->{
            try{
                return new PaymentTask(orderID).call();
            } catch (Exception e) {
                throw new CompletionException(e);
            }
        }, executor);

        //run shipment only if the payment and inventory tasks succeed
        CompletableFuture<String> shipmentFuture = inventoryFuture.thenCombine(paymentFuture,
                (inventoryIsAvailable, transactionID) ->{
                    if(!inventoryIsAvailable){
                        throw new CompletionException(new RuntimeException("Inventory not found"));
                    }
                    if(transactionID == null || transactionID.isEmpty()){
                        throw new CompletionException(new RuntimeException("Payment not found"));
                    }

                    try{
                        return new ShipmentTask(orderID).call();
                    } catch (Exception e) {
                        throw new CompletionException(e);
                    }
                });

        // Send confirmation email after shipment is completed
        CompletableFuture<Void> confirmationFuture = shipmentFuture.thenAcceptAsync(
                trackingID -> {
                    new ConfirmationEmailTask(orderID, trackingID).run(); //manually run the confirmationEmail task
                }, executor);

        // Run update recommendations independently
        CompletableFuture<Void> updateRecommendationsFuture = CompletableFuture.runAsync(
                new UpdateRecommendationsTask(orderID), executor);

        // Wait for all and handle errors
        try {
            CompletableFuture.allOf(confirmationFuture, updateRecommendationsFuture).join();
            System.out.println("Order processing complete for Order ID: " + orderID);
        } catch (CompletionException e) {
            System.err.println("Order processing failed: " + e.getCause().getMessage());
        } finally {
            executor.shutdown();
        }

    }
}