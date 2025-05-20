public class BankTransfer implements Runnable{
    private final  Bank bank;

    public BankTransfer(Bank bank) {
        this.bank = bank;
    }

    @Override
    public void run(){
        int DELAY = 200;
        while (true)
        {
            int toAccount = (int) (bank.getSize() * Math.random());
            int fromAccount = (int) (bank.getSize() * Math.random());

            //check if the two accounts are the same and only proceed if they are different
            if(toAccount != fromAccount){
                double amount = bank.getMaxAmount() * Math.random();
                bank.transfer(fromAccount, toAccount, amount);
            }
            try {
                Thread.sleep((int) (DELAY * Math.random()));
            } catch (InterruptedException e) {
                //restore the interrupt status and exit the loop
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}
