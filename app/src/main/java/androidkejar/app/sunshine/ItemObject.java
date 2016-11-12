package androidkejar.app.sunshine;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alodokter-it on 12/11/16.
 */

public class ItemObject {
    public static class ListWeather {
        private String judul;

        public ListWeather(String judul) {
            this.judul = judul;
        }

        public String getJudul() {
            return judul;
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
            private int temp;

            public int getTemp() {
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
