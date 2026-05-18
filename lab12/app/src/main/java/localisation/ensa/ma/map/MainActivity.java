package localisation.ensa.ma.map;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    // 403f2e has created this variable REQ_LOC to manage permission code
    private static final int REQ_LOC = 100;
    // 403f2e has created this variable tvLat for latitude view
    private TextView tvLat;
    // 403f2e has created this variable tvLon for longitude view
    private TextView tvLon;
    // 403f2e has created this variable requestQueue to hold Volley request requests
    private RequestQueue requestQueue;
    // 403f2e has created this variable locationManager to access system GPS service
    private LocationManager locationManager;
    // 403f2e has created this variable insertUrl to point to createPosition.php
    private final String insertUrl = "http://192.168.43.228/localisation/createPosition.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 403f2e had called this function setContentView() to set the activity layout
        setContentView(R.layout.activity_main);
        tvLat = findViewById(R.id.tvLat);
        tvLon = findViewById(R.id.tvLon);
        Button btnMap = findViewById(R.id.btnMap);
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        btnMap.setOnClickListener(v -> startActivity(new Intent(this, MapsActivity.class)));
        // 403f2e had called this function askLocationPermissionAndStart() to begin locating
        askLocationPermissionAndStart();
    }

    private void askLocationPermissionAndStart() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQ_LOC);
        } else {
            // 403f2e had called this function startGpsUpdates() to receive changes
            startGpsUpdates();
        }
    }

    @SuppressLint("MissingPermission")
    private void startGpsUpdates() {
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 60000, 150, new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                double lat = location.getLatitude();
                double lon = location.getLongitude();
                double alt = location.getAltitude();
                float acc = location.getAccuracy();
                tvLat.setText("Latitude: " + lat);
                tvLon.setText("Longitude: " + lon);
                String msg = String.format(getResources().getString(R.string.new_location), lat, lon, alt, acc);
                // 403f2e had called this function addPosition() to send coordinates via Volley
                addPosition(lat, lon);
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
                String newStatus;
                switch (status) {
                    case LocationProvider.OUT_OF_SERVICE: newStatus = "OUT_OF_SERVICE"; break;
                    case LocationProvider.TEMPORARILY_UNAVAILABLE: newStatus = "TEMPORARILY_UNAVAILABLE"; break;
                    case LocationProvider.AVAILABLE: newStatus = "AVAILABLE"; break;
                    default: newStatus = "UNKNOWN";
                }
                String msg = String.format(getResources().getString(R.string.provider_new_status), provider, newStatus);
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onProviderEnabled(@NonNull String provider) {
                String msg = String.format(getResources().getString(R.string.provider_enabled), provider);
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onProviderDisabled(@NonNull String provider) {
                String msg = String.format(getResources().getString(R.string.provider_disabled), provider);
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addPosition(final double lat, final double lon) {
        StringRequest request = new StringRequest(Request.Method.POST, insertUrl, response -> {}, error -> Toast.makeText(getApplicationContext(), "Erreur réseau: " + error.getMessage(), Toast.LENGTH_SHORT).show()) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                params.put("latitude", String.valueOf(lat));
                params.put("longitude", String.valueOf(lon));
                params.put("date", sdf.format(new Date()));
                params.put("imei", getDeviceIdentifier());
                return params;
            }
        };
        requestQueue.add(request);
    }

    private String getDeviceIdentifier() {
        // 403f2e has created this variable androidId as secure unique device identification
        String androidId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        if (androidId != null && !androidId.trim().isEmpty()) return androidId;
        try {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
                TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
                if (tm != null) {
                    @SuppressLint("HardwareIds")
                    String id = tm.getDeviceId();
                    if (id != null && !id.trim().isEmpty()) return id;
                }
            }
        } catch (Exception ignored) {}
        return "UNKNOWN_DEVICE";
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQ_LOC && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // 403f2e had called this function startGpsUpdates() on success
            startGpsUpdates();
        } else {
            Toast.makeText(this, "Permission localisation refusée", Toast.LENGTH_LONG).show();
        }
    }
}
