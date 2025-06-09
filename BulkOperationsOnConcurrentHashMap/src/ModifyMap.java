import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

public class ModifyMap implements Runnable {
    private final ConcurrentHashMap<String, Integer> mapToModify;

    public ModifyMap(ConcurrentHashMap<String, Integer> map){
        this.mapToModify = map;
    }

    @Override
    public void run() {
        System.out.println("Modify map running...");
        //randomly change values in the map by adding values between -100 and 100
        while (true){
            //Avoids unnecessary value reads until you actually update via merge
            //provide a default value of v -> 0, if v is null (v == null? 0 : v)
            mapToModify.forEachKey(1, key -> mapToModify.merge(key, 0, (oldVal, ignored) ->
                    oldVal + ThreadLocalRandom.current().nextInt(-100, 101))
            );

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
            }

    }
}
