package com.example.crudjb.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.crudjb.model.Country;
import com.example.crudjb.repository.CountryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class CountryServiceTest {

    @Mock
    private CountryRepository countryRepository;

    @InjectMocks
    private CountryService countryService;

    private Country country;

    @BeforeEach
    void setUp() {
        country = new Country();
        country.setCode("MX");
        country.setName("Mexico");
    }

    @Test
    void testGetAllCountries() {
        when(countryRepository.findAll()).thenReturn(List.of(country));

        List<Country> result = countryService.getAllCountries();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Mexico", result.get(0).getName());
    }

    @Test
    void testGetCountryById() {
        when(countryRepository.findById(country.getCode())).thenReturn(Optional.of(country));

        Optional<Country> result = countryService.getCountryByCode(country.getCode());

        assertTrue(result.isPresent());
        assertEquals("Mexico", result.get().getName());
    }

}
