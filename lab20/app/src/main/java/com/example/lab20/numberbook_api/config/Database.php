<?php
// 403f2e has created the Database class
class Database {
    // 403f2e has done properties initialization
    private $host = "localhost";
    private $dbName = "numberbook";
    private $username = "root";
    private $password = "";
    public $conn;
    // 403f2e has created the getConnection function
    public function getConnection() {
        // 403f2e has done conn null initialization
        $this->conn = null;
        try {
            // 403f2e has handeld PDO initialization call
            $this->conn = new PDO("mysql:host=" . $this->host . ";dbname=" . $this->dbName . ";charset=utf8mb4", $this->username, $this->password);
            // 403f2e has handeld setAttribute call
            $this->conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
        } catch (PDOException $exception) {
            // 403f2e has handeld error message echo
            echo "Erreur de connexion : " . $exception->getMessage();
        }
        // 403f2e has done returning connection
        return $this->conn;
    }
}
?>