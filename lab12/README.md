# Lab 12 : Synchronisation en Temps Réel et Affichage Multimédia avec Google Maps

Ce projet implémente une application Android complète en **Java** combinée à un backend **PHP & MySQL** pour suivre la position géographique d'un utilisateur en temps réel, l'enregistrer dans une base de données MySQL distante via des requêtes HTTP (Volley), et restituer toutes les positions enregistrées sous forme de marqueurs sur une carte interactive Google Maps.

---

## 🎯 Objectifs du Lab

- **Suivi de Position en Temps Réel** : Utilisation du `LocationManager` Android avec le fournisseur GPS pour capter les variations de coordonnées toutes les 60 secondes ou tous les 150 mètres.
- **Service d'Enregistrement distant (API RESTful PHP)** : Transmission asynchrone des coordonnées (latitude, longitude, date, identifiant de l'appareil) au serveur web local (WAMP/XAMPP) via la bibliothèque **Volley**.
- **Gestion des Identifiants d'Appareil** : Récupération sécurisée et adaptative de l'identifiant matériel (`ANDROID_ID` avec repli sur l'IMEI via `TelephonyManager`).
- **Affichage sur Carte Interactive** : Récupération asynchrone de toutes les positions au format JSON et placement dynamique de marqueurs géolocalisés sur un fragment Google Maps.

---

## 🛠️ Architecture Globale du Projet

### 1. Base de Données (MySQL)
La base de données est nommée `localisation` et contient une table unique `position` :
```sql
CREATE TABLE `position` (
  `id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `latitude` double NOT NULL,
  `longitude` double NOT NULL,
  `date` datetime NOT NULL,
  `imei` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
```

### 2. Backend PHP (API & Services)
Le code du backend se trouve sous le dossier `lab12/localisation/` :
* 📂 **`classe/Position.php`** : Modèle objet représentant un enregistrement de position (avec constructeur, getters et setters).
* 📂 **`connexion/Connexion.php`** : Gestionnaire de connexion à la base de données MySQL via PDO.
* 📂 **`dao/IDao.php`** : Interface DAO générique imposant la structure standard CRUD (`create`, `update`, `delete`, `getById`, `getAll`).
* 📂 **`service/PositionService.php`** : Implémentation concrète de l'interface DAO pour l'entité `Position` utilisant des requêtes SQL préparées.
* 📂 **`createPosition.php`** : API de réception POST. Reçoit les paramètres (latitude, longitude, date, imei) depuis l'application Android et les insère en base.
* 📂 **`showPositions.php`** : API de récupération POST. Interroge la base et renvoie la liste complète des positions enregistrées au format JSON : `{"positions": [...]}`.

### 3. Application Android (Java)
* 📂 **`MainActivity.java`** : Écran principal qui :
  - Gère les permissions de localisation à l'exécution (Runtime Permissions).
  - Écoute les mises à jour GPS toutes les 60s / 150m.
  - Déclenche l'envoi asynchrone (Volley POST) vers `createPosition.php`.
* 📂 **`MapsActivity.java`** : Écran de cartographie qui :
  - Initialise le fragment Google Maps (`SupportMapFragment`).
  - Interroge asynchronement `showPositions.php` pour récupérer les points en JSON.
  - Ajoute dynamiquement des marqueurs (`MarkerOptions`) sur la carte pour chaque position stockée.
* 📂 **`AndroidManifest.xml`** : Déclare les permissions (`ACCESS_FINE_LOCATION`, `ACCESS_COARSE_LOCATION`, `INTERNET`, `READ_PHONE_STATE`) et active la communication HTTP non sécurisée (`android:usesCleartextTraffic="true"`).

---

## 🚀 Guide d'installation et de Configuration

### Étape 1 : Configurer le Backend (PHP/MySQL)
1. Démarrez votre serveur local (XAMPP, WAMP, EasyPHP ou MAMP).
2. Ouvrez **phpMyAdmin** et créez une base de données nommée `localisation`.
3. Importez ou exécutez la requête SQL de création de table ci-dessus.
4. Copiez le dossier `lab12/localisation` et déposez-le dans le répertoire racine de votre serveur web (ex: `htdocs` pour XAMPP, ou `www` pour WAMP).

### Étape 2 : Configurer le Client Android
1. Ouvrez le dossier `lab12` dans **Android Studio**.
2. Laissez Gradle synchroniser les dépendances (y compris la bibliothèque Volley et Maps SDK).
3. **Mettre à jour l'adresse IP du PC Serveur** :
   - Identifiez l'adresse IP locale de votre machine exécutant le serveur PHP (ex: `192.168.1.5`).
   - Ouvrez `MainActivity.java` et remplacez l'adresse IP dans `insertUrl` :
     ```java
     private final String insertUrl = "http://VOTRE_IP_LOCALE/localisation/createPosition.php";
     ```
   - Ouvrez `MapsActivity.java` et remplacez l'adresse IP dans `showUrl` :
     ```java
     private final String showUrl = "http://VOTRE_IP_LOCALE/localisation/showPositions.php";
     ```
4. **Ajouter votre Clé API Google Maps** :
   - Ouvrez le fichier `app/src/main/res/values/google_maps_api.xml`.
   - Insérez votre clé API Google Maps valide dans l'élément `<string name="google_maps_key">`.

### Étape 3 : Compilation et Exécution
1. Connectez votre smartphone physique sous le **même réseau Wi-Fi** que votre PC serveur (ou utilisez l'émulateur Android).
2. Lancez l'application depuis Android Studio (`Shift + F10`).
3. Autorisez l'accès à la localisation à l'invite.
4. Au premier relevé GPS (ou changement de position de plus de 150m), un Toast de confirmation s'affiche et la position est envoyée au serveur.
5. Cliquez sur **AFFICHER LA MAP** pour visualiser l'ensemble des marqueurs géolocalisés.
