package androidkejar.app.sunshine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

import androidkejar.app.sunshine.controller.CloudConnecting;
import androidkejar.app.sunshine.controller.CloudResult;
import androidkejar.app.sunshine.controller.CloudURL;
import androidkejar.app.sunshine.controller.adapter.ListAdapter;

public class MainActivity extends AppCompatActivity {

    CloudConnecting connecting;
    String url;

    RecyclerView lstSunshineItem;
    LinearLayoutManager linearLayoutManager;
    ItemObject.ListWeather listWeather;
    ListAdapter listAdapter;

    TextView txtTime;
    TextView txtCelcius;
    TextView txtLocation;
    ImageView imgSunshine;

    ItemObject.CurrentWeather currentWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setElevation(0);

        txtTime = (TextView) findViewById(R.id.txt_time);
        txtCelcius = (TextView) findViewById(R.id.txt_celcius);
        txtLocation = (TextView) findViewById(R.id.txt_location);
        imgSunshine = (ImageView) findViewById(R.id.img_sunshine);

        linearLayoutManager = new LinearLayoutManager(this);
        lstSunshineItem = (RecyclerView) findViewById(R.id.lst_sunshine_item);
        lstSunshineItem.setLayoutManager(linearLayoutManager);
        lstSunshineItem.setHasFixedSize(true);

        getData();
        getDataList();
    }

    private void getData() {
        connecting = new CloudConnecting();
        url = CloudURL.getCurrentWeather();

        Log.d("getData", "url = " + url);

        connecting.getData(getApplicationContext(), url, new CloudResult() {
            @Override
            public void resultData(String response) {
                Log.d("resultData", response);

                setCurrentWeather(response);
            }

            @Override
            public void errorResultData(String errorResponse) {
                Log.d("errorResultData", errorResponse);
                Toast.makeText(getApplicationContext(), "Error Connection, check logcat for details.", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getDataList() {
        connecting = new CloudConnecting();
        url = CloudURL.getListWeather();

        Log.d("getData", "url = " + url);

        connecting.getData(getApplicationContext(), url, new CloudResult() {
            @Override
            public void resultData(String response) {
                Log.d("resultData", response);

                setListWeather(response);
            }

            @Override
            public void errorResultData(String errorResponse) {
                Log.d("errorResultData", errorResponse);
                Toast.makeText(getApplicationContext(), "Error Connection, check logcat for details.", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setListWeather(String response) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();

        listWeather = gson.fromJson(response, ItemObject.ListWeather.class);
        listAdapter = new ListAdapter(getApplicationContext(), listWeather.getListOfWeathers());

        lstSunshineItem.setAdapter(listAdapter);

    }

    private void setCurrentWeather(String response) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        currentWeather = gson.fromJson(response, ItemObject.CurrentWeather.class);

        txtLocation.setText(currentWeather.getName());

        double doubleTemp = currentWeather.getMainTemperature().getTemp();
        int intTemp = (int) Math.round(doubleTemp);

        String temperature = intTemp + " \u2103";
        txtCelcius.setText(temperature);

        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        List<String> listDay = Arrays.asList(BaseApp.Dates.getListDay());
        List<String> listMonth = Arrays.asList(BaseApp.Dates.getListMonth());
        String time = listDay.get(gregorianCalendar.get(GregorianCalendar.DAY_OF_WEEK) - 1) + ", " + gregorianCalendar.get(GregorianCalendar.DAY_OF_MONTH) + " " + listMonth.get(gregorianCalendar.get(GregorianCalendar.MONTH)) + " " + gregorianCalendar.get(GregorianCalendar.YEAR);
        txtTime.setText(time);

        Glide.with(getApplicationContext())
                .load(CloudURL.getImageWeather(currentWeather.getWeathers().get(0).getIcon()))
                .centerCrop()
                .fitCenter()
                .crossFade()
                .into(imgSunshine);
    }

}