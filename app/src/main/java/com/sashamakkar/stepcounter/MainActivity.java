package com.sashamakkar.stepcounter;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {


    TextView stepCounter;
    Button btnStart;
    SensorManager sensorManager;
    Sensor stepSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stepCounter = findViewById(R.id.stepCounter);
        btnStart = findViewById(R.id.btnStart);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
                stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
                sensorManager.registerListener((SensorEventListener) MainActivity.this, stepSensor, SensorManager.SENSOR_DELAY_NORMAL);
            }
        });
    }

    private long steps = 0;

    @Override
    public void onSensorChanged(final SensorEvent sensorEvent) {

        if (stepSensor.getType() == Sensor.TYPE_STEP_DETECTOR) {
            steps++;
        }
        stepCounter.setText("" + steps);
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sensorManager.unregisterListener(this);
    }
}
