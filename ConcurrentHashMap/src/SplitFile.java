import java.io.*;
import java.util.concurrent.BlockingQueue;

public class SplitFile implements Runnable {
    private final BlockingQueue<String> taskList;
    private final String filePath;

    public SplitFile(BlockingQueue<String> tasks, String file){
        this.taskList = tasks;
        this.filePath = file;
    }

    @Override
    public void run() {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(filePath);
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
            System.exit(1);
        }

        try (BufferedReader reader = new BufferedReader(fileReader)) {
            String line;
            StringBuilder paragraph = new StringBuilder();

            //Read the file line by line until you encounter a new line
            while ((line = reader.readLine()) != null){
                if(line.trim().isEmpty()){
                    //end of paragraph
                    if(!paragraph.isEmpty()){
                        taskList.put(paragraph.toString().trim()); //add the paragraph to the blocking queue
                        paragraph.setLength(0); //empty the string builder
                    }
                }
                else{
                    if(!paragraph.isEmpty()){
                        paragraph.append(" ");//add space between lines
                    }
                    paragraph.append(line.trim().replaceAll("[^a-zA-Z0-9\\s]", " ")
                            .toLowerCase());
                            // Replace punctuation with space
                            // Normalize to lowercase
                            // add the line to the string builder
                }
            }

            //after adding the paragraphs, the last one may not be added if it does not end with an empty line
            //add it
            if(!paragraph.isEmpty()){
                taskList.put(paragraph.toString().trim());
            }
        }


        //catch exceptions
        catch (InterruptedException | IOException e){
            Thread.currentThread().interrupt();
        }
    }
}
