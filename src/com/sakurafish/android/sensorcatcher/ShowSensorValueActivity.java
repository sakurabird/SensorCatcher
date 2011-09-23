package com.sakurafish.android.sensorcatcher;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ShowSensorValueActivity extends Activity implements SensorEventListener {
	static final String TAG = "ShowSensorValueActivity";
	{
		Log.d( TAG, "@@@---start---@@@" );
	}
	SensorManager sensorMgr;
	int sensorType;
	boolean hasSensor;
	List< Sensor > sensors;

	static TextView tv_title1;
	static TextView tv_Sensor_Sensitivity;
	static TextView tv_message;

	// // TODO onResumeの定義を変更sensorを他のメソッドでも使いたいため
	// List< Sensor > sensors;

	/** Called when the activity is first created. */
	@Override
	public void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.sensorsensitivity );

		sensorMgr = ( SensorManager ) getSystemService( SENSOR_SERVICE );
		hasSensor = false;

		tv_title1 = ( TextView ) findViewById( R.id.tv_title1 );
		tv_Sensor_Sensitivity = ( TextView ) findViewById( R.id.Sensor_Sensitivity );
		tv_message = ( TextView ) findViewById( R.id.message );

		Intent intent = this.getIntent();
		this.sensorType = intent.getIntExtra( "sensorType", 0 );
		setTextViewTitle();
	}

	private void setTextViewTitle() {
		switch ( sensorType ) {
			case Sensor.TYPE_PROXIMITY:
				tv_title1.setText( R.string.title_proximity );
				break;
			case Sensor.TYPE_ACCELEROMETER:
				tv_title1.setText( R.string.title_accelerometer );
				break;
			case Sensor.TYPE_LIGHT:
				tv_title1.setText( R.string.title_light );
				break;
			case Sensor.TYPE_ORIENTATION:
				tv_title1.setText( R.string.title_orientation );
				break;
			case Sensor.TYPE_MAGNETIC_FIELD:
				tv_title1.setText( R.string.title_magnetic_field );
				break;
			case Sensor.TYPE_GYROSCOPE:
				tv_title1.setText( R.string.title_gyroscope );
				break;
			case Sensor.TYPE_PRESSURE:
				tv_title1.setText( R.string.title_pressure );
				break;
			case Sensor.TYPE_TEMPERATURE:
				tv_title1.setText( R.string.title_temperature );
				break;

			default:
				tv_title1.setText( "" );
				break;
		}

	}

	@Override
	protected void onResume() {
		super.onResume();

		/* センサーの値を格納するリスト変数を取得 * */
		sensors = sensorMgr.getSensorList( this.sensorType );

		if ( sensors.size() > 0 ) {
			// センサーリスナー開始
			Sensor sensor = sensors.get( 0 );
			hasSensor = sensorMgr.registerListener( this, sensor, SensorManager.SENSOR_DELAY_NORMAL );
		} else {
			// センサーがついていない
			tv_message.setText( R.string.message_sensorcheck_na );
		}
	}

	//
	// private void getSensorList() {
	// switch ( sensorType ) {
	// case Defines.TYPE_PROXIMITY:
	// sensors = sensorMgr.getSensorList( Sensor.TYPE_PROXIMITY );
	// break;
	// case Defines.TYPE_ACCELEROMETER:
	// sensors = sensorMgr.getSensorList( Sensor.TYPE_ACCELEROMETER );
	// break;
	// case Defines.TYPE_LIGHT:
	// sensors = sensorMgr.getSensorList( Sensor.TYPE_LIGHT );
	// break;
	// case Defines.TYPE_ORIENTATION:
	// sensors = sensorMgr.getSensorList( Sensor.TYPE_ORIENTATION );
	// break;
	// case Defines.TYPE_MAGNETIC_FIELD:
	// sensors = sensorMgr.getSensorList( Sensor.TYPE_MAGNETIC_FIELD );
	// break;
	// case Defines.TYPE_GYROSCOPE:
	// sensors = sensorMgr.getSensorList( Sensor.TYPE_GYROSCOPE );
	// break;
	// case Defines.TYPE_PRESSURE:
	// sensors = sensorMgr.getSensorList( Sensor.TYPE_PRESSURE );
	// break;
	// case Defines.TYPE_TEMPERATURE:
	// sensors = sensorMgr.getSensorList( Sensor.TYPE_TEMPERATURE );
	// break;
	// }
	//
	// }

	@Override
	protected void onPause() {
		super.onPause();

		// センサーリスナー終了
		if ( hasSensor ) {
			sensorMgr.unregisterListener( this );
			hasSensor = false;
		}
		tv_message.setText( "" );
	}

	@Override
	public void onAccuracyChanged( Sensor arg0, int arg1 ) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSensorChanged( SensorEvent event ) {
		if ( event.sensor.getType() == sensorType ) {
			tv_Sensor_Sensitivity.setText( Float.toString( event.values[ 0 ] ) ); 
			Log.d( TAG, "onSensorChanged　event.values[ 0 ]=" + event.values[ 0 ] );
		}

	}

}