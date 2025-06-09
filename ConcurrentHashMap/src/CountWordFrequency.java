import java.util.Arrays;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

public class CountWordFrequency implements Runnable{
    private final BlockingQueue<String> taskList;
    private final ConcurrentHashMap<String, Integer> wordFrequencyTable;
    private final String POISON_PILL;

    public CountWordFrequency(BlockingQueue<String> list, ConcurrentHashMap<String, Integer> table, String PILL){
        this.taskList = list;
        this.wordFrequencyTable = table;
        this.POISON_PILL = PILL;
    }

    @Override
    public void run() {
        try{

            while (true){
                // Take items out of the blocking queue until you encounter a poison pill
                String paragraph = taskList.take();
                if(paragraph.equals(POISON_PILL)) break; //terminate the loop on encountering the poison pill

                //split the paragraph into words
                Arrays.stream(paragraph.trim().split("\\s+"))
                        .forEach((word ->{
                            //if the hashmap does not contain the word, add it as the key, with a value of 1
                            //add 1 to the value corresponding to the <word> key every time you encounter the word
                            wordFrequencyTable.merge(word, 1, Integer::sum);

                            //alternative
                            //wordFrequencyTable.compute(word, (k, v) -> v == null ? 1 : v + 1);
                        }));
            }
        }

        catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }
}
