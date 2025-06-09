import java.util.UUID;
import java.util.concurrent.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {
        final String POISON_PILL = UUID.randomUUID().toString();

        //A blocking queue to keep track of paragraphs to be processed
        BlockingQueue<String> taskList = new LinkedBlockingQueue<>(500);

        //A consumer thread group to execute the CountWordFrequency task
        int cores = Runtime.getRuntime().availableProcessors(); //get the number of processor cores on the system
        int numberOfConsumerThreads = cores * 2;


        //A shared concurrentHashmap to keep track of each word and its corresponding frequency
        ConcurrentHashMap<String, Integer> wordFrequencyTable = new ConcurrentHashMap<>();

        //the file path from which to read the text from
        String filePath = "res/text.txt";

        try(ExecutorService splitFileExecutor = Executors.newSingleThreadExecutor(); //A producer thread group to execute the splitFile task
            ExecutorService countWordFrequencyExecutors = Executors.newFixedThreadPool(numberOfConsumerThreads)) //2 consumer threads per processor core
        {
            //start the consumer first
            splitFileExecutor.execute(new SplitFile(taskList, filePath));

            //then start the consumers
            for(int i = 0; i < numberOfConsumerThreads; i++) {
                countWordFrequencyExecutors.execute(new CountWordFrequency(taskList, wordFrequencyTable, POISON_PILL));
                }

            //initiate systematic shutdown
            splitFileExecutor.shutdown();
            countWordFrequencyExecutors.shutdown();

            try{
                //await the termination of the producer thread then add poison pills
                if(!splitFileExecutor.awaitTermination(1, TimeUnit.MINUTES)) //fails to terminate in time
                {
                    System.out.println("Producer executor could not complete in time");
                    splitFileExecutor.shutdown();
                }

                else {
                    //insert poison pills
                    for (int i = 0; i < numberOfConsumerThreads; i++) {
                        try {
                            taskList.put(POISON_PILL);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }

                //await the termination of the consumer threads
                if(!countWordFrequencyExecutors.awaitTermination(1, TimeUnit.MINUTES)){
                    System.out.println("Consumer executor did not complete in time");
                }
            }

            catch (InterruptedException e){
                Thread.currentThread().interrupt();
                splitFileExecutor.shutdown();
                countWordFrequencyExecutors.shutdown();
            }

        }

        System.out.println(wordFrequencyTable);
    }
}