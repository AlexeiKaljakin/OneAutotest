package data.countryCityes;

public enum RussiaCityData implements ICityData{
    MOSCOW("Москва", CountryData.RUSSIA);
    private String nameCity;
    private CountryData countryData;
    RussiaCityData(String nameCity, CountryData countryData) {
        this.nameCity = nameCity;
        this.countryData = countryData;
    }
    public String getName(){
        return nameCity;
    }
    public CountryData getCountryData() {
        return countryData;
    }
}
