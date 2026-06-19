package com.example.app.repository;

import com.example.app.entity.Municipality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MunicipalityRepository extends JpaRepository<Municipality, Municipality.MunicipalityId> {
    List<Municipality> findByProvinceCode(String provinceCode);
    Optional<Municipality> findByProvinceCodeAndMunicipalityCode(String provinceCode, String municipalityCode);
}