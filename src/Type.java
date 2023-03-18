public enum Type {
    WORK ("рабочая задача"),
    PERSONAL("личная задача");
    private final String value;

    Type(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}