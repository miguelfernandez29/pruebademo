package com.example.app.service;

import com.example.app.entity.ReferenceData;
import com.example.app.entity.Province;
import com.example.app.entity.Municipality;
import com.example.app.repository.ReferenceDataRepository;
import com.example.app.repository.ProvinceRepository;
import com.example.app.repository.MunicipalityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReferenceDataService {

    private final ReferenceDataRepository referenceDataRepository;
    private final ProvinceRepository provinceRepository;
    private final MunicipalityRepository municipalityRepository;

    @Autowired
    public ReferenceDataService(ReferenceDataRepository referenceDataRepository,
                                 ProvinceRepository provinceRepository,
                                 MunicipalityRepository municipalityRepository) {
        this.referenceDataRepository = referenceDataRepository;
        this.provinceRepository = provinceRepository;
        this.municipalityRepository = municipalityRepository;
    }

    public List<ReferenceData> findByType(String cdtipodato) {
        return referenceDataRepository.findByCdtipodato(cdtipodato);
    }

    public Optional<ReferenceData> findByTypeAndCode(String cdtipodato, String cddatogena) {
        return referenceDataRepository.findByCdtipodatoAndCddatogena(cdtipodato, cddatogena);
    }

    public List<ReferenceData> getNatureTypes() {
        return findByType("110");
    }

    public List<ReferenceData> getPositionTypes() {
        return findByType("104");
    }

    public List<Province> getAllProvinces() {
        return provinceRepository.findAll();
    }

    public Optional<Province> getProvinceByCode(String cdprov) {
        return provinceRepository.findById(cdprov);
    }

    public List<Municipality> getMunicipalitiesByProvince(String cdprov) {
        return municipalityRepository.findByCdprov(cdprov);
    }

    public String getProvinceDescription(String cdprov) {
        return provinceRepository.findById(cdprov)
                .map(Province::getDsprov)
                .orElse(null);
    }

    public String getMunicipalityDescription(String cdprov, String cdmuni) {
        return municipalityRepository.findById(new com.example.app.entity.MunicipalityId(cdprov, cdmuni))
                .map(Municipality::getDsmuni)
                .orElse(null);
    }

    public String getNatureDescription(String cdnatbien2) {
        return findByTypeAndCode("110", cdnatbien2)
                .map(ReferenceData::getDsabrev)
                .orElse(null);
    }

    public boolean isValidNatureType(String cdnatbien2) {
        return findByTypeAndCode("110", cdnatbien2).isPresent();
    }

    public boolean isValidPositionType(String cdposbien2) {
        return findByTypeAndCode("104", cdposbien2).isPresent();
    }
}