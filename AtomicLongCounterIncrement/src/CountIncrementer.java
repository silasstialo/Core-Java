import java.util.concurrent.atomic.AtomicLong;

public class CountIncrementer implements Runnable{
    private final AtomicLong counter;
    public CountIncrementer(AtomicLong counterValue){
        this.counter = counterValue;
    }

    @Override
    public void run() {
        for(int i=0 ; i<100000; i++){

            //Atomically increments counter.
            //Returns the updated value, which is stored in currentValue just for display or use in that scope.
            long currentValue = counter.incrementAndGet();
            System.out.println(Thread.currentThread().getName() + " counter = " + currentValue );
        }
    }
}
