import java.util.ArrayList;

public class WeatherInfo {
    private final String city;
    private final ArrayList<String> weatherData;

    public WeatherInfo(String city, ArrayList<String> weatherData) {
        this.city = city;
        this.weatherData = weatherData;
    }

    public ArrayList<String> getWeatherData() {
        return weatherData;
    }

    public String getCity() {
        return city;
    }
}