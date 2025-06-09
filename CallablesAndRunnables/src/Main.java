import java.util.concurrent.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        try(ExecutorService executors = Executors.newFixedThreadPool(4)) {

            /*A Future holds the result of an asynchronous computation. You start a
            computation, give someone the Future object, and forget about it. The owner
            of the Future object can obtain the result when it is ready.
            */

            Future<String> future = executors.submit(new MyCallable());

            executors.shutdown();
            try{
                if(!executors.awaitTermination(1, TimeUnit.MINUTES)){
                    System.out.println("Executors failed to terminate in time");
                    executors.shutdown();
                }
            } catch (InterruptedException e) {
                System.out.println("Interrupted while waiting for termination");
                Thread.currentThread().interrupt();
                executors.shutdown();
            }

            try {
                /*
                Waits if necessary for at most the given time for the computation to complete,
                 and then retrieves its result, if available.
                 */
                String result = future.get(1, TimeUnit.MINUTES);
                System.out.println(result);
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                Thread.currentThread().interrupt();
            }
            }
        }
    }
