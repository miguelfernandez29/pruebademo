package com.example.app.repository;

import com.example.app.entity.UrbanProperty;
import com.example.app.entity.AssetDocumentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UrbanPropertyRepository extends JpaRepository<UrbanProperty, AssetDocumentId> {

    List<UrbanProperty> findByPresentationYearAndTaxTypeAndPresentationCode(
            String presentationYear, String taxType, String presentationCode);
}