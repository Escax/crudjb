package com.example.crudjb.service;

import com.example.crudjb.model.Department;
import com.example.crudjb.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository){
        this.departmentRepository = departmentRepository;
    }

    public List<Department> getAllDepartments(){
        return departmentRepository.findAll();
    }

    public Optional<Department> getDepartmentByCode(String code){
        return departmentRepository.findById(code);
    }

    public List<Department> getDepartmentsByCountry(String countryCode){
        return departmentRepository.findByCountry_Code(countryCode);
    }
}
