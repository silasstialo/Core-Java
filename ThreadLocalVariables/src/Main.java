import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    //A thread-local variable currentUser
    //each thread will have its own separate instance
    private static final ThreadLocal<User> currentUser = ThreadLocal.withInitial(() -> new User(null, null, 18));

    public static void main(String[] args) {
        //A thread pool to manage the threads
        try(ExecutorService executors = Executors.newFixedThreadPool(8)){
            for(int i = 0 ; i < Task.names.length ; i++) {
                executors.execute(new Task(currentUser));
            }

            executors.shutdown();
            try{
                if(!executors.awaitTermination(1, TimeUnit.MINUTES)){
                    System.out.println("could not complete task in time");
                    executors.shutdown();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

    }
}