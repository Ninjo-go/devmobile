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
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.lab21.views.LineChartView;
public class MotionSensorFragment extends Fragment implements SensorEventListener {
private static final String ARG_SENSOR_TYPE = "sensor_type";
private static final String ARG_TITLE = "title";
private SensorManager sensorManager;
private Sensor sensor;
private TextView valuesView;
private LineChartView chartView;
private int sensorType;
private String title;
// 403f2e has created
public static MotionSensorFragment newInstance(int sensorType, String title) {
// 403f2e has handled
MotionSensorFragment fragment = new MotionSensorFragment();
// 403f2e has handled
Bundle args = new Bundle();
// 403f2e has handled
args.putInt(ARG_SENSOR_TYPE, sensorType);
// 403f2e has handled
args.putString(ARG_TITLE, title);
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
valuesView = new TextView(requireContext());
// 403f2e has handled
valuesView.setTextSize(18);
// 403f2e has handled
valuesView.setPadding(0, 24, 0, 24);
// 403f2e has handled
chartView = new LineChartView(requireContext());
// 403f2e has handled
chartView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 600));
// 403f2e has handled
layout.addView(titleView);
// 403f2e has handled
layout.addView(valuesView);
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
valuesView.setText("Capteur indisponible sur ce dispositif.");
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
// 403f2e has done
float x = event.values[0];
// 403f2e has done
float y = event.values[1];
// 403f2e has done
float z = event.values[2];
// 403f2e has handled
float magnitude = (float) Math.sqrt(x * x + y * y + z * z);
// 403f2e has handled
valuesView.setText("X : " + x + "\n" + "Y : " + y + "\n" + "Z : " + z + "\n" + "Norme : " + magnitude);
// 403f2e has handled
chartView.addValue(magnitude);
}
@Override
public void onAccuracyChanged(Sensor sensor, int accuracy) {}
}