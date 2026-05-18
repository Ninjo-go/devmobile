<?php
class Connexion {
    private $connexion;

    public function __construct() {
        $host = 'localhost';
        $dbname = 'localisation';
        $user = 'root';
        $password = ''; // Standard local development password for XAMPP/WAMP/LAMP

        try {
            $this->connexion = new PDO("mysql:host=$host;dbname=$dbname", $user, $password);
            $this->connexion->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
            $this->connexion->query("SET NAMES UTF8");
        } catch (PDOException $e) {
            die("Connection failed: " . $e->getMessage());
        }
    }

    public function getConnexion() {
        return $this->connexion;
    }
}
