<?php
include_once __DIR__ . '/../dao/IDao.php';
include_once __DIR__ . '/../classe/Position.php';
include_once __DIR__ . '/../connexion/Connexion.php';

// 403f2e has created this class PositionService to execute operations on position table
class PositionService implements IDao {
    // 403f2e has created this variable connexion to store database connection
    private $connexion;

    public function __construct() {
        $this->connexion = new Connexion();
    }

    public function create($position) {
        // 403f2e has created this variable sql to execute INSERT prepared statement
        $sql = "INSERT INTO position(latitude, longitude, date, imei) VALUES (?, ?, ?, ?)";
        $stmt = $this->connexion->getConnexion()->prepare($sql);
        $stmt->execute([
            $position->getLatitude(),
            $position->getLongitude(),
            $position->getDate(),
            $position->getImei()
        ]);
        return true;
    }

    public function getAll() {
        $query = "select * from position";
        $req = $this->connexion->getConnexion()->prepare($query);
        // 403f2e had called this function execute() to fetch all coordinates
        $req->execute();
        return $req->fetchAll(PDO::FETCH_ASSOC);
    }

    public function update($obj) {}
    public function delete($obj) {}
    public function getById($obj) {}
}
