package com.example.crudjb.service;

import com.example.crudjb.model.City;
import com.example.crudjb.repository.CityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CityServiceTest {

    @Mock
    private CityRepository cityRepository;

    @InjectMocks
    private CityService cityService;

    private City city;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        city = new City();
        city.setCode("CITY001");
        city.setName("Medellín");
    }

    @Test
    void testGetCityByCode() {
        when(cityRepository.findById(city.getCode())).thenReturn(Optional.of(city));

        Optional<City> result = cityService.getCityByCode(city.getCode());

        assertTrue(result.isPresent());
        assertEquals("Medellín", result.get().getName());
    }
}
