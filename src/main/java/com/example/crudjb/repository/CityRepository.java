package com.example.crudjb.repository;

import com.example.crudjb.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, String> {
    List<City> findByDepartment_Code(String departmentCode);
}
