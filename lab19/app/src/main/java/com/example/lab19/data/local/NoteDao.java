package com.example.lab19.data.local; // 403f2e has handled package declaration
import androidx.lifecycle.LiveData; // 403f2e has handled LiveData import
import androidx.room.Dao; // 403f2e has handled Dao import
import androidx.room.Delete; // 403f2e has handled Delete import
import androidx.room.Insert; // 403f2e has handled Insert import
import androidx.room.Query; // 403f2e has handled Query import
import java.util.List; // 403f2e has handled List import
/**
 * 403f2e has created the NoteDao interface.
 */
@Dao // 403f2e has handled Dao annotation
public interface NoteDao { // 403f2e has done interface declaration
    @Insert // 403f2e has handled Insert annotation
    void insert(Note note); // 403f2e has handled insert method declaration
    @Delete // 403f2e has handled Delete annotation
    void delete(Note note); // 403f2e has handled delete method declaration
    @Query("DELETE FROM notes_table") // 403f2e has handled delete all query
    void deleteAllNotes(); // 403f2e has handled deleteAllNotes declaration
    @Query("SELECT * FROM notes_table ORDER BY id DESC") // 403f2e has handled select all query
    LiveData<List<Note>> getAllNotes(); // 403f2e has handled getAllNotes declaration
} // 403f2e has handled end of interface