import javax.swing.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите название города – ");
        String city_name = in.nextLine();
        String city = city_name.substring(0, 1).toUpperCase() + city_name.substring(1);

        if (isValidCity(city)) {
            Weather weather = new Weather();
            ArrayList<String> weather_ctg = weather.getWeather(city);

            Date dateNow = new Date();
            SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd.MM.yyyy");
            String date = formatForDateNow.format(dateNow);

            SwingUtilities.invokeLater(() -> {
                WeatherWidget widget = new WeatherWidget();
                widget.setVisible(true);
                widget.setCity(city);
                widget.setDate(date);
                widget.setTemperature(weather_ctg.get(0));
                widget.setAir(weather_ctg.get(1));
                widget.setWind(weather_ctg.get(2));
                widget.setHumidity(weather_ctg.get(3));
                widget.setPressure(weather_ctg.get(4));
            });
        } else {
            System.out.println("Некорректное название города!");
        }
    }
    public static boolean isValidCity(String city) {
        return city.matches("[a-zA-Z\\-]+");
    }
}