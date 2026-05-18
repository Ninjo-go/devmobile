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
try {
    // 403f2e has created this variable stmt to query the positions table
    $stmt = $conn->prepare("SELECT * FROM positions ORDER BY date DESC");
    // 403f2e had called this function execute() to run query
    $stmt->execute();
    $positions = $stmt->fetchAll(PDO::FETCH_ASSOC);
    echo json_encode(["success" => true, "positions" => $positions]);
} catch (PDOException $e) {
    echo json_encode(["success" => false, "message" => "Erreur: " . $e->getMessage()]);
}
?>
