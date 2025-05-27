import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

public class RemoveLogs implements Runnable{
    private final BlockingQueue<String> blockingQueue;
    private final String POISON_PILL;

    public RemoveLogs(BlockingQueue<String> queue, String PILL){
        this.blockingQueue = queue;
        this.POISON_PILL = PILL;
    }

    @Override
    public void run() {
        //get the users home directory
        String homeDir = System.getProperty("user.home");

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(homeDir + File.separator +  "log.txt", true))){
            while (true){
                //Continuously remove elements from the blocking queue
                //take() Retrieves and removes the head of this queue, waiting if necessary until an element becomes available.
                String log = blockingQueue.take();

                //Break the loop if you encounter the poison pill
                if(log.equals(POISON_PILL)){
                    break;
                }
                writer.write(log);
                //add a new line after adding a log
                writer.newLine();
                writer.flush();
                System.out.println(Thread.currentThread().getName() + " Removed " + log + "\n");
            }

        }

        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Thread interrupted while taking from queue.");
        }

        catch (IOException e) {
            System.err.println("IO error while writing to log file: " + e.getMessage());
        }
    }
}
