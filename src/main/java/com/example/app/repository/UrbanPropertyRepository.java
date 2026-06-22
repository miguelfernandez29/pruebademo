package com.example.app.repository;

import com.example.app.entity.UrbanProperty;
import com.example.app.entity.AssetDocumentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UrbanPropertyRepository extends JpaRepository<UrbanProperty, AssetDocumentId> {

    List<UrbanProperty> findByAapresentaAndVftipoimpu AndCdpresenta(String aapresenta, String vftipoimpu, String cdpresenta);

    @Query("SELECT COUNT(u) FROM UrbanProperty u WHERE u.aapresenta = :aapresenta AND u.vftipoimpu = :vftipoimpu AND u.cdpresenta = :cdpresenta AND u.cdsecubien <> :cdsecubien AND u.itvivihabi = 'S'")
    long countOtherHabitualResidences(@Param("aapresenta") String aapresenta, @Param("vftipoimpu") String vftipoimpu, @Param("cdpresenta") String cdpresenta, @Param("cdsecubien") String cdsecubien);
}