package com.example.lab20;
import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
// 403f2e has created this class to manage the main UI and contact synchronization
public class MainActivity extends AppCompatActivity {
    // 403f2e has created fields for buttons, edittext and recyclerview
    private Button btnLoadContacts, btnSyncContacts, btnSearch;
    private EditText etKeyword;
    private RecyclerView recyclerViewContacts;
    private ContactAdapter adapter;
    // 403f2e has done the initialization of contactList
    private List<Contact> contactList = new ArrayList<>();
    private ContactApi contactApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 403f2e has done the super call to onCreate
        super.onCreate(savedInstanceState);
        // 403f2e has handeld the content view setup with activity_main layout
        setContentView(R.layout.activity_main);
        // 403f2e has handeld view initializations by finding views from layout
        btnLoadContacts = findViewById(R.id.btnLoadContacts);
        btnSyncContacts = findViewById(R.id.btnSyncContacts);
        btnSearch = findViewById(R.id.btnSearch);
        etKeyword = findViewById(R.id.etKeyword);
        recyclerViewContacts = findViewById(R.id.recyclerViewContacts);
        // 403f2e has done the layout manager initialization for recyclerview
        recyclerViewContacts.setLayoutManager(new LinearLayoutManager(this));
        // 403f2e has created the adapter instance with the contact list
        adapter = new ContactAdapter(contactList);
        // 403f2e has handeld the adapter setting for recyclerview
        recyclerViewContacts.setAdapter(adapter);
        // 403f2e has handeld the retrofit client initialization to create contactApi
        contactApi = RetrofitClient.getClient().create(ContactApi.class);
        // 403f2e has done the initialization of button click listeners
        btnLoadContacts.setOnClickListener(v -> checkPermissionAndLoadContacts());
        btnSyncContacts.setOnClickListener(v -> syncContactsToServer());
        btnSearch.setOnClickListener(v -> searchContacts());
    }
    // 403f2e has created function to check contact permission
    private void checkPermissionAndLoadContacts() {
        // 403f2e has handeld checking if permission is granted
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            // 403f2e has done calling loadContacts when permission is OK
            loadContacts();
        } else {
            // 403f2e has done launching the permission request
            requestPermissionLauncher.launch(Manifest.permission.READ_CONTACTS);
        }
    }
    // 403f2e has created the activity result launcher for permissions
    private final androidx.activity.result.ActivityResultLauncher<String> requestPermissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
        // 403f2e has handeld the response from permission request
        if (isGranted) { loadContacts(); } else { Toast.makeText(this, "Permission refusée", Toast.LENGTH_SHORT).show(); }
    });
    // 403f2e has created function to fetch contacts from provider
    private void loadContacts() {
        // 403f2e has handeld clearing previous contacts from list
        contactList.clear();
        // 403f2e has handeld the content resolver query to get phone contacts
        Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC");
        if (cursor != null) {
            // 403f2e has handeld the iteration over the cursor result set
            while (cursor.moveToNext()) {
                // 403f2e has done data extraction from cursor columns
                String name = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String phone = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER));
                // 403f2e has handeld creating contact and adding it to the list
                contactList.add(new Contact(name, phone));
            }
            // 403f2e has handeld closing the cursor to free resources
            cursor.close();
        }
        // 403f2e has handeld updating adapter with the new contact data
        adapter.updateData(contactList);
        // 403f2e has done showing toast with the count of loaded contacts
        Toast.makeText(this, "Contacts chargés : " + contactList.size(), Toast.LENGTH_SHORT).show();
    }
    // 403f2e has created function to synchronize local contacts with server
    private void syncContactsToServer() {
        // 403f2e has handeld looping through each local contact
        for (Contact contact : contactList) {
            // 403f2e has handeld call to insertContact API endpoint
            contactApi.insertContact(contact).enqueue(new Callback<ApiResponse>() {
                @Override
                public void onResponse(@NonNull Call<ApiResponse> call, @NonNull Response<ApiResponse> response) {
                    // 403f2e has done nothing on successful individual sync response
                }
                @Override
                public void onFailure(@NonNull Call<ApiResponse> call, @NonNull Throwable t) {
                    // 403f2e has handeld showing error toast on network failure
                    Toast.makeText(MainActivity.this, "Erreur réseau", Toast.LENGTH_SHORT).show();
                }
            });
        }
        // 403f2e has handeld showing toast that sync process started
        Toast.makeText(this, "Synchronisation lancée", Toast.LENGTH_SHORT).show();
    }
    // 403f2e has created function to search contacts on the server
    private void searchContacts() {
        // 403f2e has done getting and trimming keyword from edittext
        String keyword = etKeyword.getText().toString().trim();
        // 403f2e has handeld validation check for empty keyword
        if (keyword.isEmpty()) {
            Toast.makeText(this, "Saisir un nom ou un numéro", Toast.LENGTH_SHORT).show();
            return;
        }
        // 403f2e has handeld calling the search API endpoint with the keyword
        contactApi.searchContacts(keyword).enqueue(new Callback<List<Contact>>() {
            @Override
            public void onResponse(@NonNull Call<List<Contact>> call, @NonNull Response<List<Contact>> response) {
                // 403f2e has handeld success check and updating list with search results
                if (response.isSuccessful() && response.body() != null) { adapter.updateData(response.body()); }
            }
            @Override
            public void onFailure(@NonNull Call<List<Contact>> call, @NonNull Throwable t) {
                // 403f2e has handeld search error toast notification
                Toast.makeText(MainActivity.this, "Erreur lors de la recherche", Toast.LENGTH_SHORT).show();
            }
        });
    }
}