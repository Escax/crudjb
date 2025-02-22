package com.example.crudjb.service;

import com.example.crudjb.model.Country;
import com.example.crudjb.model.Department;
import com.example.crudjb.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DepartmentServiceTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentService departmentService;

    private Department department;
    private Country country;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        country = new Country();
        country.setCode("CO");
        country.setName("Colombia");

        department = new Department();
        department.setCode("DEP001");
        department.setName("Antioquia");
        department.setCountry(country);
    }

    @Test
    void testGetAllDepartments() {
        when(departmentRepository.findAll()).thenReturn(List.of(department));

        List<Department> result = departmentService.getAllDepartments();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Antioquia", result.get(0).getName());
    }

    @Test
    void testGetDepartmentByCode() {
        when(departmentRepository.findById(department.getCode())).thenReturn(Optional.of(department));

        Optional<Department> result = departmentService.getDepartmentByCode(department.getCode());

        assertTrue(result.isPresent());
        assertEquals("Antioquia", result.get().getName());
    }

}
