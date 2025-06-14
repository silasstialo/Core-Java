import java.util.concurrent.Callable;

public class InventoryTask implements Callable<Boolean> {
    private final String orderID;

    public InventoryTask(String id){
        this.orderID = id;
    }
    @Override
    public Boolean call() {
        final boolean inventoryAvailable = true;

        System.out.println("Checking the inventory for order: " + orderID + " , Inventory available: " + inventoryAvailable);

        //simulate delay in checking the inventory
        try{
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return inventoryAvailable;

    }
}
