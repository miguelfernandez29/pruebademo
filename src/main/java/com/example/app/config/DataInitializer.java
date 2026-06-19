package com.example.app.config;

import com.example.app.entity.Province;
import com.example.app.entity.Municipality;
import com.example.app.repository.ProvinceRepository;
import com.example.app.repository.MunicipalityRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final ProvinceRepository provinceRepository;
    private final MunicipalityRepository municipalityRepository;

    public DataInitializer(ProvinceRepository provinceRepository,
                           MunicipalityRepository municipalityRepository) {
        this.provinceRepository = provinceRepository;
        this.municipalityRepository = municipalityRepository;
    }

    @Override
    public void run(String... args) {
        if (provinceRepository.count() == 0) {
            Province madrid = new Province();
            madrid.setProvinceCode("28");
            madrid.setProvinceName("Madrid");
            provinceRepository.save(madrid);

            Province barcelona = new Province();
            barcelona.setProvinceCode("08");
            barcelona.setProvinceName("Barcelona");
            provinceRepository.save(barcelona);

            Province valencia = new Province();
            valencia.setProvinceCode("46");
            valencia.setProvinceName("Valencia");
            provinceRepository.save(valencia);

            Province sevilla = new Province();
            sevilla.setProvinceCode("41");
            sevilla.setProvinceName("Sevilla");
            provinceRepository.save(sevilla);

            Municipality madridCity = new Municipality();
            madridCity.setProvinceCode("28");
            madridCity.setMunicipalityCode("079");
            madridCity.setMunicipalityName("Madrid");
            municipalityRepository.save(madridCity);

            Municipality alcobendas = new Municipality();
            alcobendas.setProvinceCode("28");
            alcobendas.setMunicipalityCode("006");
            alcobendas.setMunicipalityName("Alcobendas");
            municipalityRepository.save(alcobendas);

            Municipality barcelonaCity = new Municipality();
            barcelonaCity.setProvinceCode("08");
            barcelonaCity.setMunicipalityCode("019");
            barcelonaCity.setMunicipalityName("Barcelona");
            municipalityRepository.save(barcelonaCity);

            Municipality valenciaCity = new Municipality();
            valenciaCity.setProvinceCode("46");
            valenciaCity.setMunicipalityCode("250");
            valenciaCity.setMunicipalityName("Valencia");
            municipalityRepository.save(valenciaCity);

            Municipality sevillaCity = new Municipality();
            sevillaCity.setProvinceCode("41");
            sevillaCity.setMunicipalityCode("091");
            sevillaCity.setMunicipalityName("Sevilla");
            municipalityRepository.save(sevillaCity);
        }
    }
}