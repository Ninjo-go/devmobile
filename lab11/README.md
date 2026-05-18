# Lab 11 : Localisation et Cartographie avec Google Maps sur Android

Ce projet est une application Android développée en **Java** qui intègre l'API **Google Maps** pour afficher une carte interactive, suivre la position en temps réel de l'utilisateur, gérer les permissions de localisation au runtime et inviter l'utilisateur à activer son GPS si ce dernier est désactivé.

## 🎯 Objectifs du Lab

- **Afficher une Google Map** dans l'application.
- **Demander la permission de localisation** dynamiquement à l'exécution (Runtime Permission).
- **Écouter les changements de position** en utilisant le fournisseur de localisation (`LocationManager` et `NETWORK_PROVIDER`).
- **Ajouter un marker** à chaque nouvelle position détectée.
- **Centrer et zoomer automatiquement** la caméra sur la position actuelle de l'utilisateur.
- **Gérer le GPS désactivé** : Afficher une boîte de dialogue (`AlertDialog`) redirigeant l'utilisateur vers les paramètres du système pour l'activer.

---

## 🛠️ Structure du Projet

Le code source principal et les configurations se trouvent dans le dossier `lab11/` :
- 📍 **[MapsActivity.java](app/src/main/java/localisation/ensa/ma/map/MapsActivity.java)** : Contient toute la logique de l'application (initialisation de la carte, gestion du `LocationListener`, demande de permissions et dialogues d'alerte GPS).
- 🎨 **[activity_maps.xml](app/src/main/res/layout/activity_maps.xml)** : Layout contenant le `SupportMapFragment` pour afficher la carte.
- 🔑 **[google_maps_api.xml](app/src/main/res/values/google_maps_api.xml)** : Contient la clé API Google Maps (`google_maps_key`).
- 📝 **[AndroidManifest.xml](app/src/main/AndroidManifest.xml)** : Déclare les permissions requises (`ACCESS_FINE_LOCATION`, `ACCESS_COARSE_LOCATION`, `INTERNET`) et la clé API.

---

## 🚀 Guide d'installation et de Configuration

### 1. Cloner le projet
Clonez le dépôt sur votre machine locale :
```bash
git clone git@github2.com:Ninjo-go/devmobile.git
```

### 2. Ajouter la clé API Google Maps
1. Allez sur la [Google Cloud Console](https://console.developers.google.com/).
2. Créez un projet et activez l'API **Maps SDK for Android**.
3. Générez une clé API.
4. Ouvrez le fichier `lab11/app/src/main/res/values/google_maps_api.xml`.
5. Remplacez le texte `VOTRE_CLE_ICI` par votre clé API générée :
   ```xml
   <string name="google_maps_key" templateMergeStrategy="preserve" translatable="false">VOTRE_CLE_REELLE</string>
   ```

### 3. Exécuter l'application
- Ouvrez le dossier `lab11` dans **Android Studio**.
- Laissez Gradle synchroniser les dépendances.
- Lancez l'application sur un émulateur ou sur un appareil physique connecté.
- Acceptez la demande de permission de localisation au démarrage.
