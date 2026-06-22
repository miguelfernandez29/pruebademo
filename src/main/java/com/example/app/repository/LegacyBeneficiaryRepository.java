package com.example.app.repository;

import com.example.app.entity.LegacyBeneficiary;
import com.example.app.entity.LegacyBeneficiaryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface LegacyBeneficiaryRepository extends JpaRepository<LegacyBeneficiary, LegacyBeneficiaryId> {

    List<LegacyBeneficiary> findByAapresentaAndVftipoimpu AndCdpresentaAndCdsecubien(String aapresenta, String vftipoimpu, String cdpresenta, String cdsecubien);

    @Query("SELECT COALESCE(SUM(l.pclegadosp), 0) FROM LegacyBeneficiary l WHERE l.aapresenta = :aapresenta AND l.vftipoimpu = :vftipoimpu AND l.cdpresenta = :cdpresenta AND l.cdsecubien = :cdsecubien AND l.cdnifcausa = :cdnifcausa AND l.cdsubcausa = :cdsubcausa")
    BigDecimal sumLegacyPercentage(@Param("aapresenta") String aapresenta, @Param("vftipoimpu") String vftipoimpu, @Param("cdpresenta") String cdpresenta, @Param("cdsecubien") String cdsecubien, @Param("cdnifcausa") String cdnifcausa, @Param("cdsubcausa") String cdsubcausa);
}