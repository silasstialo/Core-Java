import java.util.UUID;
import java.util.concurrent.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        int mapSize =  100;
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>(100);

        //fill the map with random keys and a value of 100 each
        for(int i = 0; i < mapSize; i++){
            map.put(UUID.randomUUID().toString(), 100);
        }

        //the executors should keep running , no need to use try-with-resources
        ExecutorService modifyMapExecutors = Executors.newFixedThreadPool(7);
        ScheduledExecutorService periodicBulkOperatorExecutor = Executors.newSingleThreadScheduledExecutor();

        // Schedule to run every 1 second with no initial delay
        periodicBulkOperatorExecutor.scheduleAtFixedRate(new BulkOperator(map), 0, 1, TimeUnit.SECONDS);

        modifyMapExecutors.execute(new ModifyMap(map));


        // ❗ Prevent main thread from exiting
        try {
            new CountDownLatch(1).await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}