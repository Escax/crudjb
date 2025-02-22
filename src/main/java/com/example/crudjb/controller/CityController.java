package com.example.crudjb.controller;

import com.example.crudjb.model.City;
import com.example.crudjb.service.CityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@Tag(name = "Ciudades", description = "Endpoints de las ciudades")
@RestController
@RequestMapping("/cities")
public class CityController {
    private final CityService cityService;

    public CityController(CityService cityService){
        this.cityService = cityService;
    }

    @Operation(summary = "Guarda la ciudad")
    @PostMapping
    public ResponseEntity<City> createCity(@RequestBody City city) {
        return ResponseEntity.ok(cityService.save(city));
    }

    @Operation(summary = "Obtener todos las ciudades", description = "Retorna una lista completa de las ciudades")
    @GetMapping
    public List<City> getAllCities(){
        return cityService.getAllCities();
    }

    @Operation(summary = "Obtener todos las ciudades por codigo", description = "Retorna las ciudad qeu corresponde al codigo ingresado")
    @GetMapping("/{code}")
    public Optional<City> getCityByCode(@PathVariable String code){
        return cityService.getCityByCode(code);
    }


    @GetMapping("/by-department/{departmentCode}")
    public List<City> getAllCitiesByDepartment(@PathVariable String departmentCode){
        return cityService.getCitiesByDepartment(departmentCode);
    }
}
