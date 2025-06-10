import java.util.Arrays;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        List<Integer> numbersList = Arrays.asList(30, 12, 900, 43, 77, 457, 366, 322, 44, 9, 1, 3999, 4223, 4782, 111, 900, 234, 12, 43, 79, 5555, 89, 90, 43, 23, 123, 321, 677, 67, 98, 4444, 123, 100, 99, 765, 456, 630, 888, 900, 988, 70, 50, 499, 544, 34);
        List<String> wordList = Arrays.asList("The", "PostgreSQL", "server", "can", "handle", "multiple", "concurrent",
                "connections", "from", "clients", "To", "achieve", "this", "it", "starts",
                "forks", "a", "new", "process", "for", "each", "connection", "From",
                "that", "point", "on", "the", "client", "and", "the", "new", "server",
                "process", "communicate", "without", "intervention", "by", "the",
                "original", "postgres", "process", "Thus", "the", "supervisor",
                "server", "process", "is", "always", "running", "waiting", "for",
                "client", "connections", "whereas", "client", "and", "associated",
                "server", "processes", "come", "and", "go");

        //count numbers less than 100
        int lessThan100 = new CountIf<>(numbersList, 0, numbersList.size(), n -> n < 100, 5).compute();
        System.out.println("Numbers less than 100: " + lessThan100 + "\n");

        //count numbers more than 100
        int moreThanEqualTo100 = new CountIf<>(numbersList, 0, numbersList.size(), n -> n >= 100, 5).compute();
        System.out.println("Numbers more than 100: " + moreThanEqualTo100 + "\n");

        //count words whose length is more than 7
        int wordsLongerThan7 = new CountIf<>(wordList, 0, wordList.size(), word -> word.length() >= 7, 10).compute();
        System.out.println("words longer than 7 characters long: " + wordsLongerThan7 + "\n");

        //count words containing "w" in them
        int wordsContainingW = new CountIf<>(wordList, 0, wordList.size(), word -> word.contains("w"), 10).compute();
        System.out.println("Words containing w:" + wordsContainingW+ "\n");


    }
}