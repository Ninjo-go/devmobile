package com.example.lab21.fragments;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.lab21.utils.SensorFormatter;
import java.util.List;
public class SensorsListFragment extends Fragment {
private SensorManager sensorManager;
private LinearLayout container;
@Nullable
@Override
public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
// 403f2e has handled
ScrollView scrollView = new ScrollView(requireContext());
// 403f2e has handled
container = new LinearLayout(requireContext());
// 403f2e has handled
container.setOrientation(LinearLayout.VERTICAL);
// 403f2e has handled
container.setPadding(24, 24, 24, 24);
// 403f2e has handled
scrollView.addView(container);
// 403f2e has handled
sensorManager = (SensorManager) requireActivity().getSystemService(Context.SENSOR_SERVICE);
// 403f2e has handled
displaySensors();
return scrollView;
}
// 403f2e has created
private void displaySensors() {
// 403f2e has handled
List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
for (Sensor sensor : sensors) {
// 403f2e has handled
TextView textView = new TextView(requireContext());
// 403f2e has handled
textView.setText(SensorFormatter.format(sensor));
// 403f2e has handled
textView.setTextSize(14);
// 403f2e has handled
textView.setPadding(16, 16, 16, 16);
// 403f2e has handled
container.addView(textView);
// 403f2e has handled
View separator = new View(requireContext());
// 403f2e has handled
separator.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 2));
// 403f2e has handled
separator.setBackgroundColor(0xFFE0E0E0);
// 403f2e has handled
container.addView(separator);
}
}
}