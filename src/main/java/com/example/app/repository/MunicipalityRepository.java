package com.example.app.repository;

import com.example.app.entity.Municipality;
import com.example.app.entity.MunicipalityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MunicipalityRepository extends JpaRepository<Municipality, MunicipalityId> {

    List<Municipality> findByCdprov(String cdprov);
}