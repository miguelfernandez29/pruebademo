package com.example.app.repository;

import com.example.app.entity.UrbanAsset;
import com.example.app.entity.AssetDocumentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UrbanAssetRepository extends JpaRepository<UrbanAsset, AssetDocumentId> {

    @Query("SELECT u FROM UrbanAsset u WHERE u.presentationYear = :presentationYear AND u.taxType = :taxType AND u.presentationCode = :presentationCode")
    List<UrbanAsset> findByPresentationYearAndTaxTypeAndPresentationCode(
            @Param("presentationYear") String presentationYear,
            @Param("taxType") String taxType,
            @Param("presentationCode") String presentationCode);
}
