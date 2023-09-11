import javax.swing.*;
import java.awt.*;

public class WeatherWidget extends JFrame {
    private final JLabel cityLabel, temperatureLabel, airLabel, windLabel, humidityLabel, pressureLabel, dateLabel;
    public WeatherWidget() {
        super("Weather Widget");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(360, 540);
        setLocationRelativeTo(null);

        cityLabel = new JLabel("City", JLabel.CENTER);
        cityLabel.setFont(new Font("Arial", Font.BOLD, 25));

        dateLabel = new JLabel("Date", JLabel.CENTER);
        dateLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        temperatureLabel = new JLabel("Temperature", JLabel.CENTER);
        temperatureLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        airLabel = new JLabel("Air", JLabel.CENTER);
        airLabel.setFont(new Font("", Font.PLAIN, 18));

        windLabel = new JLabel("Wind", JLabel.CENTER);
        windLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        humidityLabel = new JLabel("Humidity", JLabel.CENTER);
        humidityLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        pressureLabel = new JLabel("Pressure", JLabel.CENTER);
        pressureLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 1));
        panel.add(cityLabel);
        panel.add(dateLabel);
        panel.add(temperatureLabel);
        panel.add(airLabel);
        panel.add(windLabel);
        panel.add(humidityLabel);
        panel.add(pressureLabel);
        add(panel);
    }

    public void setCity(String city) {
        cityLabel.setText(city);
    }
    public void setDate(String date) {
        dateLabel.setText(date);
    }
    public void setTemperature(String temperature) {
        temperatureLabel.setText("Temperature: " + temperature + "°C");
    }
    public void setAir(String air) {
        airLabel.setText("Air: " + air);
    }
    public void setWind(String wind) {
        windLabel.setText("Wind: " + wind + " m/s");
    }
    public void setHumidity(String humidity) {
        humidityLabel.setText("Humidity: " + humidity + "%");
    }
    public void setPressure(String pressure) {
        pressureLabel.setText("Pressure: " + pressure + " mmHg");
    }
}