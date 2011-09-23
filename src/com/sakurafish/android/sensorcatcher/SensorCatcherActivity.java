package com.sakurafish.android.sensorcatcher;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.os.Bundle;
import android.view.View;

public class SensorCatcherActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.main );
	}

	public void onClick( View view ) {
		Intent intent = new Intent( this, ShowSensorValueActivity.class );
		
		switch ( view.getId() ) {
			case R.id.button_proximity:
				intent.putExtra( "sensorType", Sensor.TYPE_PROXIMITY );
				break;
			case R.id.button_accelerometer:
				intent.putExtra( "sensorType", Sensor.TYPE_ACCELEROMETER );
				break;
			case R.id.button_light:
				intent.putExtra( "sensorType", Sensor.TYPE_LIGHT );
				break;
			case R.id.button_orientation:
				intent.putExtra( "sensorType", Sensor.TYPE_ORIENTATION );
				break;
			case R.id.button_magnetic_field:
				intent.putExtra( "sensorType", Sensor.TYPE_MAGNETIC_FIELD );
				break;
			case R.id.button_gyroscope:
				intent.putExtra( "sensorType", Sensor.TYPE_GYROSCOPE );
				break;
			case R.id.button_pressure:
				intent.putExtra( "sensorType", Sensor.TYPE_PRESSURE );
				break;
			case R.id.button_temperature:
				intent.putExtra( "sensorType", Sensor.TYPE_TEMPERATURE );
				break;
		}
		startActivity( intent );
	}
}