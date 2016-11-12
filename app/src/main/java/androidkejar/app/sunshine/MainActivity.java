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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import androidkejar.app.sunshine.controller.CloudConnecting;
import androidkejar.app.sunshine.controller.CloudResult;
import androidkejar.app.sunshine.controller.CloudURL;
import androidkejar.app.sunshine.controller.adapter.ListAdapter;

public class MainActivity extends AppCompatActivity implements CloudResult {

    CloudConnecting connecting;
    String url;

    RecyclerView lstSunshineItem;
    LinearLayoutManager linearLayoutManager;
    List<ItemObject.ListWeather> itemObjects;
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

        getData();
        getSupportActionBar().setElevation(0);

        txtTime = (TextView) findViewById(R.id.txt_time);
        txtCelcius = (TextView) findViewById(R.id.txt_celcius);
        txtLocation = (TextView) findViewById(R.id.txt_location);
        imgSunshine = (ImageView) findViewById(R.id.img_sunshine);

        linearLayoutManager = new LinearLayoutManager(this);
        lstSunshineItem = (RecyclerView) findViewById(R.id.lst_sunshine_item);
        lstSunshineItem.setLayoutManager(linearLayoutManager);

        itemObjects = new ArrayList<>();
        itemObjects.add(new ItemObject.ListWeather("Hari ini"));
        itemObjects.add(new ItemObject.ListWeather("Besoknya"));
        itemObjects.add(new ItemObject.ListWeather("Besoknya lagi"));
        itemObjects.add(new ItemObject.ListWeather("Lusa kemaren"));
        itemObjects.add(new ItemObject.ListWeather("Besoknya lusa"));
        itemObjects.add(new ItemObject.ListWeather("Udah gak tahu hari apa"));

        listAdapter = new ListAdapter(this, itemObjects);
        lstSunshineItem.setAdapter(listAdapter);

    }

    private void getData() {
        connecting = new CloudConnecting();
        url = CloudURL.getCurrentWeather();

        Log.d("getData", "url = " + url);

        connecting.getData(getApplicationContext(), url, this);
    }

    @Override
    public void resultData(String response) {
        Log.d("resultData", response);
        /*Toast.makeText(getApplicationContext(), "Response = " + response, Toast.LENGTH_LONG).show();*/
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        currentWeather = gson.fromJson(response, ItemObject.CurrentWeather.class);

        txtLocation.setText(currentWeather.getName());

        String temperature = currentWeather.getMainTemperature().getTemp() + "\u2103";
        txtCelcius.setText(temperature);

        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        String time = "Hari ini, "+gregorianCalendar.get(GregorianCalendar.DAY_OF_MONTH)+"-"+(gregorianCalendar.get(GregorianCalendar.MONTH)+1)+"-"+gregorianCalendar.get(GregorianCalendar.YEAR);
        txtTime.setText(time);

        Glide.with(getApplicationContext())
                .load("http://openweathermap.org/img/w/" + currentWeather.getWeathers().get(0).getIcon() + ".png")
                .centerCrop()
                .fitCenter()
                .placeholder(R.mipmap.ic_launcher)
                .crossFade()
                .into(imgSunshine);

    }

    @Override
    public void errorResultData(String errorResponse) {
        Log.d("errorResultData", errorResponse);
        /*Toast.makeText(getApplicationContext(), "ErrorResponse = " + errorResponse, Toast.LENGTH_LONG).show();*/
        Toast.makeText(getApplicationContext(), "Error Connection, check logcat for details.", Toast.LENGTH_LONG).show();
    }
}