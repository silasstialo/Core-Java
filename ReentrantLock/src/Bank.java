import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {
    private final int size;
    private static final double MAX_AMOUNT = 1000;
    private final double[] accounts;
    private final Condition sufficientFunds;
    private final Lock bankLock = new ReentrantLock(); // ReentrantLock implements the Lock interface

    public Bank(int bankSize){
        //associate the bankLock with the sufficientFunds condition
        sufficientFunds = bankLock.newCondition();

        this.size = bankSize;
        //the indexes will act as the account number and the values will be the amounts in the accounts
        this.accounts = new double[bankSize];
        Arrays.fill(accounts, 1000.0);
    }



    // CAUTION: unsafe when called from multiple threads
    public void transfer(int from, int to, double amount)
    {
        //lock the code block
        bankLock.lock();

        try {
            //check if the account you want to transfer from has enough money
            //if not, wait until(hopefully) another thread add money to the account
            while(accounts[from] < amount){
                try{
                    sufficientFunds.await();
                } catch (InterruptedException e) {
                    // Restore interrupt status and exit
                    Thread.currentThread().interrupt();
                    return;
                }
            }

            //perform the transfer first
            accounts[from] -= amount;
            accounts[to] += amount;

            //print the updated values
            System.out.println(Thread.currentThread().getName());
            System.out.printf("%10.2f from %d to %d\n", amount, from, to);
            System.out.printf("total balance: %10.2f\n", getTotalBalance());

            //unblock the waiting threads
            sufficientFunds.signalAll();
        }
        finally {
            //unlock the code block
            bankLock.unlock();
        }

    }

    public int getSize(){
        return this.size;
    }

    public double getMaxAmount(){
        return MAX_AMOUNT;
    }

    public double getTotalBalance(){
        double sum = 0;
        for(double i : accounts){
            sum += i;
        }
        return sum;
    }
}