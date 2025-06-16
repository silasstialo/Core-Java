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
        final String orderID = "ORD_" + UUID.randomUUID();

        CompletableFuture<String> paymentFuture = checkPaymentAsync(orderID);
        CompletableFuture<Boolean> inventoryFuture = checkInventoryAsync(orderID);

        //invoke the shipment only if the inventory and the payment tasks succeed
        CompletableFuture<String> shipmentFuture = inventoryFuture.thenCombine(paymentFuture, (inventoryAvailable, transactionID) ->{
            if(!inventoryAvailable){
                throw new CompletionException(new RuntimeException("Inventory not found"));
            }

            if(transactionID == null || transactionID.isEmpty()){
                throw new CompletionException(new RuntimeException("Payment not found"));
            }

            return processShipment(orderID);
        });

        //update recommendations independently and asynchronously
        CompletableFuture.runAsync(() -> updateRecommendations(orderID), executor);

        //send confirmation email after the shipment task completes
        CompletableFuture<Void> confirmationFuture = shipmentFuture.thenAcceptAsync(
                trackingID -> sendConfirmationMessage(orderID, trackingID), executor);

        //wait for the confirmation task to complete and catch errors
        try {
            confirmationFuture.join();
            System.out.println("Order processing complete for order : " + orderID);
        }
        catch (CompletionException e){
            System.err.println("Order processing failed: " + e.getCause() + e.getMessage());
        }

        finally {
            executor.shutdown();
        }

    }


    //A method to check for payment
    //returns the transaction ID
    public static CompletableFuture<String> checkPaymentAsync(String orderID) {
        return CompletableFuture.supplyAsync(() ->{
            String transactionID = "TRANSACT_" + UUID.randomUUID(); //hard coded transaction ID
            System.out.println("Checking payment for order:" + orderID);
            System.out.println("Transaction ID: " + transactionID+ "\n");

            //simulate delay in computation
            sleep(2000);

            return transactionID;
        }, executor);
    }

    //A method to check the inventory
    //returns true if the inventory is available and false otherwise
    public static CompletableFuture<Boolean> checkInventoryAsync(String orderID) {
        return CompletableFuture.supplyAsync(() ->{
            boolean inventoryIsAvailable = true; //hard coded availability of inventory
            System.out.println("checking the inventory for order: " + orderID + ". Inventory available: " + inventoryIsAvailable + "\n");

            //simulate delay
            sleep(2000);

            return inventoryIsAvailable;
        }, executor);
    }

    //A method to process shipment(only if both the payment and inventory are available
    //returns the tracking ID
    public static String processShipment(String orderID) {
        String trackingID = "TRACK_" + UUID.randomUUID();
        System.out.println("Processing shipment for order: " + orderID + ". Tracking ID: " + trackingID + "\n" );

        //simulate delay
        sleep(2000);

        return trackingID;
    }

    //A method to send a confirmation message only after shipment is processed
    //no return value
    public static void sendConfirmationMessage(String orderID, String trackingID) {
        System.out.println("sending confirmation message for order: " + orderID + ". Tracking ID: " + trackingID + "\n");

        //simulate delay
        sleep(2000);
    }

    //A method to update recommendations
    //It is an independent method and can start as soon as the order is placed
    public static void updateRecommendations(String orderID) {
        System.out.println("Updating recommendations on order: " + orderID + "\n");

        //simulate delay
        sleep(2000);
    }

    //the sleep method
    public static void sleep(long milliSec){
        try{
            Thread.sleep(milliSec);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}