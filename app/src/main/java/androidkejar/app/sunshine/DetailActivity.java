package androidkejar.app.sunshine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by alodokter-it on 12/11/16.
 */

public class DetailActivity extends AppCompatActivity {
    TextView txtJudul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        txtJudul = (TextView) findViewById(R.id.txt_judul);

        txtJudul.setText(getIntent().getStringExtra("judul"));
    }
}
