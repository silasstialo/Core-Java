import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.stream.Stream;

public class SearchWordsTask implements Callable<Optional<String>> {
    private final String filePath; //path to the file
    private final String targetWord; //the word to search in the file

    public SearchWordsTask(String path, String word){
        this.filePath = path;
        this.targetWord = word;
    }

    @Override
    public Optional<String> call() throws Exception {

        try(Stream<String> stream = Files.lines(Paths.get(filePath))){
            //break the file into lines, normalize the lines and break them into words
            boolean found = stream.anyMatch((line) ->{
                //remove punctuation marks, convert to lowe case and split into words
                String[] words = line.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");

                //compare every word to the target word
                for(String word : words){
                    if(word.equalsIgnoreCase(targetWord)){
                        return true;
                    }
                }

                return false;
            });

            //return nothing if no match was found , and filepath if a match was found
            //You have to use Optional.String as the return type for the call() method
            //You also have to implement the Callable<Optional<String>> callable
            return found? Optional.of(filePath) : Optional.empty();
        }
    }
}
