package data.countrycityes;

public enum CountryData {
    RUSSIA("Россия");
    private String nameCountry;
    CountryData(String nameCountry) {
        this.nameCountry = nameCountry;
    }
    public String getNameCountry() {
        return nameCountry;
    }
}
