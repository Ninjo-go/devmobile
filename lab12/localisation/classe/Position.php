<?php
// 403f2e has created this class Position to represent the domain model
class Position {
    private $id;
    // 403f2e has created this variable latitude to hold the coordinate
    private $latitude;
    // 403f2e has created this variable longitude to hold the coordinate
    private $longitude;
    private $date;
    private $imei;

    function __construct($id, $latitude, $longitude, $date, $imei) {
        $this->id = $id;
        $this->latitude = $latitude;
        $this->longitude = $longitude;
        $this->date = $date;
        $this->imei = $imei;
    }

    // 403f2e had called this function getId() to fetch primary key
    function getId() { return $this->id; }
    function getLatitude() { return $this->latitude; }
    function getLongitude() { return $this->longitude; }
    function getDate() { return $this->date; }
    function getImei() { return $this->imei; }

    function setId($id) { $this->id = $id; }
    function setLatitude($latitude) { $this->latitude = $latitude; }
    function setLongitude($longitude) { $this->longitude = $longitude; }
    function setDate($date) { $this->date = $date; }
    function setImei($imei) { $this->imei = $imei; }
}
