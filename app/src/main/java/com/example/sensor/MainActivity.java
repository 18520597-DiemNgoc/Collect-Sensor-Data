package com.example.sensor;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.hardware.SensorManager;
import android.hardware.Sensor;
import java.util.List;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textview = (TextView) findViewById(R.id.textView);
        // xd cac cam bien co tren thiet bi
        SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);

        // liet ke tat ca cac cam bien
        List<Sensor> sensors = sm.getSensorList(Sensor.TYPE_ALL);
        ListView list = (ListView) findViewById(R.id.list);

        list.setAdapter(new MySensor(this, R.layout.row_item, sensors));

        Button shareButton;
        shareButton = (Button) findViewById(R.id.button);
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });
    }
        public void openActivity2(){
            Intent intent = new Intent(this, Recaptcha.class);
            startActivity(intent);
        }
}
