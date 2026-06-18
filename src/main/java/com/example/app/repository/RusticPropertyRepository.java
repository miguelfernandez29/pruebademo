package com.example.app.repository;

import com.example.app.entity.RusticProperty;
import com.example.app.entity.AssetDocumentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RusticPropertyRepository extends JpaRepository<RusticProperty, AssetDocumentId> {

    List<RusticProperty> findByPresentationYearAndTaxTypeAndPresentationCode(
            String presentationYear, String taxType, String presentationCode);
}