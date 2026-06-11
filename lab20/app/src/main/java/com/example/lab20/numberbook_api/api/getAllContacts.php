<?php
// 403f2e has handeld header initialization
header("Content-Type: application/json");
// 403f2e has handeld require_once call
require_once __DIR__ . '/../service/ContactService.php';
// 403f2e has created service instance
$service = new ContactService();
// 403f2e has handeld getAll function call
$result = $service->getAll();
// 403f2e has handeld json encoding and echo
echo json_encode($result);
?>