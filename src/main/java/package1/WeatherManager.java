package package1;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.google.gson.*;
import com.google.gson.reflect.*;

//File -> Project Structure -> libraries -> from Maven -> com.google.code.gson:gson:jar:2.8.6 -> apply

//<dependencies>
//<dependency>
//<groupId>com.google.code.gson</groupId>
//<artifactId>gson</artifactId>
//<version>2.8.5</version>
//</dependency>
//</dependencies>

public class WeatherManager {

    public static Map<String, Object> jsonToMap(String str) {
        Map<String, Object> map = new Gson().fromJson(
                str, new TypeToken<HashMap<String, Object>>() {
                }.getType()
        );
        return map;
    }

    public static void start() {
        System.out.println("----------WEATHER MANAGER----------");
        showWeatherMenu();
    }

    public static void showWeatherMenu() {
        System.out.println("-----------------------------------");
        System.out.println("Enter 0 to go main menu");
        System.out.println("Enter 1 to get the current weather for a city");
        String userInput = getUserInput();
        if (userInput.equals("0")) {
            return; // back to Main Menu
        } else if (userInput.equals("1")) {
            getCurrentWeather();
        }

    }

    public static void getCurrentWeather() {
        String api = "0278bc5b450f2487b9e081098aa9297f"; //API_KEY
        System.out.println("------------------------------");
        System.out.println("Enter 0 to go back");
        System.out.println("Enter your city:");
        String location = getUserInput();
        if (location.equals("0")) {
            showWeatherMenu();
            return;
        }
        String urlString = "https://api.openweathermap.org/data/2.5/weather?q=" + location + "&units=metric" + "&appid=" + api;
        try {
            StringBuilder result = new StringBuilder();
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();


            Map<String, Object> respMap = jsonToMap(result.toString());

            Map<String, Object> mainMap = jsonToMap(respMap.get("main").toString());
            Map<String, Object> windMap = jsonToMap(respMap.get("wind").toString());
            Map<String, Object> sysMap = jsonToMap(respMap.get("sys").toString());
            WeatherData weatherData = new WeatherData();
            weatherData.currentTemp = (Double) mainMap.get("temp");
            weatherData.minTemp = (Double) mainMap.get("temp_min");
            weatherData.maxTemp = (Double) mainMap.get("temp_max");
            weatherData.feelsLike = (Double) mainMap.get("feels_like");
            weatherData.humidity = (Double) mainMap.get("humidity");
            weatherData.windSpeed = (Double) windMap.get("speed");
            weatherData.country = (String) sysMap.get("country");
            weatherData.city = (String) respMap.get("name");

            System.out.println("Country: " + weatherData.country);
            System.out.println("City: " + weatherData.city);
            System.out.println("Current Temperature: " + String.format("%.0f", weatherData.currentTemp) + "C");

            System.out.println("Temperature Feels Like: " + String.format("%.0f", weatherData.feelsLike) + "C");
            System.out.println("Current Humidity: " + String.format("%.0f", weatherData.humidity) + "%");
            System.out.println("Wind Speed: " + String.format("%.1f", weatherData.windSpeed) + "m/s" + "\n");

            System.out.println("Enter 0 to go main menu");
            System.out.println("Enter 1 to get the current weather for another city");

            String userInput = getUserInput();
            if (userInput.equals("0")) {
                return; // go back to main menu
            } else if (userInput.equals("1")) {
                getCurrentWeather();
            }


        } catch (IOException e) {

            System.out.println("Can't find your city");
            System.out.println("Enter 0 to go back");

            String userInput = getUserInput();
            if (userInput.equals("0")) {
                showWeatherMenu();
            }
//
        }
    }

    public static String getUserInput() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        try {
            if (Integer.parseInt(userInput) > 1) {
                System.out.println("Wrong entry, try again");
                userInput = getUserInput();
            }
        } catch (NumberFormatException e) {
            return userInput;
        }

        return userInput;
    }

}


