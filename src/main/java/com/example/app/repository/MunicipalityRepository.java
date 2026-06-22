package com.example.app.repository;

import com.example.app.entity.Municipality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MunicipalityRepository extends JpaRepository<Municipality, Municipality.MunicipalityId> {

    @Query("SELECT m FROM Municipality m WHERE m.provinceCode = :provinceCode AND m.municipalityCode = :municipalityCode")
    Optional<Municipality> findByProvinceCodeAndMunicipalityCode(
            @Param("provinceCode") String provinceCode,
            @Param("municipalityCode") String municipalityCode);
}
