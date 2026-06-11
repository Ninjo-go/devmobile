# Counter Mini-Project

A simple Android application demonstrating the use of the **ViewModel** and **LiveData** components from the Android Architecture Components. This project follows the MVVM (Model-View-ViewModel) pattern to manage a counter state that survives configuration changes (like screen rotations).

## Features
- **Increment**: Increases the counter value by 1.
- **Decrement**: Decreases the counter value by 1.
- **Reset**: Resets the counter value back to 0.
- **State Persistence**: The counter value is maintained using a `ViewModel`, ensuring it doesn't reset when the activity is recreated.

## Project Structure
- `MainActivity.java`: Handles the UI interactions and observes the `LiveData` from the ViewModel to update the screen.
- `CounterViewModel.java`: Encapsulates the counter logic and holds the `MutableLiveData` representing the current count.
- `activity_main.xml`: Defines the layout with a `TextView` for the count and three `Buttons` for the actions.

## Technologies Used
- **Language**: Java
- **Architecture**: MVVM
- **Libraries**:
    - `androidx.lifecycle:lifecycle-viewmodel`: To manage UI-related data in a lifecycle-conscious way.
    - `androidx.lifecycle:lifecycle-livedata`: To notify the UI when the underlying data changes.
    - `androidx.appcompat`: For backward compatibility.
    - `androidx.constraintlayout`: For flexible UI design.

## How to Build
1. Open the project in **Android Studio**.
2. Sync the project with Gradle files.
3. Build and run the app on an emulator or a physical device.

---
*Created as part of Lab 18.*