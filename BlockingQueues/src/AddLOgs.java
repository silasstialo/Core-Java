import com.google.gson.Gson;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;

public class AddLOgs implements Runnable{
    private final BlockingQueue<String> blockingQueue;
    private final Gson gson = new Gson();

    public AddLOgs(BlockingQueue<String> queue){
        this.blockingQueue = queue;
    }

    @Override
    public void run() {

        //generate a random ID , level and timestamp for the log
        String logID = UUID.randomUUID().toString();

        Level[] levels = Level.values();
        Random random = new Random();
        Level logLevel = levels[random.nextInt(levels.length)];

        Instant timestamp = Instant.now();
        Timestamp jobTimestamp = Timestamp.from(timestamp);

        //convert the job object into its json representation
        Job job = new Job(logID, logLevel, jobTimestamp);
        String jobJson = gson.toJson(job);

        //add the job to the blocking queue
        //This ensures the producer will wait until there's space, instead of freezing the app due to unhandled exceptions.
        try{
            blockingQueue.put(jobJson);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println(Thread.currentThread().getName() +
                " added job  \n" + "ID: " + job.getLogID() +
                " \nLevel: " + job.getJobLevel() +
                " \nTimestamp " + job.getJobTimestamp() + "\n");

        //simulate delay in adding jobs to the queue
        try{
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }
}
