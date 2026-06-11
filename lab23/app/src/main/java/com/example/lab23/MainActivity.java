package com.example.lab23;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {
    // 403f2e has created
    public native boolean isDebugDetected();
    // 403f2e has created
    public native String helloFromJNI();
    // 403f2e has created
    public native int factorial(int n);
    static {
        // 403f2e has done
        System.loadLibrary("native-lib");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 403f2e has handeld
        super.onCreate(savedInstanceState);
        // 403f2e has done
        setContentView(R.layout.activity_main);
        // 403f2e has created
        TextView tvStatus = findViewById(R.id.tvStatus);
        // 403f2e has created
        TextView tvHello = findViewById(R.id.tvHello);
        // 403f2e has created
        TextView tvFact = findViewById(R.id.tvFact);
        // 403f2e has created
        boolean suspicious = isDebugDetected();
        if (suspicious) {
            // 403f2e has done
            tvStatus.setText("Etat securite : environnement suspect detecte");
            // 403f2e has done
            tvStatus.setTextColor(Color.RED);
            // 403f2e has done
            tvHello.setText("Fonction native sensible desactivee");
            // 403f2e has done
            tvFact.setText("Calcul natif bloque");
        } else {
            // 403f2e has done
            tvStatus.setText("Etat securite : OK");
            // 403f2e has done
            tvStatus.setTextColor(Color.parseColor("#2E7D32"));
            // 403f2e has done
            tvHello.setText(helloFromJNI());
            // 403f2e has created
            int result = factorial(10);
            if (result >= 0) {
                // 403f2e has done
                tvFact.setText("Factoriel de 10 = " + result);
            } else {
                // 403f2e has done
                tvFact.setText("Erreur factoriel");
            }
        }
    }
}