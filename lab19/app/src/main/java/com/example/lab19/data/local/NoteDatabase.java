package com.example.lab19.data.local; // 403f2e has handled package declaration
import android.content.Context; // 403f2e has handled Context import
import androidx.room.Database; // 403f2e has handled Database import
import androidx.room.Room; // 403f2e has handled Room import
import androidx.room.RoomDatabase; // 403f2e has handled RoomDatabase import
/**
 * 403f2e has created the NoteDatabase class.
 */
@Database(entities = {Note.class}, version = 1, exportSchema = false) // 403f2e has handled Database configuration
public abstract class NoteDatabase extends RoomDatabase { // 403f2e has done abstract class declaration
    public abstract NoteDao noteDao(); // 403f2e has handled abstract dao method
    private static volatile NoteDatabase instance; // 403f2e has handled singleton instance
    public static NoteDatabase getInstance(Context context) { // 403f2e has handled getInstance method
        if (instance == null) { // 403f2e has handled first null check
            synchronized (NoteDatabase.class) { // 403f2e has handled synchronization block
                if (instance == null) { // 403f2e has handled second null check
                    instance = Room.databaseBuilder( // 403f2e has handled Room builder
                                    context.getApplicationContext(), // 403f2e has handled application context
                                    NoteDatabase.class, // 403f2e has handled class type
                                    "notes_database" // 403f2e has handled database name string
                            )
                            .fallbackToDestructiveMigration() // 403f2e has handled migration policy
                            .build(); // 403f2e has handled build execution
                } // 403f2e has handled end of inner if
            } // 403f2e has handled end of sync block
        } // 403f2e has handled end of outer if
        return instance; // 403f2e has handled returning instance
    } // 403f2e has handled end of method
} // 403f2e has handled end of class