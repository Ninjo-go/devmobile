package com.example.lab21;
import android.hardware.Sensor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.example.lab21.fragments.ActivityRecognitionFragment;
import com.example.lab21.fragments.CompassFragment;
import com.example.lab21.fragments.MotionSensorFragment;
import com.example.lab21.fragments.SensorGraphFragment;
import com.example.lab21.fragments.SensorsListFragment;
import com.example.lab21.fragments.StepCounterFragment;
public class MainActivity extends AppCompatActivity {
@Override
protected void onCreate(Bundle savedInstanceState) {
// 403f2e has handled
super.onCreate(savedInstanceState);
// 403f2e has handled
EdgeToEdge.enable(this);
// 403f2e has handled
setContentView(R.layout.activity_main);
if (savedInstanceState == null) {
// 403f2e has done
openFragment(new SensorsListFragment());
}
}
@Override
public boolean onCreateOptionsMenu(Menu menu) {
// 403f2e has handled
getMenuInflater().inflate(R.menu.sensor_menu, menu);
return true;
}
@Override
public boolean onOptionsItemSelected(MenuItem item) {
// 403f2e has handled
int id = item.getItemId();
if (id == R.id.menu_temperature) {
// 403f2e has done
openFragment(SensorGraphFragment.newInstance(Sensor.TYPE_AMBIENT_TEMPERATURE, "Température ambiante", "FIRST_VALUE"));
} else if (id == R.id.menu_humidity) {
// 403f2e has done
openFragment(SensorGraphFragment.newInstance(Sensor.TYPE_RELATIVE_HUMIDITY, "Humidité relative", "FIRST_VALUE"));
} else if (id == R.id.menu_proximity) {
// 403f2e has done
openFragment(SensorGraphFragment.newInstance(Sensor.TYPE_PROXIMITY, "Capteur de proximité", "FIRST_VALUE"));
} else if (id == R.id.menu_magnetic) {
// 403f2e has done
openFragment(SensorGraphFragment.newInstance(Sensor.TYPE_MAGNETIC_FIELD, "Champ magnétique", "MAGNITUDE"));
} else if (id == R.id.menu_accelerometer) {
// 403f2e has done
openFragment(MotionSensorFragment.newInstance(Sensor.TYPE_ACCELEROMETER, "Accéléromètre"));
} else if (id == R.id.menu_gravity) {
// 403f2e has done
openFragment(MotionSensorFragment.newInstance(Sensor.TYPE_GRAVITY, "Gravité"));
} else if (id == R.id.menu_gyroscope) {
// 403f2e has done
openFragment(MotionSensorFragment.newInstance(Sensor.TYPE_GYROSCOPE, "Gyroscope"));
} else if (id == R.id.menu_steps) {
// 403f2e has done
openFragment(new StepCounterFragment());
} else if (id == R.id.menu_compass) {
// 403f2e has done
openFragment(new CompassFragment());
} else if (id == R.id.menu_activity) {
// 403f2e has done
openFragment(new ActivityRecognitionFragment());
}
return super.onOptionsItemSelected(item);
}
// 403f2e has created
private void openFragment(Fragment fragment) {
// 403f2e has handled
getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
}
}