import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;
import java.awt.image.BufferedImage;

public class Main {
    public static void main(String[] args) throws IOException {
        final BufferedImage background = ImageIO.read(new File("C:\\Users\\MSI\\IdeaProjects\\Weather API\\imgs\\background.png"));
        final BufferedImage cloud = ImageIO.read(new File("C:\\Users\\MSI\\IdeaProjects\\Weather API\\imgs\\cloud.png"));
        final BufferedImage rain = ImageIO.read(new File("C:\\Users\\MSI\\IdeaProjects\\Weather API\\imgs\\rain.png"));
        final BufferedImage sun = ImageIO.read(new File("C:\\Users\\MSI\\IdeaProjects\\Weather API\\imgs\\sun.png"));
        final BufferedImage snow = ImageIO.read(new File("C:\\Users\\MSI\\IdeaProjects\\Weather API\\imgs\\snow.png"));
        final BufferedImage clear = ImageIO.read(new File("C:\\Users\\MSI\\IdeaProjects\\Weather API\\imgs\\clear.png"));

        ArrayList<BufferedImage> imgs = new ArrayList<>();
        imgs.add(sun);
        imgs.add(snow);
        imgs.add(cloud);
        imgs.add(rain);
        imgs.add(clear);

        Scanner in = new Scanner(System.in);
        System.out.print("Введите название города – ");
        String cityName = in.nextLine();
        String city = cityName.substring(0, 1).toUpperCase() + cityName.substring(1);

        if (isValidCity(city)) {
            // Создание данных
            WeatherService weatherService = new WeatherService();
            WeatherInfo weatherInfo = weatherService.getWeather(city);
            ArrayList<String> weatherCtg = weatherInfo.getWeatherData();

            Date dateNow = new Date();
            SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd.MM.yyyy");
            String date = formatForDateNow.format(dateNow);

            // Запись данных
            Graphics title = background.getGraphics();
            Graphics characteristic = background.getGraphics();

            title.setFont(title.getFont().deriveFont(70f));
            title.drawString(city, 100, 125);
            characteristic.setFont(title.getFont().deriveFont(38f));
            characteristic.drawString(date, 100, 250);

            ImageObserver imageObserver = (img, infoflags, x, y, width, height) -> false;
            title.drawImage(picStatus(imgs, weatherCtg.get(1)), 540, 22, 170, 170, imageObserver);

            characteristic.drawString("Temp: " + weatherCtg.get(0) + "°C", 100, 350);
            characteristic.drawString("Air: " + weatherCtg.get(1), 100, 450);
            characteristic.drawString("Wind: " + weatherCtg.get(2) + " m/s", 100, 550);
            characteristic.drawString("Humidity: " + weatherCtg.get(3) + "%", 100, 650);
            characteristic.drawString("Pressure: " + weatherCtg.get(4)+ " mmHg", 100, 750);
            title.dispose();

            ImageIO.write(background, "png", new File("result img\\weather.png"));

        } else System.out.println("Некорректное название города!");
    }

    public static boolean isValidCity(String city) {
        return city.matches("[a-zA-Z\\-]+");
    }
    public static BufferedImage picStatus(ArrayList<BufferedImage> imgs, String stat) {
        final String[] status = new String[]{"sun", "snow", "cloud", "rain", "clear"};
        BufferedImage imgStatus = null;
        for (int i = 0; i < imgs.size(); i++) {
            if(stat.contains(status[i])) {
                imgStatus = imgs.get(i);
            }
        }
        return imgStatus;
    }
}