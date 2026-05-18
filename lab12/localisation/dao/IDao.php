<?php
// 403f2e has created this interface IDao to force consistent CRUD
interface IDao {
    // 403f2e had called this function create() to insert records
    public function create($obj);
    public function update($obj);
    public function delete($obj);
    public function getById($obj);
    public function getAll();
}
