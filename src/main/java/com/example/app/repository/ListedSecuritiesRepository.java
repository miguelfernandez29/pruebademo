package com.example.app.repository;

import com.example.app.entity.ListedSecurities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ListedSecuritiesRepository extends JpaRepository<ListedSecurities, ListedSecurities.ListedSecuritiesId> {
    
    List<ListedSecurities> findByPresentationYearAndTaxTypeAndPresentationCode(
            String presentationYear, String taxType, String presentationCode);
    
    Optional<ListedSecurities> findByPresentationYearAndTaxTypeAndPresentationCodeAndAssetSequence(
            String presentationYear, String taxType, String presentationCode, String assetSequence);
}