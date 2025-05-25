import java.util.Random;
import java.util.UUID;

public class Task implements Runnable{
    private final ThreadLocal<User> currentUser;
    static String[] names = {"James", "Alice", "Joy", "Jude", "Daniel", "Victor", "Adam", "Brian", "Easton", "Mary", "Ivanova", "Linda"};


    public Task(ThreadLocal<User> current){
        this.currentUser = current;
    }


    @Override
    public void run() {
        Random random = new Random();

        //generate Random user details

        String uID = UUID.randomUUID().toString();
        //pick a random name from the names array
        String uName = names[random.nextInt(0, names.length - 1)];
        int uAge = random.nextInt(18, 75);

        currentUser.set(new User(uID, uName, uAge));

        //extract the user object from ThreadLocal
        User user = currentUser.get();
        System.out.println(Thread.currentThread().getName() + "User -> " + "\n" +
                "ID : " + user.getID() + "\n" + "Name : " + user.getName() +
                "\n" + "Age : " + user.getAge() + "\n");

        //cleanup
        currentUser.remove();
    }
}
