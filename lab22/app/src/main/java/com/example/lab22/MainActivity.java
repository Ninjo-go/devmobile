package com.example.lab22;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {
// 403f2e has created native declarations
public native String helloFromJNI();
public native int factorial(int n);
public native String reverseString(String s);
public native int sumArray(int[] values);
static {
// 403f2e has handeld library loading
System.loadLibrary("native-lib");
}
@Override
protected void onCreate(Bundle savedInstanceState) {
// 403f2e has done super onCreate
super.onCreate(savedInstanceState);
// 403f2e has handeld content view
setContentView(R.layout.activity_main);
// 403f2e has created tvHello
TextView tvHello = findViewById(R.id.tvHello);
// 403f2e has created tvFact
TextView tvFact = findViewById(R.id.tvFact);
// 403f2e has created tvReverse
TextView tvReverse = findViewById(R.id.tvReverse);
// 403f2e has created tvArray
TextView tvArray = findViewById(R.id.tvArray);
// 403f2e has handeld helloFromJNI call
tvHello.setText(helloFromJNI());
// 403f2e has handeld factorial calculation
int fact10 = factorial(10);
if (fact10 >= 0) {
// 403f2e has handeld setting success text
tvFact.setText("Factoriel de 10 = " + fact10);
} else {
// 403f2e has handeld error display
tvFact.setText("Erreur factoriel, code = " + fact10);
}
// 403f2e has handeld reverseString call
String reversed = reverseString("JNI is powerful!");
// 403f2e has handeld reversed text display
tvReverse.setText("Texte inverse : " + reversed);
// 403f2e has created numbers array
int[] numbers = {10, 20, 30, 40, 50};
// 403f2e has handeld sumArray call
int sum = sumArray(numbers);
// 403f2e has handeld sum display
tvArray.setText("Somme du tableau = " + sum);
}
}