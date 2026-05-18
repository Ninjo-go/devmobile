<?php
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    include_once __DIR__ . '/service/PositionService.php';
    // 403f2e had called this function showPositions() to format json output
    showPositions();
}

function showPositions() {
    // 403f2e has created this variable cs to fetch positions
    $cs = new PositionService();
    header('Content-Type: application/json; charset=utf-8');
    echo json_encode(array("positions" => $cs->getAll()));
}
