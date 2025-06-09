import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

public class BulkOperator implements Runnable{
            /*
        No effort is made to freeze a snapshot of the map in time. Unless you
        happen to know that the map is not being modified while a bulk operation
        runs, you should treat its result as an approximation of the map’s state.

        Each operation has four versions:
        • operationKeys: operates on keys.
        • operationValues: operates on values.
        • operation: operates on keys and values.
        • operationEntries: operates on Map.Entry objects.
         */
    private final ConcurrentHashMap<String, Integer> targetMap;

    public BulkOperator(ConcurrentHashMap<String, Integer> map){
        this.targetMap = map;
    }

    @Override //runs bulk operations on the map whilst it is being modified
    public void run() {
        System.out.println("Bulk operator running ...");
        //search for a random value
        //• search applies a function to each key and/or value, until the function yields
        //           a non-null result. Then the search terminates and the function’s result is
        //           returned.

        //A random value to be searched
        int target = ThreadLocalRandom.current().nextInt(-100, 101);
        int searchResult = targetMap.searchValues(1, value -> {
            if(value == target){
                return value;
            }
            return null;
        });

        System.out.println("search results: " + searchResult + "\n");


        //• reduce combines all keys and/or values, using a provided accumulation function.
        int sum = targetMap.reduceValues(1, Integer::sum);
        long mapSize = targetMap.mappingCount();
        long average = mapSize == 0? 0 : sum/mapSize;
        System.out.println("Average: " + average + "\n");


        //• forEach applies a function to all keys and/or values.
        targetMap.forEach(1, (key, value) -> System.out.println("Key :" + key + " Value : " + value + " "));
        System.out.println();


    }
}
