import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Weather {
    public final ArrayList<String> weather_list = new ArrayList<>();
    private final String urlAddress = "https://api.openweathermap.org/data/2.5/weather?q=";
    public ArrayList<String> getWeather(String city) throws IOException {
        URL url = new URL(urlAddress + city + "&APPID=7d8350edceb83ec279fd60d5294f1bdd");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder intermediateLine = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null){
            intermediateLine.append(line);
        }
        String str = intermediateLine.toString();
        weather_list.add(temperature_parser(str));
        weather_list.add(air_parser(str));
        weather_list.add(wind_parser(str));
        weather_list.add(humidity_parser(str));
        weather_list.add(pressure_parser(str));

        return weather_list;
    }

    private String air_parser(String rawtxt) {
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

    private String temperature_parser(String rawtxt) {
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

    private String pressure_parser(String rawtxt) {
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

    private String humidity_parser(String rawtxt) {
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

    private String wind_parser(String rawtxt) {
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