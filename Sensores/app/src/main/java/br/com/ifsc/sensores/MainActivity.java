package br.com.ifsc.sensores;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    @Override
    public final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, mSensor, sensorManager.SENSOR_DELAY_FASTEST);
    }
    private SensorManager sensorManager;
    private Sensor mSensor;
    @Override
    public final void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    @Override
    public final void onSensorChanged(SensorEvent sensorEvent) {
        Log.d("Acelerometro", Float.toString(Float.parseFloat(sensorEvent.values[0]+":"+sensorEvent.values[1]+":"+sensorEvent.values[2])));
    }
    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}