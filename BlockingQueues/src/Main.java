
import java.util.concurrent.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    private static final BlockingQueue<String> queue = new ArrayBlockingQueue<>(100);
    private static  final String POISON_PILL = "done";


    public static void main(String[] args) {
        ExecutorService producerThreads = Executors.newFixedThreadPool(7);
        ExecutorService consumerThread = Executors.newSingleThreadExecutor();

        //start the consumer thread first
        consumerThread.execute(new RemoveLogs(queue, POISON_PILL));

        //start the producer threads
        for(int i = 0; i < 200; i++) {
            producerThreads.execute(new AddLOgs(queue));
        }

        producerThreads.shutdown();
        consumerThread.shutdown();

        try{
            if(!producerThreads.awaitTermination(1, TimeUnit.MINUTES)){
                System.out.println("Producer executor could not complete in time");
                producerThreads.shutdown();
            }

            //add a poison pill to indicate to the consumer thread that the producers are done adding elements
            else {
                queue.put(POISON_PILL);// Only one if you have a single consumer
            }


            if(!consumerThread.awaitTermination(1, TimeUnit.MINUTES)){
                System.out.println("Consumer executor could not complete in time");
                consumerThread.shutdown();
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            producerThreads.shutdown();
            consumerThread.shutdown();
        }

    }
}