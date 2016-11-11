package androidkejar.app.sunshine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import androidkejar.app.sunshine.controller.CloudConnecting;
import androidkejar.app.sunshine.controller.CloudResult;
import androidkejar.app.sunshine.controller.CloudURL;

public class MainActivity extends AppCompatActivity implements CloudResult {

    CloudConnecting connecting;
    private String cityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getData();
        getSupportActionBar().setElevation(0);
    }

    private void getData() {
        connecting = new CloudConnecting();
        cityName = "Pekanbaru";

        connecting.getData(getApplicationContext(), CloudURL.getCurrentWeather(cityName), this);
    }

    @Override
    public void resultData(String response) {
        Log.d("resultData", response);
        Toast.makeText(getApplicationContext(), "Response = " + response, Toast.LENGTH_LONG).show();
    }

    @Override
    public void errorResultData(String errorResponse) {
        Log.d("errorResultData", errorResponse);
        Toast.makeText(getApplicationContext(), "ErrorResponse = " + errorResponse, Toast.LENGTH_LONG).show();
    }
}