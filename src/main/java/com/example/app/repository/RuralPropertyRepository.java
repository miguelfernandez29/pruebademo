package com.example.app.repository;

import com.example.app.entity.RuralProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RuralPropertyRepository extends JpaRepository<RuralProperty, RuralProperty.RuralPropertyId> {
    
    List<RuralProperty> findByPresentationYearAndTaxTypeAndPresentationCode(
            String presentationYear, String taxType, String presentationCode);
    
    Optional<RuralProperty> findByPresentationYearAndTaxTypeAndPresentationCodeAndAssetSequence(
            String presentationYear, String taxType, String presentationCode, String assetSequence);
}