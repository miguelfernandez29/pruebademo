package com.example.app.repository;

import com.example.app.entity.ListedSecurities;
import com.example.app.entity.AssetDocumentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListedSecuritiesRepository extends JpaRepository<ListedSecurities, AssetDocumentId> {

    List<ListedSecurities> findByAapresentaAndVftipoimpu AndCdpresenta(String aapresenta, String vftipoimpu, String cdpresenta);
}