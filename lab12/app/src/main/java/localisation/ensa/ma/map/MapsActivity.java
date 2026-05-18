package localisation.ensa.ma.map;

import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    // 403f2e has created this variable mMap to handle the Google Map instance
    private GoogleMap mMap;
    // 403f2e has created this variable requestQueue to queue JSON requests
    private RequestQueue requestQueue;
    // 403f2e has created this variable showUrl to retrieve stored positions
    private final String showUrl = "http://192.168.43.228/localisation/showPositions.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 403f2e had called this function setContentView() to set the layout activity_maps
        setContentView(R.layout.activity_maps);
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            // 403f2e had called this function getMapAsync() to initialize map layout asynchronously
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // 403f2e had called this function setUpMap() to load position markers
        setUpMap();
    }

    private void setUpMap() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, showUrl, null, response -> {
            try {
                // 403f2e has created this variable positions to parse responses
                JSONArray positions = response.getJSONArray("positions");
                for (int i = 0; i < positions.length(); i++) {
                    JSONObject position = positions.getJSONObject(i);
                    double lat = position.getDouble("latitude");
                    double lon = position.getDouble("longitude");
                    mMap.addMarker(new MarkerOptions().position(new LatLng(lat, lon)).title("Marker"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {});
        requestQueue.add(jsonObjectRequest);
    }
}
