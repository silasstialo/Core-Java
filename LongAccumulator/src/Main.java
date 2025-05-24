import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAccumulator;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        int numberOfJobs = 12;
        Random random = new Random();

        //Maximum daily sales across multiple stores , to be updated by several threads concurrently
        LongAccumulator maxSales = new LongAccumulator(Math::max, Long.MIN_VALUE);

        try(ExecutorService executor = Executors.newFixedThreadPool(8)) {


            /*The order of accumulation within or across threads is not guaranteed and cannot be depended upon,
            so this class is only applicable to functions for which the order of accumulation does not matter.
             The supplied accumulator function should be side-effect-free, since it may be re-applied when attempted updates fail due to contention among threads.
             The function is applied with the current value as its first argument, and the given update as the second argument.
              For example, to maintain a running maximum value, you could supply Long::max along with Long.MIN_VALUE as the identity.*/
            Runnable accumulateMaxSales = () -> {
                for (int i = 0; i < 10; i++) {
                    long sales = random.nextLong(1001);
                    maxSales.accumulate(sales);
                }
                };

            //submit <numberOfJobs> tasks to the executor thread pool
            for(int i = 0; i < numberOfJobs; i++){
                executor.execute(accumulateMaxSales);
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

        System.out.println("Maximum observed sales" + maxSales.get());
    }
}