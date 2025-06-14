import java.util.UUID;
import java.util.concurrent.Callable;

public class PaymentTask implements Callable<String> {
    private final String orderID;

    public PaymentTask(String id){
        this.orderID = id;
    }
    @Override
    public String call(){
        //generate a random transaction ID
        final String transactionID = "TRANSACT_" + UUID.randomUUID();

        System.out.println("Processing payment for order: " + orderID + ". Transaction ID: " + transactionID);

        //simulate delay in processing payment
        try{
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return transactionID;
    }
}
