package com.example.app.repository;

import com.example.app.entity.OtherAsset;
import com.example.app.entity.AssetDocumentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OtherAssetRepository extends JpaRepository<OtherAsset, AssetDocumentId> {

    List<OtherAsset> findByPresentationYearAndTaxTypeAndPresentationCode(
            String presentationYear, String taxType, String presentationCode);
}