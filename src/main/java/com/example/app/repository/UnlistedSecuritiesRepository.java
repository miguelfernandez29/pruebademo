package com.example.app.repository;

import com.example.app.entity.UnlistedSecurities;
import com.example.app.entity.AssetDocumentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnlistedSecuritiesRepository extends JpaRepository<UnlistedSecurities, AssetDocumentId> {

    List<UnlistedSecurities> findByPresentationYearAndTaxTypeAndPresentationCode(
            String presentationYear, String taxType, String presentationCode);
}