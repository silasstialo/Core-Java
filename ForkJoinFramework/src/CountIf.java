import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.function.Predicate;

/*To put the recursive computation in a form that is usable by the framework,
    supply a class that extends RecursiveTask<T> (if the computation produces a
    result of type T) or RecursiveAction (if it doesn’t produce a result). Override
    the compute method to generate and invoke subtasks, and to combine their
    results.

 */

public class CountIf<T> extends RecursiveTask<Integer> {
    private final int from;
    private final int to;
    private final Predicate<T> filter;
    private final int parallelismThreshold;
    private final List<T> targetList;

    public CountIf(List<T> list, int start, int end, Predicate<T> filterFunction, int threshold){
        this.targetList = list;
        this.from = start;
        this.to = end;
        this.filter = filterFunction;
        this.parallelismThreshold = threshold;
    }

    @Override
    protected Integer compute() {
        if((to - from) <= parallelismThreshold){
            //solve the problem directly
            int count = 0;
            for(int i = from; i < to; i++){
                if(filter.test(targetList.get(i))){
                    count++;
                }
            }

            return  count;
        }

        else{
            //divide the problem into sub problems and solve the separately
            int mid = from + (to - from) / 2;

            //result for the first half
            var first = new CountIf<>(targetList, from, mid, filter, parallelismThreshold);

            //result for the second half
            var second = new CountIf<>(targetList, mid, to, filter, parallelismThreshold);

            /*
            combine the results of the halves
            invokeAll(first, second);
            return first.join() + second.join();
            */

            first.fork();  // Better than invokeAll for two tasks
            return second.compute() + first.join();
        }
    }
}
