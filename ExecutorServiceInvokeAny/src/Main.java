import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Stream;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //a list to store all tasks
        List<Callable<String>> tasks = new ArrayList<>();

        String pathToDirectory = "res/dir0/";

        //create a task for each file found in the directory
        try (Stream<Path> stream = Files.walk(Paths.get(pathToDirectory))) {
            stream.filter(Files::isRegularFile)
                    .forEach((file) -> {
                        //create a new task and add it to the task list
                        tasks.add(new SearchWordsTask(file.toString(), "network"));
                    });
        } catch (IOException e) {
            System.out.println("Reading directory failed");
        }

        try(ExecutorService executor = Executors.newFixedThreadPool(8)){

            //submit the task to the executor
            try{
                String result = executor.invokeAny(tasks);
                System.out.println(result);
            }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
         /*
            If one of the submitted Callable tasks throws an exception,
            and invokeAny() tries to return its result,
            the method wraps the original exception inside an ExecutionException.
            So, ExecutionException is a wrapper around the actual exception thrown by a task.
        */
            catch (ExecutionException e) {
                System.err.println("All tasks failed. Last exception was: " + e.getCause());
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
}