<?php
// 403f2e has handeld header initialization
header("Content-Type: application/json");
// 403f2e has handeld require_once call
require_once __DIR__ . '/../service/ContactService.php';
// 403f2e has handeld request method check
if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    // 403f2e has handeld json decoding of input
    $data = json_decode(file_get_contents("php://input"), true);
    // 403f2e has handeld field validation
    if (!isset($data['name']) || !isset($data['phone'])) {
        // 403f2e has handeld error response echo
        echo json_encode(["success" => false, "message" => "Champs manquants"]);
        exit;
    }
    // 403f2e has created the service instance
    $service = new ContactService();
    // 403f2e has handeld insert function call
    $ok = $service->insert($data['name'], $data['phone'], "mobile");
    // 403f2e has handeld success response echo
    echo json_encode(["success" => $ok, "message" => $ok ? "Contact inséré avec succès" : "Erreur d'insertion"]);
}
?>