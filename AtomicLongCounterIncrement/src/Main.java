import java.util.concurrent.atomic.AtomicLong;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        AtomicLong counter = new AtomicLong(0);

        CountIncrementer incrementer = new CountIncrementer(counter);

        //Threads to increment the counter concurrently
        Thread thread1 = new Thread(incrementer, "Thread1");
        Thread thread2 = new Thread(incrementer, "Thread2");

        thread1.start();
        thread2.start();

        //wait for both threads to finish computing
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        //now, print the final value of counter
        System.out.println("Final value: " + counter);

    }
}