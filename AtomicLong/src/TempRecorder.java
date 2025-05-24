import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class TempRecorder implements Runnable{
    private final AtomicLong maxTemp;
    private final Random random = new Random();

    public TempRecorder(AtomicLong max){
        this.maxTemp = max;
    }


    @Override
    public void run() {
        //simulate the recording of a temperature sensor recording random temperature readings
        for(int i = 0; i<20; i++){

            //generate random numbers from 0 to 100
            double observed = random.nextInt(101);

            //update and get maxTemp to observed if observed is larger than maxTemp
            //store the return value in updated just for printing or another use
            long updated = maxTemp.updateAndGet(current -> (long) Math.max(current, observed));

            System.out.println(Thread.currentThread().getName() + " Observed max :" + updated);
        }
    }
}
