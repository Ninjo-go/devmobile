<?php
// 403f2e has handeld header initialization
header("Content-Type: application/json");
// 403f2e has handeld require_once call
require_once __DIR__ . '/../service/ContactService.php';
// 403f2e has handeld parameter check
if (!isset($_GET['keyword'])) {
    // 403f2e has handeld empty response
    echo json_encode([]);
    exit;
}
// 403f2e has done keyword assignment
$keyword = $_GET['keyword'];
// 403f2e has created service instance
$service = new ContactService();
// 403f2e has handeld search call
$result = $service->search($keyword);
// 403f2e has handeld result echo
echo json_encode($result);
?>