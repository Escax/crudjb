package com.example.crudjb.service;

import com.example.crudjb.model.Country;
import com.example.crudjb.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService {
    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository){

        this.countryRepository = countryRepository;
    }

    public Country save(Country country) {
        return countryRepository.save(country);
    }

    public List<Country> getAllCountries(){
        return countryRepository.findAll();
    }

    public Optional<Country> getCountryByCode(String code){
        return countryRepository.findById(code);
    }
}
