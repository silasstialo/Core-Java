
public class UpdateRecommendationsTask implements Runnable{
    private final String orderID;

    public UpdateRecommendationsTask(String id){
        this.orderID = id;
    }

    @Override
    public void run() {
        System.out.println("Updating recommendations after order :" + orderID);

        //simulate delay in updating recommendations
        try{
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
