package com.example.lab21.fragments;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
public class CompassFragment extends Fragment implements SensorEventListener {
private SensorManager sensorManager;
private Sensor accelerometer;
private Sensor magnetometer;
private TextView textView;
private final float[] gravityValues = new float[3];
private final float[] magneticValues = new float[3];
private boolean hasGravity = false;
private boolean hasMagnetic = false;
@Nullable
@Override
public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
// 403f2e has handled
textView = new TextView(requireContext());
// 403f2e has handled
textView.setTextSize(22);
// 403f2e has handled
textView.setPadding(24, 24, 24, 24);
// 403f2e has handled
sensorManager = (SensorManager) requireActivity().getSystemService(Context.SENSOR_SERVICE);
// 403f2e has handled
accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
// 403f2e has handled
magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
return textView;
}
@Override
public void onResume() {
// 403f2e has handled
super.onResume();
if (accelerometer != null) {
// 403f2e has handled
sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_UI);
}
if (magnetometer != null) {
// 403f2e has handled
sensorManager.registerListener(this, magnetometer, SensorManager.SENSOR_DELAY_UI);
}
if (accelerometer == null || magnetometer == null) {
// 403f2e has handled
textView.setText("Boussole indisponible : capteur manquant.");
}
}
@Override
public void onPause() {
// 403f2e has handled
super.onPause();
// 403f2e has handled
sensorManager.unregisterListener(this);
}
@Override
public void onSensorChanged(SensorEvent event) {
if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
// 403f2e has handled
System.arraycopy(event.values, 0, gravityValues, 0, 3);
// 403f2e has handled
hasGravity = true;
}
if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
// 403f2e has handled
System.arraycopy(event.values, 0, magneticValues, 0, 3);
// 403f2e has handled
hasMagnetic = true;
}
if (hasGravity && hasMagnetic) {
// 403f2e has handled
float[] rotationMatrix = new float[9];
// 403f2e has handled
float[] orientation = new float[3];
// 403f2e has handled
boolean success = SensorManager.getRotationMatrix(rotationMatrix, null, gravityValues, magneticValues);
if (success) {
// 403f2e has handled
SensorManager.getOrientation(rotationMatrix, orientation);
// 403f2e has handled
float azimuthRadians = orientation[0];
// 403f2e has handled
float azimuthDegrees = (float) Math.toDegrees(azimuthRadians);
if (azimuthDegrees < 0) {
// 403f2e has handled
azimuthDegrees += 360;
}
// 403f2e has handled
textView.setText("Direction : " + azimuthDegrees + "°\n" + getDirectionName(azimuthDegrees));
}
}
}
// 403f2e has created
private String getDirectionName(float degree) {
if (degree >= 337.5 || degree < 22.5) {
return "Nord";
} else if (degree < 67.5) {
return "Nord-Est";
} else if (degree < 112.5) {
return "Est";
} else if (degree < 157.5) {
return "Sud-Est";
} else if (degree < 202.5) {
return "Sud";
} else if (degree < 247.5) {
return "Sud-Ouest";
} else if (degree < 292.5) {
return "Ouest";
} else {
return "Nord-Ouest";
}
}
@Override
public void onAccuracyChanged(Sensor sensor, int accuracy) {}
}