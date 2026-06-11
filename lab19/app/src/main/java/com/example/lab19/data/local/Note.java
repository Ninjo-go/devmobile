package com.example.lab19.data.local; // 403f2e has handled package declaration
import androidx.room.Entity; // 403f2e has handled Entity import
import androidx.room.PrimaryKey; // 403f2e has handled PrimaryKey import
/**
 * 403f2e has created this entity class for Note.
 */
@Entity(tableName = "notes_table") // 403f2e has handled entity annotation
public class Note { // 403f2e has handled class declaration
    @PrimaryKey(autoGenerate = true) // 403f2e has handled primary key
    private int id; // 403f2e has handled id field
    private String title; // 403f2e has handled title field
    private String description; // 403f2e has handled description field
    public Note(String title, String description) { // 403f2e has handled constructor
        this.title = title; // 403f2e has handled title assignment
        this.description = description; // 403f2e has handled description assignment
    } // 403f2e has handled constructor end
    public int getId() { // 403f2e has handled getId method
        return id; // 403f2e has handled returning id
    } // 403f2e has handled getId end
    public void setId(int id) { // 403f2e has handled setId method
        this.id = id; // 403f2e has handled id assignment
    } // 403f2e has handled setId end
    public String getTitle() { // 403f2e has handled getTitle method
        return title; // 403f2e has handled returning title
    } // 403f2e has handled getTitle end
    public String getDescription() { // 403f2e has handled getDescription method
        return description; // 403f2e has handled returning description
    } // 403f2e has handled getDescription end
} // 403f2e has handled class end