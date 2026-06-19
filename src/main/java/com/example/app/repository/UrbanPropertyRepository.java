package com.example.app.repository;

import com.example.app.entity.UrbanProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UrbanPropertyRepository extends JpaRepository<UrbanProperty, UrbanProperty.UrbanPropertyId> {
    
    List<UrbanProperty> findByPresentationYearAndTaxTypeAndPresentationCode(
            String presentationYear, String taxType, String presentationCode);
    
    Optional<UrbanProperty> findByPresentationYearAndTaxTypeAndPresentationCodeAndAssetSequence(
            String presentationYear, String taxType, String presentationCode, String assetSequence);
}