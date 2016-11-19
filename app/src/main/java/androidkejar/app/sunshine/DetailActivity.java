package androidkejar.app.sunshine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

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
        txtHumidity = (TextView) findViewById(R.id.txt_humadity);
        txtPressure = (TextView) findViewById(R.id.txt_pressure);
        txtWind = (TextView) findViewById(R.id.txt_wind);
        txtTemperature = (TextView) findViewById(R.id.txt_temperature);
        imgSunshine = (ImageView) findViewById(R.id.img_sunshine);

    }
}
