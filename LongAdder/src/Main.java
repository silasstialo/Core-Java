import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.IntStream;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        int numberOfJobs = 12;

        //A counter that will be incremented from several threads concurrently
        LongAdder counter = new LongAdder();

        /*while you can use a thread pool in a try-with-resources block,
         it is more common to manage thread pools through their lifecycle methods,
         such as shutdown() and shutdownNow(),
         rather than through the try-with-resources mechanism.*/
        try(ExecutorService executor = Executors.newFixedThreadPool(8)){

            //create an Integer stream that returns integers from 0 to 99
            //for each integer generated, increment the counter by 1
            Runnable incrementAction = () -> IntStream
                    .range(0, 100)
                    .forEach(_ -> counter.increment());

            //submit <numberOfJobs> tasks to the executor thread pool
            for(int i = 0; i < numberOfJobs; i++){
                executor.execute(incrementAction);
            }

            executor.shutdown();
            try {
                boolean terminated = executor.awaitTermination(1, TimeUnit.MINUTES);
                if (!terminated) {
                    System.err.println("Executor did not terminate in the specified time.");
                    // Optional: force shutdown or handle accordingly
                    executor.shutdownNow();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                executor.shutdownNow(); // Optional: handle the interruption
            }
        }

        System.out.println(counter.sum());
    }
}