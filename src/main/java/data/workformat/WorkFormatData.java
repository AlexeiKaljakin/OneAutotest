package data.workformat;

public enum WorkFormatData {
    FULLDAY("Полный день"),
    FLEXIBLESCHEDULE("Гибкий график"),
    REMOTELY("Удаленно");
    private String name;
    WorkFormatData(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
