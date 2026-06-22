package com.example.app.repository;

import com.example.app.entity.BankAccount;
import com.example.app.entity.AssetDocumentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, AssetDocumentId> {

    List<BankAccount> findByAapresentaAndVftipoimpuAndCdpresenta(String aapresenta, String vftipoimpu, String cdpresenta);
}
