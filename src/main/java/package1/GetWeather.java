package package1;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

//File -> Project Structure -> libraries -> from Maven -> com.google.code.gson:gson:jar:2.8.6 -> apply
import com.google.gson.*;
import com.google.gson.reflect.*;

public class GetWeather {


    public static Map<String, Object> jsonToMap(String str) {
        Map<String, Object> map = new Gson().fromJson(
                str, new TypeToken<HashMap<String, Object>>() {
                }.getType()
        );
        return map;
    }

    public void weather() {

        String api = "0278bc5b450f2487b9e081098aa9297f"; //API_KEY
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your city:");
        String location = scanner.nextLine();
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
//            System.out.println(result);

            Map<String, Object> respMap = jsonToMap(result.toString());
//           Map<String, Object> listMap = jsonToMap(respMap.get("list").toString());
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
//            System.out.println("Max Temperature: " +  String.format("%.2f",weatherData.maxTemp) + "C");
//            System.out.println("Min Temperature: " +  String.format("%.2f",weatherData.minTemp) + "C");
            System.out.println("Temperature Feels Like: " + String.format("%.0f", weatherData.feelsLike) + "C");
            System.out.println("Current Humidity: " + String.format("%.0f", weatherData.humidity) + "%");
            System.out.println("Wind Speed: " + String.format("%.1f", weatherData.windSpeed) + "m/s");




        } catch (IOException e) {
            try {
                StringBuilder result = new StringBuilder();
                Map<String, Object> respMap = jsonToMap(result.toString());
                URL url = new URL(e.getMessage());
                URLConnection conn = url.openConnection();
                BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                while ((line = rd.readLine()) != null) {
                    result.append(line);
                    System.out.println(line);
                }
                System.out.println(result);
                rd.close();


                System.out.println(e.getMessage());


            } catch (IOException ex) {
//                System.out.println("Error Code: " + respMap.get("cod"));
//                System.out.println("Error Message: " + respMap.get("message"));
                System.out.println("Can't find your city, check your spelling");
//                System.out.println(ex.getMessage());

            }

        }
    }
}

