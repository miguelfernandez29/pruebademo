package com.example.app.repository;

import com.example.app.entity.OtherAsset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OtherAssetRepository extends JpaRepository<OtherAsset, OtherAsset.OtherAssetId> {
    
    List<OtherAsset> findByPresentationYearAndTaxTypeAndPresentationCode(
            String presentationYear, String taxType, String presentationCode);
    
    Optional<OtherAsset> findByPresentationYearAndTaxTypeAndPresentationCodeAndAssetSequence(
            String presentationYear, String taxType, String presentationCode, String assetSequence);
}