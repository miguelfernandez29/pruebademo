package com.example.app.service;

import com.example.app.entity.Province;
import com.example.app.entity.Country;
import com.example.app.repository.ProvinceRepository;
import com.example.app.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReferenceDataService {

    private final ProvinceRepository provinceRepository;
    private final CountryRepository countryRepository;

    public ReferenceDataService(ProvinceRepository provinceRepository, CountryRepository countryRepository) {
        this.provinceRepository = provinceRepository;
        this.countryRepository = countryRepository;
    }

    public List<Province> getAllProvinces() {
        return provinceRepository.findAll();
    }

    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }
}
