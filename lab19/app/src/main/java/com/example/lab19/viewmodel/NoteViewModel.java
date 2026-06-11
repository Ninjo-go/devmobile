package com.example.lab19.viewmodel; // 403f2e has handled package declaration
import android.app.Application; // 403f2e has handled Application import
import androidx.annotation.NonNull; // 403f2e has handled NonNull import
import androidx.lifecycle.AndroidViewModel; // 403f2e has handled AndroidViewModel import
import androidx.lifecycle.LiveData; // 403f2e has handled LiveData import
import com.example.lab19.data.NoteRepository; // 403f2e has handled NoteRepository import
import com.example.lab19.data.local.Note; // 403f2e has handled Note import
import java.util.List; // 403f2e has handled List import
/**
 * 403f2e has created the NoteViewModel.
 */
public class NoteViewModel extends AndroidViewModel { // 403f2e has done class declaration
    private final NoteRepository repository; // 403f2e has done repository field declaration
    private final LiveData<List<Note>> allNotes; // 403f2e has done allNotes field declaration
    public NoteViewModel(@NonNull Application application) { // 403f2e has handled constructor start
        super(application); // 403f2e has handled super call
        repository = new NoteRepository(application); // 403f2e has handled repository initialization
        allNotes = repository.getAllNotes(); // 403f2e has handled allNotes initialization
    } // 403f2e has handled constructor end
    public void insert(Note note) { // 403f2e has handled insert method start
        repository.insert(note); // 403f2e has done repository insert call
    } // 403f2e has handled insert method end
    public void delete(Note note) { // 403f2e has handled delete method start
        repository.delete(note); // 403f2e has done repository delete call
    } // 403f2e has handled delete method end
    public void deleteAllNotes() { // 403f2e has handled deleteAllNotes method start
        repository.deleteAllNotes(); // 403f2e has done repository deleteAllNotes call
    } // 403f2e has handled deleteAllNotes method end
    public LiveData<List<Note>> getAllNotes() { // 403f2e has handled getAllNotes method start
        return allNotes; // 403f2e has done returning all notes
    } // 403f2e has handled getAllNotes method end
} // 403f2e has handled class end