package androidkejar.app.sunshine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import androidkejar.app.sunshine.controller.CloudURL;

/**
 * Created by alodokter-it on 12/11/16.
 */

public class DetailActivity extends AppCompatActivity {
    TextView txtTime, txtDate, txtTemperature, txtHumidity, txtPressure, txtWind;
    ImageView imgSunshine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        txtTime = (TextView) findViewById(R.id.txt_time);
        txtDate = (TextView) findViewById(R.id.txt_date);
        txtHumidity = (TextView) findViewById(R.id.txt_humidity);
        txtPressure = (TextView) findViewById(R.id.txt_pressure);
        txtWind = (TextView) findViewById(R.id.txt_wind);
        txtTemperature = (TextView) findViewById(R.id.txt_temperature);
        imgSunshine = (ImageView) findViewById(R.id.img_sunshine);

        txtTime.setText(getIntent().getExtras().getString("main") + "");
        txtDate.setText(getIntent().getExtras().getString("desc") + "");
        txtHumidity.setText(getIntent().getExtras().getString("humidity") + "");
        txtPressure.setText(getIntent().getExtras().getString("pressure") + "");
        txtWind.setText(getIntent().getExtras().getString("speed") + "");
        txtTemperature.setText((int) Math.round(getIntent().getExtras().getDouble("temperature")) + " \u2103");

        Glide.with(getApplicationContext())
                .load(CloudURL.getImageWeather(getIntent().getExtras().getString("icon")))
                .centerCrop()
                .fitCenter()
                .crossFade()
                .into(imgSunshine);

    }
}
