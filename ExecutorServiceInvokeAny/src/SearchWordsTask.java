import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.Callable;
import java.util.stream.Stream;

public class SearchWordsTask implements Callable<String> {
    private final String filePath; //path to the file
    private final String targetWord; //the word to search in the file

    public SearchWordsTask(String path, String word){
        this.filePath = path;
        this.targetWord = word;
    }

    @Override
    public String call() throws Exception {

        try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
            //break the file into lines, normalize the lines and break them into words
            boolean found = stream.anyMatch((line) -> {
                //remove punctuation marks, convert to lowe case and split into words
                String[] words = line.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");

                //compare every word to the target word
                for (String word : words) {
                    if (word.equalsIgnoreCase(targetWord)) {
                        return true;
                    }
                }

                return false;
            });

            if (found) {
                return filePath;
            }

            //ensure no task returns null because it will be counted as a valid return, and invokeAny will terminate.
            //throw an exception instead of returning null.
            //the other tasks will continue running.
            //if all tasks throw exceptions, invokeAny() throws an ExecutionException, wrapping the last exception thrown.
            else {
                throw new IllegalStateException("Word not found in: " + filePath);
            }
        }
    }
}