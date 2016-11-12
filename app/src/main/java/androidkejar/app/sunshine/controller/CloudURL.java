package androidkejar.app.sunshine.controller;

/**
 * Created by alodokter-it on 05/11/16.
 */

public class CloudURL {
    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/weather";
    private static final String CURRENT_LOCATION = "Jakarta";
    private static final String API_KEY = "a1da57e96e1d31099ae46599fe4e6687";
    private static final String UNITS_METRIC = "metric";


    public static String getCurrentWeather() {
        return BASE_URL + "?q=" + CURRENT_LOCATION + "&units=" + UNITS_METRIC + "&appid=" + API_KEY;
    }
}
