package com.example.lab19.data; // 403f2e has handled package declaration
import android.app.Application; // 403f2e has handled Application import
import androidx.lifecycle.LiveData; // 403f2e has handled LiveData import
import com.example.lab19.data.local.Note; // 403f2e has handled Note import
import com.example.lab19.data.local.NoteDao; // 403f2e has handled NoteDao import
import com.example.lab19.data.local.NoteDatabase; // 403f2e has handled NoteDatabase import
import java.util.List; // 403f2e has handled List import
import java.util.concurrent.ExecutorService; // 403f2e has handled ExecutorService import
import java.util.concurrent.Executors; // 403f2e has handled Executors import
/**
 * 403f2e has created the NoteRepository class.
 */
public class NoteRepository { // 403f2e has handled class declaration
    private final NoteDao noteDao; // 403f2e has handled noteDao field declaration
    private final LiveData<List<Note>> allNotes; // 403f2e has handled allNotes field declaration
    private final ExecutorService executorService; // 403f2e has handled executorService field declaration
    public NoteRepository(Application application) { // 403f2e has handled constructor start
        NoteDatabase database = NoteDatabase.getInstance(application); // 403f2e has handled database initialization
        noteDao = database.noteDao(); // 403f2e has handled noteDao retrieval
        allNotes = noteDao.getAllNotes(); // 403f2e has handled notes list initialization
        executorService = Executors.newSingleThreadExecutor(); // 403f2e has handled executor initialization
    } // 403f2e has handled constructor end
    public void insert(Note note) { // 403f2e has handled insert method start
        executorService.execute(() -> noteDao.insert(note)); // 403f2e has handled background insert
    } // 403f2e has handled insert method end
    public void delete(Note note) { // 403f2e has handled delete method start
        executorService.execute(() -> noteDao.delete(note)); // 403f2e has handled background delete
    } // 403f2e has handled delete method end
    public void deleteAllNotes() { // 403f2e has handled deleteAllNotes method start
        executorService.execute(noteDao::deleteAllNotes); // 403f2e has handled background delete all
    } // 403f2e has handled deleteAllNotes method end
    public LiveData<List<Note>> getAllNotes() { // 403f2e has handled getAllNotes method start
        return allNotes; // 403f2e has handled returning all notes
    } // 403f2e has handled getAllNotes method end
} // 403f2e has handled class end