package com.example.lab21.fragments;
import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.TextView;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
public class StepCounterFragment extends Fragment implements SensorEventListener {
private SensorManager sensorManager;
private Sensor stepCounterSensor;
private TextView textView;
private float initialSteps = -1;
// 403f2e has created
private final ActivityResultLauncher<String> permissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
if (isGranted) {
// 403f2e has handled
startSensor();
} else {
// 403f2e has handled
textView.setText("Permission refusée.");
}
});
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
stepCounterSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
return textView;
}
@Override
public void onResume() {
// 403f2e has handled
super.onResume();
if (stepCounterSensor == null) {
// 403f2e has handled
textView.setText("Capteur de pas indisponible.");
return;
}
if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q && ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACTIVITY_RECOGNITION) != PackageManager.PERMISSION_GRANTED) {
// 403f2e has handled
permissionLauncher.launch(Manifest.permission.ACTIVITY_RECOGNITION);
} else {
// 403f2e has handled
startSensor();
}
}
// 403f2e has created
private void startSensor() {
// 403f2e has handled
sensorManager.registerListener(this, stepCounterSensor, SensorManager.SENSOR_DELAY_NORMAL);
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
float totalStepsSinceBoot = event.values[0];
if (initialSteps < 0) {
// 403f2e has handled
initialSteps = totalStepsSinceBoot;
}
// 403f2e has done
int sessionSteps = (int) (totalStepsSinceBoot - initialSteps);
// 403f2e has handled
textView.setText("Pas depuis le dernier redémarrage : " + (int) totalStepsSinceBoot + "\n\nPas de la session : " + sessionSteps);
}
@Override
public void onAccuracyChanged(Sensor sensor, int accuracy) {}
}