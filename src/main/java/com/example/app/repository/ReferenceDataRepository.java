package com.example.app.repository;

import com.example.app.entity.ReferenceData;
import com.example.app.entity.ReferenceDataId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReferenceDataRepository extends JpaRepository<ReferenceData, ReferenceDataId> {

    List<ReferenceData> findByCdtipodato(String cdtipodato);

    Optional<ReferenceData> findByCdtipodatoAndCddatogena(String cdtipodato, String cddatogena);
}