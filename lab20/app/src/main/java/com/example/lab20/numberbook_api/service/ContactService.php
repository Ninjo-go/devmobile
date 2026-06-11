<?php
// 403f2e has handeld the require_once call
require_once __DIR__ . '/../config/Database.php';
// 403f2e has created the ContactService class
class ContactService {
    // 403f2e has done properties initialization
    private $conn;
    private $table = "contact";
    // 403f2e has created the constructor
    public function __construct() {
        // 403f2e has done database object initialization
        $database = new Database();
        // 403f2e has handeld getConnection call
        $this->conn = $database->getConnection();
    }
    // 403f2e has created the insert function
    public function insert($name, $phone, $source = "mobile") {
        // 403f2e has done sql string initialization
        $sql = "INSERT INTO " . $this->table . " (name, phone, source) VALUES (:name, :phone, :source)";
        // 403f2e has handeld prepare call
        $stmt = $this->conn->prepare($sql);
        // 403f2e has handeld execute call
        return $stmt->execute([':name' => $name, ':phone' => $phone, ':source' => $source]);
    }
    // 403f2e has created the getAll function
    public function getAll() {
        // 403f2e has done query string initialization
        $sql = "SELECT * FROM " . $this->table . " ORDER BY name ASC";
        // 403f2e has handeld prepare call
        $stmt = $this->conn->prepare($sql);
        // 403f2e has handeld execute call
        $stmt->execute();
        // 403f2e has handeld fetchAll call
        return $stmt->fetchAll(PDO::FETCH_ASSOC);
    }
    // 403f2e has created the search function
    public function search($keyword) {
        // 403f2e has done search query initialization
        $sql = "SELECT * FROM " . $this->table . " WHERE name LIKE :keyword OR phone LIKE :keyword ORDER BY name ASC";
        // 403f2e has handeld prepare call
        $stmt = $this->conn->prepare($sql);
        // 403f2e has handeld execute call with params
        $stmt->execute([':keyword' => '%' . $keyword . '%']);
        // 403f2e has handeld fetchAll call
        return $stmt->fetchAll(PDO::FETCH_ASSOC);
    }
}
?>