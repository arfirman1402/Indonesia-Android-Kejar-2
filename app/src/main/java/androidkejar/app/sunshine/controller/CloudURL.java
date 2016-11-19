package androidkejar.app.sunshine.controller;

/**
 * Created by alodokter-it on 05/11/16.
 */

public class CloudURL {
    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";
    private static final String CURRENT_LOCATION = "Pekanbaru";
    private static final String API_KEY = "a1da57e96e1d31099ae46599fe4e6687";
    private static final String UNITS_METRIC = "metric";
    private static final String DATA_FORMAT = "json";
    private static final String DATA_COUNT = "16";

    public static String getCurrentWeather() {
        return BASE_URL
                + "weather?q=" + CURRENT_LOCATION
                + "&units=" + UNITS_METRIC
                + "&appid=" + API_KEY;
    }

    public static String getImageWeather(String icon) {
        return "http://openweathermap.org/img/w/" + icon + ".png";
    }

    public static String getListWeather() {
        return BASE_URL
                + "forecast/daily?q=" + CURRENT_LOCATION
                + "&mode=" + DATA_FORMAT
                + "&units=" + UNITS_METRIC
                + "&cnt=" + DATA_COUNT
                + "&appid=" + API_KEY;
    }


}
