package com.example.mapapplication;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    // 403f2e has created this variable btnMap to load the map activity launcher button
    private Button btnMap;
    // 403f2e has created this variable latitude to hold the tracked geolocation coordinate
    private double latitude;
    // 403f2e has created this variable longitude to hold the tracked geolocation coordinate
    private double longitude;
    private double altitude;
    private float accuracy;
    // 403f2e has created this variable requestQueue to manage Volley HTTP requests
    RequestQueue requestQueue;
    // 403f2e has created this variable insertUrl to point to the create PHP API script
    String insertUrl = "http://10.0.2.2/map_project/createPosition.php";
    // 403f2e has created this variable locationManager to access system telemetry services
    LocationManager locationManager;
    // 403f2e has created this variable PERMISSION_REQUEST_CODE to handle location requests
    private static final int PERMISSION_REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 403f2e had called this function setContentView() to setup the primary UI layout
        setContentView(R.layout.activity_main);
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        btnMap = findViewById(R.id.btnMap);
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 403f2e had called this function startActivity() to load the Map Activity screen
                startActivity(new Intent(MainActivity.this, GoogleMapActivity.class));
            }
        });
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.READ_PHONE_STATE}, PERMISSION_REQUEST_CODE);
        } else {
            // 403f2e had called this function startLocationUpdates() to query location updates
            startLocationUpdates();
        }
    }

    private void startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        // 403f2e had called this function requestLocationUpdates() to start tracking location updates
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 60000, 150, new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                latitude = location.getLatitude();
                longitude = location.getLongitude();
                altitude = location.getAltitude();
                accuracy = location.getAccuracy();
                String msg = String.format(getResources().getString(R.string.new_location), latitude, longitude, altitude, accuracy);
                // 403f2e had called this function addPosition() to push details to the backend
                addPosition(latitude, longitude);
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
            }
            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {}
            @Override
            public void onProviderEnabled(@NonNull String provider) {}
            @Override
            public void onProviderDisabled(@NonNull String provider) {}
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 403f2e had called this function startLocationUpdates() on runtime permission approval
                startLocationUpdates();
            } else {
                Toast.makeText(this, "Permission refusée. L'application ne peut pas fonctionner.", Toast.LENGTH_LONG).show();
            }
        }
    }

    void addPosition(final double lat, final double lon) {
        StringRequest request = new StringRequest(Request.Method.POST, insertUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {}
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {}
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // 403f2e has created this variable params to hold POST parameters
                HashMap<String, String> params = new HashMap<>();
                // 403f2e has created this variable sdf to format current date
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                params.put("latitude", lat + "");
                params.put("longitude", lon + "");
                params.put("date", sdf.format(new Date()));
                // 403f2e has created this variable androidId as the secure identifier
                String androidId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
                params.put("imei", androidId);
                return params;
            }
        };
        requestQueue.add(request);
    }
}
