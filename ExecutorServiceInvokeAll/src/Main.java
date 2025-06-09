import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.*;
import java.util.stream.Stream;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //a list to store all tasks
        List<Callable<Optional<String>>> tasks = new ArrayList<>();

        String pathToDirectory = "res/dir0/";

        //a thread pool to execute the tasks
        ExecutorService executor = Executors.newFixedThreadPool(8);

        /*
            List<Callable<T>> tasks = . . .;
            List<Future<T>> results = executor.invokeAll(tasks);
                for (Future<T> result : results)
                processFurther(result.get());

            In the for loop, the first call result.get() blocks until the first result is
            available. That is not a problem if all tasks finish in about the same time.
            However, it may be worth obtaining the results in the order in which they are
            available. This can be arranged with the ExecutorCompletionService
         */
        var service = new ExecutorCompletionService<Optional<String>>(executor);

        //create a task for each file found in the directory
        try (Stream<Path> stream = Files.walk(Paths.get(pathToDirectory))) {
            stream.filter(Files::isRegularFile)
                    .forEach((file) -> {
                        //create a new task
                        tasks.add(new SearchWordsTask(file.toString(), "network"));
                    });
        } catch (IOException e) {
            System.out.println("Reading directory failed");
        }

        //submit the task to the executor
        for (Callable<Optional<String>> task : tasks){
            service.submit(task);
        }

        //get the futures in the order in which they are completed
        try{
            for(int i = 0; i < tasks.size(); i++){
                Optional<String> result = service.take().get();
                //only process present values
                result.ifPresent(System.out::println);
            }
        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        catch (ExecutionException e) {
            throw new RuntimeException(e);
        }


        //initiate shutdown of the executor
        executor.shutdown();

        try{
            if(!executor.awaitTermination(1, TimeUnit.MINUTES)){
                System.out.println("Executor could not complete in time");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            executor.shutdown();
        }

    }
}