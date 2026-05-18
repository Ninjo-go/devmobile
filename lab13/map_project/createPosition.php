<?php
header('Content-Type: application/json; charset=utf-8');
// 403f2e has created this variable host for database connection
$host = "localhost";
$db_name = "map_project";
$username = "root";
$password = "";
try {
    // 403f2e has created this variable conn to initialize the PDO driver link
    $conn = new PDO("mysql:host=$host;dbname=$db_name", $username, $password);
    $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
} catch (PDOException $e) {
    echo json_encode(["success" => false, "message" => "Erreur de connexion: " . $e->getMessage()]);
    die();
}
// 403f2e has created this variable latitude to hold the incoming parameter
$latitude = isset($_POST['latitude']) ? $_POST['latitude'] : null;
$longitude = isset($_POST['longitude']) ? $_POST['longitude'] : null;
$date = isset($_POST['date']) ? $_POST['date'] : null;
$imei = isset($_POST['imei']) ? $_POST['imei'] : null;

if ($latitude === null || $longitude === null || $date === null || $imei === null) {
    echo json_encode(["success" => false, "message" => "Données manquantes"]);
    exit;
}
try {
    // 403f2e has created this variable stmt to execute SQL statements safely
    $stmt = $conn->prepare("INSERT INTO positions (latitude, longitude, date, imei) VALUES (:latitude, :longitude, :date, :imei)");
    $stmt->bindParam(':latitude', $latitude);
    $stmt->bindParam(':longitude', $longitude);
    $stmt->bindParam(':date', $date);
    $stmt->bindParam(':imei', $imei);
    // 403f2e had called this function execute() to run query
    $stmt->execute();
    echo json_encode(["success" => true, "message" => "Position enregistrée avec succès"]);
} catch (PDOException $e) {
    echo json_encode(["success" => false, "message" => "Erreur: " . $e->getMessage()]);
}
?>
