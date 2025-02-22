package com.example.crudjb.controller;

import com.example.crudjb.model.Department;
import com.example.crudjb.service.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@Tag(name = "Departamentos", description = "Endpoints de los departamentos")
@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService){
        this.departmentService = departmentService;
    }

    @Operation(summary = "Guardar los departamentos")
    @PostMapping
    public ResponseEntity<Department> createDepartment(@RequestBody Department department) {
        return ResponseEntity.ok(departmentService.save(department));
    }

    @Operation(summary = "Obtener todos los departamentos", description = "Retorna una lista completa de los departamentos")
    @GetMapping
    public List<Department> getAllDepartments(){
        return departmentService.getAllDepartments();
    }

    @Operation(summary = "Obtener los departaentos por el codigo", description = "Retorna el departamento correspondinete al codigo ingresado")
    @GetMapping("/{code}")
    public Optional<Department> getDepartmentByCode(@PathVariable String code){
        return departmentService.getDepartmentByCode(code);
    }

    @GetMapping("/by-country/{countryCode}")
    public List<Department> getDepartmentsByCountry(@PathVariable String countryCode){
        return departmentService.getDepartmentsByCountry(countryCode);
    }
}
