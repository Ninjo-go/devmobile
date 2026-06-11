# Lab20 - Contact Synchronization System

A simple Android application designed to bridge local device contacts with a remote database. This project demonstrates the use of Retrofit for networking, RecyclerView for data display, and a PHP/MySQL backend for data persistence.

## Features

- **Load Local Contacts**: Accesses and displays contacts from the Android device using `ContactsContract`.
- **Cloud Synchronization**: Uploads local contacts to a remote server via a RESTful API.
- **Search Capability**: Allows users to search for contacts stored on the server by name or phone number.
- **Modern Architecture**: Implements a clean separation of concerns with Model, Adapter, and API interface layers.

## Tech Stack

### Android App
- **Language**: Java
- **Networking**: Retrofit 2 with GSON Converter
- **UI Components**: RecyclerView, ConstraintLayout, AppCompat
- **Permissions**: Dynamic handling of `READ_CONTACTS` and `INTERNET` permissions.

### Backend (PHP API)
- **Language**: PHP
- **Database**: MySQL (PDO for secure connections)
- **Format**: JSON API

## Project Structure

- `app/src/main/java/com/example/lab20/`
    - `MainActivity.java`: Main logic for UI and orchestration.
    - `Contact.java`: Data model for contact entities.
    - `ContactApi.java`: Retrofit interface defining API endpoints.
    - `RetrofitClient.java`: Singleton for Retrofit instance.
    - `ContactAdapter.java`: RecyclerView adapter for listing contacts.
    - `ApiResponse.java`: Model for server response handling.
- `numberbook_api/` (PHP Backend)
    - `api/`: Endpoint implementations (insert, search, get).
    - `config/`: Database connection settings.
    - `service/`: Business logic for database operations.

## Setup Instructions

1. **Backend Deployment**:
   - Import the database schema (table `contact` with columns `id`, `name`, `phone`, `source`, `created_at`).
   - Place the `numberbook_api` folder on your local server (e.g., XAMPP/WAMP).
   - Update `Database.php` with your credentials.

2. **Android Configuration**:
   - Update the `BASE_URL` in `RetrofitClient.java` to point to your server's IP address.
   - Ensure your Android device/emulator has network access to the server.

3. **Permissions**:
   - The app will request permission to read contacts on the first run.

---
*Created as part of a mobile development lab.*
