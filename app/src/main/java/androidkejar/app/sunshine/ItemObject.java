package androidkejar.app.sunshine;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by alodokter-it on 12/11/16.
 */

public class ItemObject {

    public static class ListWeather {

        @SerializedName("list")
        private List<ListOfWeather> listOfWeathers;

        public List<ListOfWeather> getListOfWeathers() {
            return listOfWeathers;
        }

        public class ListOfWeather {

            @SerializedName("dt")
            private int datetime;

            @SerializedName("temp")
            private Temperature temp;

            public class Temperature {
                @SerializedName("day")
                private double day;

                public double getDay() {
                    return day;
                }
            }

            @SerializedName("pressure")
            private String pressure;

            @SerializedName("humidity")
            private String humidity;

            @SerializedName("speed")
            private String speed;

            @SerializedName("weather")
            private List<Weather> weather;

            public class Weather {
                @SerializedName("main")
                private String main;

                @SerializedName("description")
                private String description;

                @SerializedName("icon")
                private String icon;

                public String getMain() {
                    return main;
                }

                public String getDescription() {
                    return description;
                }

                public String getIcon() {
                    return icon;
                }
            }

            public int getDatetime() {
                return datetime;
            }

            public Temperature getTemp() {
                return temp;
            }

            public String getPressure() {
                return pressure;
            }

            public String getHumidity() {
                return humidity;
            }

            public String getSpeed() {
                return speed;
            }

            public List<Weather> getWeather() {
                return weather;
            }
        }
    }

    public static class CurrentWeather {
        @SerializedName("name")
        private String name;

        public String getName() {
            return name;
        }

        @SerializedName("weather")
        private List<Weather> weathers;

        @SerializedName("main")
        private MainTemperature mainTemperature;

        public List<Weather> getWeathers() {
            return weathers;
        }

        public MainTemperature getMainTemperature() {
            return mainTemperature;
        }

        class MainTemperature {
            @SerializedName("temp")
            private double temp;

            public double getTemp() {
                return temp;
            }
        }

        class Weather {
            @SerializedName("icon")
            private String icon;

            public String getIcon() {
                return icon;
            }
        }

    }

}
