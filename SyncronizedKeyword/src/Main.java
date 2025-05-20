public class Main {
    public static void main(String[] args) {
        /// The threads should operate on one shared Bank object instead of
        /// every thread creating a new instance
        Bank sharedBank = new Bank(5000);
        Thread transfer1 = new Thread(new BankTransfer(sharedBank), "Transfer1");
        transfer1.start();
        Thread transfer2 = new Thread(new BankTransfer(sharedBank), "Transfer2");
        transfer2.start();
    }
}
