import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WeatherService {
    private final String urlAddress = "https://api.openweathermap.org/data/2.5/weather?q=";
    private final ArrayList<String> weatherList = new ArrayList<>();

    public WeatherInfo getWeather(String city) throws IOException {
        WeatherInfo weatherInfo = new WeatherInfo(city, weatherList);

        URL url = new URL(urlAddress + city + "&APPID=7d8350edceb83ec279fd60d5294f1bdd");
        HttpURLConnection connection;
        connection = (HttpURLConnection) url.openConnection();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder intermediateLine = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null){
            intermediateLine.append(line);
        }
        String str = intermediateLine.toString();

        weatherInfo.getWeatherData().add(temperatureParser(str));
        weatherInfo.getWeatherData().add(airParser(str));
        weatherInfo.getWeatherData().add(windParser(str));
        weatherInfo.getWeatherData().add(humidityParser(str));
        weatherInfo.getWeatherData().add(pressureParser(str));

        return weatherInfo;
    }

    private String airParser(String rawtxt) {
        String regEx = "\"description\\\":\\\"(?:[^\\\"]|\\\"\\\")*\\\"";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(rawtxt);
        int startIndex = 0;
        int endIndex = 0;
        while (matcher.find()) {
            startIndex = matcher.start();
            endIndex = matcher.end();
        }
        String a = rawtxt.substring(startIndex, endIndex);
        return a.substring(a.indexOf("\"description\":\"") + 15, a.lastIndexOf('"'));
    }

    private String temperatureParser(String rawtxt) {
        String regEx = "\"temp\":....";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(rawtxt);
        int startIndex = 0;
        int endIndex = 0;
        while (matcher.find()) {
            startIndex = matcher.start();
            endIndex = matcher.end();
        }
        String a = rawtxt.substring(startIndex, endIndex);
        a = a.substring(a.indexOf("\"temp\":") + 7, a.lastIndexOf('.'));
        int temp = Integer.parseInt(a);
        temp -= 273;
        return Integer.toString(temp);
    }

    private String pressureParser(String rawtxt) {
        String regEx = "\"pressure\":....,";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(rawtxt);
        int startIndex = 0;
        int endIndex = 0;
        while (matcher.find()) {
            startIndex = matcher.start();
            endIndex = matcher.end();
        }
        String a = rawtxt.substring(startIndex, endIndex);
        a = a.substring(a.indexOf("\"pressure\":") + 11, a.lastIndexOf(','));
        double pressure = Double.parseDouble(a);
        pressure = pressure / 1.333;
        return Double.toString((int)pressure);
    }

    private String humidityParser(String rawtxt) {
        String regEx = "humidity\":..";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(rawtxt);
        int startIndex = 0;
        int endIndex = 0;
        while (matcher.find()) {
            startIndex = matcher.start();
            endIndex = matcher.end();
        }
        String a = rawtxt.substring(startIndex, endIndex);
        return  a.substring(a.indexOf("humidity\":") + 10);
    }

    private String windParser(String rawtxt) {
        String regEx = "\"wind\":\\{[^}]*\\}";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(rawtxt);
        int startIndex = 0;
        int endIndex = 0;
        while (matcher.find()) {
            startIndex = matcher.start();
            endIndex = matcher.end();
        }
        String a = rawtxt.substring(startIndex, endIndex);
        return  a.substring(a.indexOf("\"wind\":") + 16, a.lastIndexOf("deg") - 2);
    }
}
