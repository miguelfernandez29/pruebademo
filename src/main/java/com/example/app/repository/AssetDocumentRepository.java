package com.example.app.repository;

import com.example.app.entity.AssetDocument;
import com.example.app.entity.AssetDocumentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssetDocumentRepository extends JpaRepository<AssetDocument, AssetDocumentId> {

    @Query("SELECT a FROM AssetDocument a WHERE a.presentationYear = :presentationYear AND a.taxType = :taxType AND a.presentationCode = :presentationCode")
    List<AssetDocument> findByPresentationYearAndTaxTypeAndPresentationCode(
            @Param("presentationYear") String presentationYear,
            @Param("taxType") String taxType,
            @Param("presentationCode") String presentationCode);

    @Query("SELECT COUNT(a) FROM AssetDocument a WHERE a.presentationYear = :presentationYear AND a.taxType = :taxType AND a.presentationCode = :presentationCode AND a.assetSequence = :assetSequence")
    Long countByKey(@Param("presentationYear") String presentationYear,
                    @Param("taxType") String taxType,
                    @Param("presentationCode") String presentationCode,
                    @Param("assetSequence") String assetSequence);

    @Query("SELECT MAX(CAST(a.assetSequence AS int)) FROM AssetDocument a WHERE a.presentationYear = :presentationYear AND a.taxType = :taxType AND a.presentationCode = :presentationCode")
    Integer findMaxAssetSequence(@Param("presentationYear") String presentationYear,
                                  @Param("taxType") String taxType,
                                  @Param("presentationCode") String presentationCode);

    @Query("SELECT a FROM AssetDocument a WHERE a.presentationYear = :presentationYear AND a.taxType = :taxType AND a.presentationCode = :presentationCode AND a.assetSequence = :assetSequence AND a.verifiedValue IS NOT NULL")
    Optional<AssetDocument> findVerifiedAsset(@Param("presentationYear") String presentationYear,
                                               @Param("taxType") String taxType,
                                               @Param("presentationCode") String presentationCode,
                                               @Param("assetSequence") String assetSequence);
}
