class NameTooLongException extends Exception{
    public NameTooLongException()
    {
        super("The name cannot be longer than 30 letters long");
    }

}

public class CustomException2 {
    static void testNameLength(String name) throws NameTooLongException{
        //remove white spaces before calculating the length of the string
        if (name.replace(" ", "").length() > 30){
            throw new NameTooLongException();
        }

        else {
            System.out.println("The length of the name " + name + " is okay");
        }
    }

    public static void main(String[] a){
        try {
            testNameLength("qwer  ytyiuf  gbtnvdgruivs  cpkmsua vx");
        }
        catch (NameTooLongException e){
            System.out.println(e);
        }
    }
}
