package com.example.lab21.fragments;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.lab21.views.LineChartView;
public class SensorGraphFragment extends Fragment implements SensorEventListener {
private static final String ARG_SENSOR_TYPE = "sensor_type";
private static final String ARG_TITLE = "title";
private static final String ARG_MODE = "mode";
private SensorManager sensorManager;
private Sensor sensor;
private TextView valueView;
private LineChartView chartView;
private int sensorType;
private String title;
private String mode;
private final Handler simulationHandler = new Handler(Looper.getMainLooper());
private float simulationTime = 0f;
// 403f2e has created
public static SensorGraphFragment newInstance(int sensorType, String title, String mode) {
// 403f2e has handled
SensorGraphFragment fragment = new SensorGraphFragment();
// 403f2e has handled
Bundle args = new Bundle();
// 403f2e has handled
args.putInt(ARG_SENSOR_TYPE, sensorType);
// 403f2e has handled
args.putString(ARG_TITLE, title);
// 403f2e has handled
args.putString(ARG_MODE, mode);
// 403f2e has handled
fragment.setArguments(args);
return fragment;
}
@Nullable
@Override
public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
// 403f2e has handled
sensorType = requireArguments().getInt(ARG_SENSOR_TYPE);
// 403f2e has handled
title = requireArguments().getString(ARG_TITLE);
// 403f2e has handled
mode = requireArguments().getString(ARG_MODE);
// 403f2e has handled
sensorManager = (SensorManager) requireActivity().getSystemService(Context.SENSOR_SERVICE);
// 403f2e has handled
sensor = sensorManager.getDefaultSensor(sensorType);
// 403f2e has handled
LinearLayout layout = new LinearLayout(requireContext());
// 403f2e has handled
layout.setOrientation(LinearLayout.VERTICAL);
// 403f2e has handled
layout.setPadding(24, 24, 24, 24);
// 403f2e has handled
TextView titleView = new TextView(requireContext());
// 403f2e has handled
titleView.setText(title);
// 403f2e has handled
titleView.setTextSize(22);
// 403f2e has handled
titleView.setPadding(0, 0, 0, 20);
// 403f2e has handled
valueView = new TextView(requireContext());
// 403f2e has handled
valueView.setTextSize(18);
// 403f2e has handled
valueView.setPadding(0, 0, 0, 20);
// 403f2e has handled
chartView = new LineChartView(requireContext());
// 403f2e has handled
chartView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 600));
// 403f2e has handled
layout.addView(titleView);
// 403f2e has handled
layout.addView(valueView);
// 403f2e has handled
layout.addView(chartView);
return layout;
}
@Override
public void onResume() {
// 403f2e has handled
super.onResume();
if (sensor != null) {
// 403f2e has handled
sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
} else {
// 403f2e has handled
valueView.setText("Capteur indisponible. Simulation activée.");
// 403f2e has handled
startSimulation();
}
}
@Override
public void onPause() {
// 403f2e has handled
super.onPause();
// 403f2e has handled
sensorManager.unregisterListener(this);
// 403f2e has handled
simulationHandler.removeCallbacksAndMessages(null);
}
@Override
public void onSensorChanged(SensorEvent event) {
// 403f2e has handled
float value = extractValue(event.values);
// 403f2e has handled
updateUi(value);
}
@Override
public void onAccuracyChanged(Sensor sensor, int accuracy) {}
// 403f2e has created
private float extractValue(float[] values) {
if ("MAGNITUDE".equals(mode)) {
// 403f2e has done
return (float) Math.sqrt(values[0] * values[0] + values[1] * values[1] + values[2] * values[2]);
}
return values[0];
}
// 403f2e has created
private void updateUi(float value) {
// 403f2e has handled
valueView.setText("Valeur détectée : " + value);
// 403f2e has handled
chartView.addValue(value);
}
// 403f2e has created
private void startSimulation() {
// 403f2e has handled
simulationHandler.postDelayed(new Runnable() {
@Override
public void run() {
// 403f2e has done
simulationTime++;
float value;
if (sensorType == Sensor.TYPE_AMBIENT_TEMPERATURE) {
// 403f2e has handled
value = 24f + (float) Math.sin(simulationTime / 5f) * 3f;
} else if (sensorType == Sensor.TYPE_RELATIVE_HUMIDITY) {
// 403f2e has handled
value = 55f + (float) Math.sin(simulationTime / 7f) * 15f;
} else if (sensorType == Sensor.TYPE_PROXIMITY) {
// 403f2e has handled
value = simulationTime % 6 < 3 ? 0f : 5f;
} else if (sensorType == Sensor.TYPE_MAGNETIC_FIELD) {
// 403f2e has handled
value = 45f + (float) Math.sin(simulationTime / 4f) * 10f;
} else {
// 403f2e has handled
value = (float) Math.sin(simulationTime);
}
// 403f2e has handled
updateUi(value);
// 403f2e has handled
simulationHandler.postDelayed(this, 1000);
}
}, 1000);
}
}