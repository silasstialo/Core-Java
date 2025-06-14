import java.util.UUID;
import java.util.concurrent.Callable;

public class ShipmentTask implements Callable<String> {
    private final String orderID;


    public ShipmentTask(String order){
        this.orderID = order;
    }

    @Override
    public String call() {
        //generate a random trackingID
        final String trackingID = UUID.randomUUID().toString();

        System.out.println("Processing shipment for order : " + orderID + " ,Tracking ID: TRACK_" + trackingID );

        //simulate delay in processing payment
        try{
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return trackingID;
    }
}
