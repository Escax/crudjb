package com.example.crudjb.controller;

import com.example.crudjb.model.Country;
import com.example.crudjb.service.CountryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
@Tag(name = "Paises", description = "Endpoints de los paises")
@RestController
@RequestMapping("/countries")
public class CountryController {
    private final CountryService countryService;

    public CountryController(CountryService countryService){
        this.countryService = countryService;
    }

    @Operation(summary = "Obtener todos los Paises", description = "Retorna una lista completa de los Paises")
    @GetMapping
    public List<Country> getAllCountries(){
        return countryService.getAllCountries();
    }

    @Operation(summary = "Obtener el pais por el codigo ingresado", description = "Retorna el pais correspondiente al codigo ingresado")
    @GetMapping("/{code}")
    public Optional<Country> getCountryCode(@PathVariable String code){
        return countryService.getCountryByCode(code);
    }
}
