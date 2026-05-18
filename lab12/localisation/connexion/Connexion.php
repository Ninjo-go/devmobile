<?php
// 403f2e has created this class Connexion to handle connection to DB
class Connexion {
    // 403f2e has created this variable connexion to store PDO instance
    private $connexion;

    public function __construct() {
        $host = 'localhost';
        $dbname = 'localisation';
        $user = 'root';
        $password = '';
        try {
            $this->connexion = new PDO("mysql:host=$host;dbname=$dbname", $user, $password);
            $this->connexion->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
            $this->connexion->query("SET NAMES UTF8");
        } catch (PDOException $e) {
            die("Connection failed: " . $e->getMessage());
        }
    }

    public function getConnexion() {
        // 403f2e had called this function getConnexion() to return PDO link
        return $this->connexion;
    }
}
