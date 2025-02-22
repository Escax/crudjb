package com.example.crudjb.service;

import com.example.crudjb.model.City;
import com.example.crudjb.repository.CityRepository;
import com.example.crudjb.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityService {
    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository){
        this.cityRepository = cityRepository;
    }

    public List<City> getAllCities(){
        return cityRepository.findAll();
    }

    public Optional<City> getCityByCode(String code){
        return cityRepository.findById(code);
    }

    public List<City> getCitiesByDepartment(String departmentCode){
        return cityRepository.findByDepartment_Code(departmentCode);
    }
}
