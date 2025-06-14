
public class ConfirmationEmailTask implements Runnable {
    private final String orderID;
    private final String trackingID;

    public ConfirmationEmailTask(String order, String tracking){
        this.orderID = order;
        this.trackingID = tracking;
    }

    @Override
    public void run() {
        System.out.println("Sending confirmation Email for order :" + orderID + " , TRACK_" + trackingID);

        //simulate delay in sending confirmation email
        try{
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
