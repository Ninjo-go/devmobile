# Lab 13: Geolocation Tracking with OpenStreetMap & Procedural PHP Backend

This lab introduces real-time GPS geolocation tracking integrated with an open-source mapping engine (**OSMDroid / OpenStreetMap**) and a lightweight procedural PHP/PDO API backend. 

---

## 1. Project Specifications
- **Project Name**: `MapApplication`
- **Package Identifiers**: `com.example.mapapplication`
- **Programming Language**: Java 8
- **Minimum SDK**: API 24 (Android 7.0)
- **Target SDK**: API 34
- **Build System**: Kotlin DSL (`build.gradle.kts`)

---

## 2. Directory Tree
```
lab13/
├── README.md
├── settings.gradle
├── build.gradle
├── app/
│   ├── build.gradle.kts
│   └── src/
│       └── main/
│           ├── AndroidManifest.xml
│           ├── java/com/example/mapapplication/
│           │   ├── MainActivity.java
│           │   └── GoogleMapActivity.java
│           └── res/
│               ├── drawable/
│               │   └── marker.png (Custom 48x48dp red pin marker)
│               ├── layout/
│               │   ├── activity_main.xml
│               │   └── activity_google_map.xml
│               ├── values/
│               │   ├── colors.xml
│               │   ├── strings.xml
│               │   └── styles.xml (Theme.MapApplication)
│               └── xml/
│                   └── network_security_config.xml
└── map_project/
    ├── createPosition.php
    └── getPosition.php
```

---

## 3. Database Architecture & Setup

### Database Configuration
- **DBMS**: MySQL (via XAMPP/WAMP/LAMP)
- **Database Name**: `map_project`
- **Table Name**: `positions`

### Table SQL Definition
```sql
CREATE TABLE `positions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `latitude` double NOT NULL,
  `longitude` double NOT NULL,
  `date` datetime NOT NULL,
  `imei` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```

---

## 4. Operational Setup & Deployment

### Step A: Start Local Server
1. Boot your MySQL/Apache stack (e.g., XAMPP Control Panel).
2. Create a new database named `map_project` in phpMyAdmin.
3. Import or execute the `positions` table SQL definition.
4. Copy the `map_project/` folder into your server's web root (`htdocs/` for XAMPP or `www/` for WAMP).

### Step B: Build & Launch App
1. Open the project folder in Android Studio.
2. Grant the required permissions (`ACCESS_FINE_LOCATION` and `READ_PHONE_STATE`) upon startup.
3. The app starts querying coordinates from the internal GPS receiver every 60 seconds (or upon moving 150 meters) and uploads them to the server.
4. Press the **"Afficher La Map"** button to load the OpenStreetMap map activity, fetching all stored locations and displaying them as custom-anchored red pin markers.
