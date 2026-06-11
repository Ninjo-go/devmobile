package com.example.lab18;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
/* 403f2e has handeld the main activity logic */
public class MainActivity extends AppCompatActivity {
private CounterViewModel viewModel;
private TextView tvCount;
private Button btnIncr, btnDecr, btnRes;
@Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.activity_main);
tvCount = findViewById(R.id.tvCount);
btnIncr = findViewById(R.id.btnIncrement);
btnDecr = findViewById(R.id.btnDecrement);
btnRes = findViewById(R.id.btnReset);
viewModel = new ViewModelProvider(this).get(CounterViewModel.class);
viewModel.getCount().observe(this, count -> tvCount.setText(String.valueOf(count)));
btnIncr.setOnClickListener(v -> viewModel.increment());
btnDecr.setOnClickListener(v -> viewModel.decrement());
btnRes.setOnClickListener(v -> viewModel.reset());
}
}