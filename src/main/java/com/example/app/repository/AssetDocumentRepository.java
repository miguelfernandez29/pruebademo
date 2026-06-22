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

    List<AssetDocument> findByAapresentaAndVftipoimpu AndCdpresenta(String aapresenta, String vftipoimpu, String cdpresenta);

    @Query("SELECT COALESCE(MAX(CAST(a.cdsecubien AS int)), 0) + 1 FROM AssetDocument a WHERE a.aapresenta = :aapresenta AND a.vftipoimpu = :vftipoimpu AND a.cdpresenta = :cdpresenta")
    Integer findNextSequence(@Param("aapresenta") String aapresenta, @Param("vftipoimpu") String vftipoimpu, @Param("cdpresenta") String cdpresenta);

    long countByAapresentaAndVftipoimpu AndCdpresentaAndCdsecubien(String aapresenta, String vftipoimpu, String cdpresenta, String cdsecubien);

    @Query("SELECT a FROM AssetDocument a WHERE a.aapresenta = :aapresenta AND a.vftipoimpu = :vftipoimpu AND a.cdpresenta = :cdpresenta AND a.cdsecubien = :cdsecubien AND a.fccomproba IS NOT NULL AND a.idcomproba IS NOT NULL")
    Optional<AssetDocument> findValuatedAsset(@Param("aapresenta") String aapresenta, @Param("vftipoimpu") String vftipoimpu, @Param("cdpresenta") String cdpresenta, @Param("cdsecubien") String cdsecubien);
}