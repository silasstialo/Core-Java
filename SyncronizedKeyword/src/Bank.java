import java.util.Arrays;


public class Bank {
    private final int size;
    private static final double MAX_AMOUNT = 1000;
    private final double[] accounts;


    public Bank(int bankSize){
        this.size = bankSize;
        //the indexes will act as the account number and the values will be the amounts in the accounts
        this.accounts = new double[bankSize];
        Arrays.fill(accounts, 1000.0);
    }



    // CAUTION: unsafe when called from multiple threads
    public synchronized void transfer(int from, int to, double amount)
    {
        //check if the account you want to transfer from has enough money
        //if not, wait until(hopefully) another thread add money to the account
        while(accounts[from] < amount) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return; //return to avoid proceeding while interrupted
            }
        }


        //perform the transfer first
        accounts[from] -= amount;
        accounts[to] += amount;

        //print the updated values
        System.out.println(Thread.currentThread().getName());
        System.out.printf("%10.2f from %d to %d\n", amount, from, to);
        System.out.printf("total balance: %10.2f\n", getTotalBalance());

        notifyAll();// notify all threads waiting on the condition
    }




    public int getSize(){
        return this.size;
    }

    public double getMaxAmount(){
        return MAX_AMOUNT;
    }

    public synchronized double getTotalBalance(){
        double sum = 0;
        for(double i : accounts){
            sum += i;
        }
        return sum;
    }
}