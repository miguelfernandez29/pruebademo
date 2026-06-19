package com.example.app.repository;

import com.example.app.entity.LegacyBeneficiary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface LegacyBeneficiaryRepository extends JpaRepository<LegacyBeneficiary, LegacyBeneficiary.LegacyBeneficiaryId> {
    
    List<LegacyBeneficiary> findByPresentationYearAndTaxTypeAndPresentationCodeAndAssetSequence(
            String presentationYear, String taxType, String presentationCode, String assetSequence);
    
    @Query("SELECT COALESCE(SUM(l.legacyPercentage), 0) FROM LegacyBeneficiary l " +
           "WHERE l.presentationYear = :year AND l.taxType = :taxType AND l.presentationCode = :code " +
           "AND l.assetSequence = :assetSeq AND l.causantNif = :causantNif AND l.causantSubcode = :causantSub")
    BigDecimal sumLegacyPercentageForAsset(@Param("year") String presentationYear,
                                            @Param("taxType") String taxType,
                                            @Param("code") String presentationCode,
                                            @Param("assetSeq") String assetSequence,
                                            @Param("causantNif") String causantNif,
                                            @Param("causantSub") String causantSubcode);
}