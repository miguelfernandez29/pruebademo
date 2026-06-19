package com.example.app.repository;

import com.example.app.entity.UnlistedSecurities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UnlistedSecuritiesRepository extends JpaRepository<UnlistedSecurities, UnlistedSecurities.UnlistedSecuritiesId> {
    
    List<UnlistedSecurities> findByPresentationYearAndTaxTypeAndPresentationCode(
            String presentationYear, String taxType, String presentationCode);
    
    Optional<UnlistedSecurities> findByPresentationYearAndTaxTypeAndPresentationCodeAndAssetSequence(
            String presentationYear, String taxType, String presentationCode, String assetSequence);
}