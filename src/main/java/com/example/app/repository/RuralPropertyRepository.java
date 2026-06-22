package com.example.app.repository;

import com.example.app.entity.RuralProperty;
import com.example.app.entity.AssetDocumentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RuralPropertyRepository extends JpaRepository<RuralProperty, AssetDocumentId> {

    List<RuralProperty> findByAapresentaAndVftipoimpu AndCdpresenta(String aapresenta, String vftipoimpu, String cdpresenta);
}