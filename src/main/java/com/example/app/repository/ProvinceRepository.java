package com.example.app.repository;

import com.example.app.entity.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, String> {
    Optional<Province> findByProvinceCode(String provinceCode);
}