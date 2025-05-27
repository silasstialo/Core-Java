import java.sql.Timestamp;

public class Job {
    private final String logID;
    private final Level jobLevel;
    private final Timestamp jobTimestamp;

    public Job(String ID, Level level, Timestamp timestamp){
        this.logID = ID;
        this.jobLevel = level;
        this.jobTimestamp = timestamp;
    }

    public  String getLogID(){
        return this.logID;
    }

    public Level getJobLevel(){
        return this.jobLevel;
    }

    public Timestamp getJobTimestamp(){
        return this.jobTimestamp;
    }
}
