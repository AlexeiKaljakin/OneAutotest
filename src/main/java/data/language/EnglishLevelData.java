package data.language;

public enum EnglishLevelData {
    FIRSTLEVEL("Начальный уровень (Beginner)");
    private String nameEnglishLevel;
    EnglishLevelData(String nameEnglishLevel) {
        this.nameEnglishLevel = nameEnglishLevel;
    }
    public String getEnglishLevel() {
        return nameEnglishLevel;
    }
}
