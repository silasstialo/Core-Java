public class User {
    private final String userID;
    private final String username;
    private final int userAge;

    public User(String ID, String name, int age){
        this.userID = ID;
        this.username = name;
        this.userAge = age;
    }

    public String getID(){
        return this.userID;
    }

    public String getName(){
        return this.username;
    }

    public int getAge(){
        return this.userAge;
    }
}
