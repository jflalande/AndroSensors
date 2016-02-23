package andro.jf;

import org.openintents.sensorsimulator.hardware.Sensor;
import org.openintents.sensorsimulator.hardware.SensorEvent;
import org.openintents.sensorsimulator.hardware.SensorEventListener;
import org.openintents.sensorsimulator.hardware.SensorManagerSimulator;

import android.app.Activity;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class Main extends Activity {

	@Override
	protected void onStop() {
		// Surtout, ne pas oublier de détacher l'écouteur !
		manager.unregisterListener(myListener);
		super.onStop();
	}
	SensorManagerSimulator manager;
	SensorEventListener myListener;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		//SensorManager manager = (SensorManager)getSystemService(SENSOR_SERVICE);
		manager = SensorManagerSimulator.getSystemService(this, SENSOR_SERVICE);
		manager.connectSimulator();

		// 	Création d'un écouteur qui réagit aux événements de l'accéléromètre
		myListener = new SensorEventListener() {
			public void onSensorChanged(SensorEvent event) {

				if (event.type == Sensor.TYPE_ACCELEROMETER)
				{
					float x,y,z;
					x = event.values[0];
					y = event.values[1];
					z = event.values[2];
					TextView t = (TextView)findViewById(R.id.text1);
					t.setText("x = " + x);

				}
			}
			public void onAccuracyChanged(Sensor sensor, int accuracy) {
				// TODO Auto-generated method stub	
			}
		};

		// Ajout d'un écouteur pour l'acceleromètre
		manager.registerListener(myListener     		
				, manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
				, SensorManager.SENSOR_DELAY_UI);

	}
}