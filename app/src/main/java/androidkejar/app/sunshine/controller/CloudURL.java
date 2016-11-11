package androidkejar.app.sunshine.controller;

/**
 * Created by alodokter-it on 05/11/16.
 */

public class CloudURL {
    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?";
    private static final String API_KEY = "&appid=a1da57e96e1d31099ae46599fe4e6687";


    public static String getCurrentWeather(String location) {
        return BASE_URL + "q=" + location + API_KEY;
    }
}
