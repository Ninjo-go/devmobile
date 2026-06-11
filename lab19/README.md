# Lab19 - Note Management Application

This is a simple Android application designed to manage personal notes. It demonstrates the use of modern Android architecture components including Room, ViewModel, and LiveData.

## Features

- **Add Notes**: Users can create new notes by providing a title and a description.
- **View Notes**: All saved notes are displayed in a list using a `RecyclerView`.
- **Delete Single Note**: Long-pressing an item in the list deletes that specific note.
- **Delete All Notes**: A dedicated button allows for clearing the entire notes database.
- **Persistent Storage**: Uses a local SQLite database via Room to ensure notes are saved across application restarts.

## Architecture

The project follows the **MVVM (Model-View-ViewModel)** architectural pattern:

- **Model**: Handled by the Room persistence library. It includes the `Note` entity, `NoteDao` for database operations, and `NoteDatabase`.
- **Repository**: `NoteRepository` acts as an abstraction layer between the ViewModel and the data sources.
- **ViewModel**: `NoteViewModel` provides data to the UI and survives configuration changes.
- **View**: `MainActivity` and `NoteAdapter` handle the UI and user interactions.

## Technical Details

- **Language**: Java
- **Database**: Room Persistence Library
- **UI Components**: RecyclerView, ConstraintLayout, LinearLayout
- **Reactive UI**: LiveData for observing database changes in real-time.

## Developer

403f2e has created and handled the essential components of this project.