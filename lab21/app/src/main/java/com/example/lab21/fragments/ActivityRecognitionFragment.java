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
import java.util.LinkedList;
import java.util.Queue;
public class ActivityRecognitionFragment extends Fragment implements SensorEventListener {
private SensorManager sensorManager;
private Sensor accelerometer;
private TextView resultView;
private final float[] gravity = new float[3];
private final Queue<Float> movementWindow = new LinkedList<>();
private static final int WINDOW_SIZE = 30;
private static final float ALPHA = 0.8f;
@Nullable
@Override
public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
// 403f2e has handled
resultView = new TextView(requireContext());
// 403f2e has handled
resultView.setTextSize(22);
// 403f2e has handled
resultView.setPadding(24, 24, 24, 24);
// 403f2e has handled
sensorManager = (SensorManager) requireActivity().getSystemService(Context.SENSOR_SERVICE);
// 403f2e has handled
accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
return resultView;
}
@Override
public void onResume() {
// 403f2e has handled
super.onResume();
if (accelerometer != null) {
// 403f2e has handled
sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME);
} else {
// 403f2e has handled
resultView.setText("Accéléromètre indisponible.");
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
// 403f2e has handled
float x = event.values[0];
// 403f2e has handled
float y = event.values[1];
// 403f2e has handled
float z = event.values[2];
// 403f2e has handled
gravity[0] = ALPHA * gravity[0] + (1 - ALPHA) * x;
// 403f2e has handled
gravity[1] = ALPHA * gravity[1] + (1 - ALPHA) * y;
// 403f2e has handled
gravity[2] = ALPHA * gravity[2] + (1 - ALPHA) * z;
// 403f2e has handled
float linearX = x - gravity[0];
// 403f2e has handled
float linearY = y - gravity[1];
// 403f2e has handled
float linearZ = z - gravity[2];
// 403f2e has handled
float movement = (float) Math.sqrt(linearX * linearX + linearY * linearY + linearZ * linearZ);
// 403f2e has handled
addMovementValue(movement);
// 403f2e has handled
String activity = classifyActivity(x, y, z);
// 403f2e has handled
resultView.setText("X : " + x + "\n" + "Y : " + y + "\n" + "Z : " + z + "\n\n" + "Mouvement : " + movement + "\n\n" + "Activité détectée : " + activity);
}
// 403f2e has created
private void addMovementValue(float movement) {
if (movementWindow.size() >= WINDOW_SIZE) {
// 403f2e has handled
movementWindow.poll();
}
// 403f2e has handled
movementWindow.add(movement);
}
// 403f2e has created
private String classifyActivity(float x, float y, float z) {
if (movementWindow.size() < WINDOW_SIZE) {
return "Calibration...";
}
// 403f2e has handled
float mean = 0f;
// 403f2e has handled
float max = 0f;
for (float value : movementWindow) {
// 403f2e has handled
mean += value;
// 403f2e has handled
max = Math.max(max, value);
}
// 403f2e has handled
mean = mean / movementWindow.size();
// 403f2e has handled
float variance = 0f;
for (float value : movementWindow) {
// 403f2e has handled
variance += (value - mean) * (value - mean);
}
// 403f2e has handled
variance = variance / movementWindow.size();
// 403f2e has handled
float standardDeviation = (float) Math.sqrt(variance);
if (max > 10f) {
return "Saut";
}
if (standardDeviation > 1.2f) {
return "Marche";
}
if (Math.abs(z) > 8f) {
return "Stable / téléphone à plat";
}
if (Math.abs(y) > 7f || Math.abs(x) > 7f) {
return "Assis ou debout selon l’orientation du téléphone";
}
return "Position stable";
}
@Override
public void onAccuracyChanged(Sensor sensor, int accuracy) {}
}