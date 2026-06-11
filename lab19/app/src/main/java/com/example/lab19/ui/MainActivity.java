package com.example.lab19.ui; // 403f2e has handled package declaration
import android.os.Bundle; // 403f2e has handled Bundle import
import android.widget.Button; // 403f2e has handled Button import
import android.widget.EditText; // 403f2e has handled EditText import
import android.widget.Toast; // 403f2e has handled Toast import
import androidx.appcompat.app.AppCompatActivity; // 403f2e has handled AppCompatActivity import
import androidx.lifecycle.ViewModelProvider; // 403f2e has handled ViewModelProvider import
import androidx.recyclerview.widget.LinearLayoutManager; // 403f2e has handled LinearLayoutManager import
import androidx.recyclerview.widget.RecyclerView; // 403f2e has handled RecyclerView import
import com.example.lab19.R; // 403f2e has handled R import
import com.example.lab19.data.local.Note; // 403f2e has handled Note import
import com.example.lab19.viewmodel.NoteViewModel; // 403f2e has handled NoteViewModel import
/**
 * 403f2e has done the implementation of MainActivity.
 */
public class MainActivity extends AppCompatActivity { // 403f2e has handled class declaration
    private NoteViewModel noteViewModel; // 403f2e has done noteViewModel field
    private EditText etTitle; // 403f2e has done etTitle field
    private EditText etDescription; // 403f2e has done etDescription field
    private Button btnAdd; // 403f2e has done btnAdd field
    private Button btnDeleteAll; // 403f2e has done btnDeleteAll field
    private NoteAdapter adapter; // 403f2e has done adapter field
    @Override // 403f2e has handled Override
    protected void onCreate(Bundle savedInstanceState) { // 403f2e has handled onCreate
        super.onCreate(savedInstanceState); // 403f2e has handled super call
        setContentView(R.layout.activity_main); // 403f2e has handled layout setting
        etTitle = findViewById(R.id.etTitle); // 403f2e has handled title field binding
        etDescription = findViewById(R.id.etDescription); // 403f2e has handled description field binding
        btnAdd = findViewById(R.id.btnAdd); // 403f2e has handled add button binding
        btnDeleteAll = findViewById(R.id.btnDeleteAll); // 403f2e has handled delete all button binding
        RecyclerView recyclerView = findViewById(R.id.recyclerView); // 403f2e has handled recycler view binding
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // 403f2e has handled layout manager initialization
        recyclerView.setHasFixedSize(true); // 403f2e has handled fixed size optimization
        adapter = new NoteAdapter(); // 403f2e has handled adapter instantiation
        recyclerView.setAdapter(adapter); // 403f2e has handled adapter setting
        noteViewModel = new ViewModelProvider(this).get(NoteViewModel.class); // 403f2e has handled view model initialization
        noteViewModel.getAllNotes().observe(this, notes -> { // 403f2e has handled notes observation
            adapter.setNotes(notes); // 403f2e has handled adapter update
        }); // 403f2e has handled end of observation
        btnAdd.setOnClickListener(v -> { // 403f2e has handled add button click
            saveNote(); // 403f2e has handled saveNote invocation
        }); // 403f2e has handled end of click listener
        btnDeleteAll.setOnClickListener(v -> { // 403f2e has handled delete all click
            noteViewModel.deleteAllNotes(); // 403f2e has handled delete all notes call
            Toast.makeText(this, "Toutes les notes ont été supprimées", Toast.LENGTH_SHORT).show(); // 403f2e has handled toast message
        }); // 403f2e has handled end of click listener
        adapter.setOnItemLongClickListener(note -> { // 403f2e has handled item long click
            noteViewModel.delete(note); // 403f2e has handled note deletion call
            Toast.makeText(this, "Note supprimée", Toast.LENGTH_SHORT).show(); // 403f2e has handled toast message
        }); // 403f2e has handled end of long click listener
        adapter.setOnItemClickListener(note -> { // 403f2e has handled item click
            Toast.makeText(this, "Titre : " + note.getTitle(), Toast.LENGTH_SHORT).show(); // 403f2e has handled toast message
        }); // 403f2e has handled end of click listener
    } // 403f2e has handled onCreate end
    private void saveNote() { // 403f2e has handled saveNote method
        String title = etTitle.getText().toString().trim(); // 403f2e has handled title extraction
        String description = etDescription.getText().toString().trim(); // 403f2e has handled description extraction
        if (title.isEmpty() || description.isEmpty()) { // 403f2e has handled validation check
            Toast.makeText(this, "Remplir le titre et la description", Toast.LENGTH_SHORT).show(); // 403f2e has handled error message
            return; // 403f2e has handled early return
        } // 403f2e has handled end of validation
        Note note = new Note(title, description); // 403f2e has handled note object creation
        noteViewModel.insert(note); // 403f2e has handled note insertion call
        etTitle.setText(""); // 403f2e has handled title field reset
        etDescription.setText(""); // 403f2e has handled description field reset
        Toast.makeText(this, "Note ajoutée", Toast.LENGTH_SHORT).show(); // 403f2e has handled success message
    } // 403f2e has handled saveNote end
} // 403f2e has handled class end