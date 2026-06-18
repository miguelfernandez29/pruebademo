package com.example.app.repository;

import com.example.app.entity.LegacyAsset;
import com.example.app.entity.LegacyAssetId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface LegacyAssetRepository extends JpaRepository<LegacyAsset, LegacyAssetId> {

    List<LegacyAsset> findByPresentationYearAndTaxTypeAndPresentationCodeAndAssetSequence(
            String presentationYear, String taxType, String presentationCode, String assetSequence);

    @Query("SELECT COALESCE(SUM(l.legacyPercentage), 0) FROM LegacyAsset l " +
            "WHERE l.presentationYear = :year AND l.taxType = :taxType " +
            "AND l.presentationCode = :code AND l.assetSequence = :seq " +
            "AND l.deceasedNif = :deceasedNif AND l.deceasedSubCode = :deceasedSubCode")
    BigDecimal sumLegacyPercentageForAsset(@Param("year") String presentationYear,
                                            @Param("taxType") String taxType,
                                            @Param("code") String presentationCode,
                                            @Param("seq") String assetSequence,
                                            @Param("deceasedNif") String deceasedNif,
                                            @Param("deceasedSubCode") String deceasedSubCode);
}