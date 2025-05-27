public enum Level {
    LOW(3),
    MEDIUM(2),
    HIGH(1),
    CRITICAL(0);

    private final int levelCode;

    Level(int code){
        this.levelCode = code;
    }

    public int getLevelCode() {
        return levelCode;
    }
}
