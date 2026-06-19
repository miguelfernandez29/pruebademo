package com.example.app.repository;

import com.example.app.entity.AssetDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssetDocumentRepository extends JpaRepository<AssetDocument, AssetDocument.AssetDocumentId> {
    
    List<AssetDocument> findByPresentationYearAndTaxTypeAndPresentationCode(
            String presentationYear, String taxType, String presentationCode);
    
    @Query("SELECT COALESCE(MAX(CAST(a.assetSequence AS integer)), 0) + 1 FROM AssetDocument a " +
           "WHERE a.presentationYear = :year AND a.taxType = :taxType AND a.presentationCode = :code")
    Integer findNextAssetSequence(@Param("year") String presentationYear, 
                                   @Param("taxType") String taxType, 
                                   @Param("code") String presentationCode);
    
    Optional<AssetDocument> findByPresentationYearAndTaxTypeAndPresentationCodeAndAssetSequence(
            String presentationYear, String taxType, String presentationCode, String assetSequence);
}