import java.util.concurrent.Callable;

public class MyCallable implements Callable<String> {
    @Override
    public String call() throws Exception {

        //simulate delay in computation
        Thread.sleep(10000);
        return "Done";
    }
}
