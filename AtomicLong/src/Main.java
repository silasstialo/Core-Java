import java.util.concurrent.atomic.AtomicLong;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        AtomicLong maxTemp = new AtomicLong();

        Thread thread1 = new Thread(new TempRecorder(maxTemp), "TempRecorder1");
        Thread thread2 = new Thread(new TempRecorder(maxTemp), "TempRecorder2");
        Thread thread3 = new Thread(new TempRecorder(maxTemp), "TempRecorder3");

        thread1.start();
        thread2.start();
        thread3.start();

        //Wait for the other threads to
        try{
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Max observed temp :" + maxTemp);
    }
}