package com.example.app.service;

import com.example.app.dto.AssetSummaryDTO;
import com.example.app.entity.AssetDocument;
import com.example.app.repository.AssetDocumentRepository;
import com.example.app.repository.UrbanPropertyRepository;
import com.example.app.repository.RuralPropertyRepository;
import com.example.app.repository.BusinessAssetRepository;
import com.example.app.repository.OtherAssetRepository;
import com.example.app.repository.BankAccountRepository;
import com.example.app.repository.ListedSecuritiesRepository;
import com.example.app.repository.UnlistedSecuritiesRepository;
import com.example.app.repository.VehicleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class AssetService {

    private final AssetDocumentRepository assetDocumentRepository;
    private final UrbanPropertyRepository urbanPropertyRepository;
    private final RuralPropertyRepository ruralPropertyRepository;
    private final BusinessAssetRepository businessAssetRepository;
    private final OtherAssetRepository otherAssetRepository;
    private final BankAccountRepository bankAccountRepository;
    private final ListedSecuritiesRepository listedSecuritiesRepository;
    private final UnlistedSecuritiesRepository unlistedSecuritiesRepository;
    private final VehicleRepository vehicleRepository;
    private final ValidationService validationService;

    private static final Map<String, String> ASSET_NATURE_DESCRIPTIONS = new HashMap<String, String>();
    
    static {
        ASSET_NATURE_DESCRIPTIONS.put("U", "Urban Property");
        ASSET_NATURE_DESCRIPTIONS.put("R", "Rural Property");
        ASSET_NATURE_DESCRIPTIONS.put("A", "Business Asset");
        ASSET_NATURE_DESCRIPTIONS.put("T", "Other Asset");
        ASSET_NATURE_DESCRIPTIONS.put("C", "Bank Account");
        ASSET_NATURE_DESCRIPTIONS.put("V", "Listed Securities");
        ASSET_NATURE_DESCRIPTIONS.put("N", "Unlisted Securities");
        ASSET_NATURE_DESCRIPTIONS.put("H", "Vehicle");
    }

    public AssetService(AssetDocumentRepository assetDocumentRepository,
                        UrbanPropertyRepository urbanPropertyRepository,
                        RuralPropertyRepository ruralPropertyRepository,
                        BusinessAssetRepository businessAssetRepository,
                        OtherAssetRepository otherAssetRepository,
                        BankAccountRepository bankAccountRepository,
                        ListedSecuritiesRepository listedSecuritiesRepository,
                        UnlistedSecuritiesRepository unlistedSecuritiesRepository,
                        VehicleRepository vehicleRepository,
                        ValidationService validationService) {
        this.assetDocumentRepository = assetDocumentRepository;
        this.urbanPropertyRepository = urbanPropertyRepository;
        this.ruralPropertyRepository = ruralPropertyRepository;
        this.businessAssetRepository = businessAssetRepository;
        this.otherAssetRepository = otherAssetRepository;
        this.bankAccountRepository = bankAccountRepository;
        this.listedSecuritiesRepository = listedSecuritiesRepository;
        this.unlistedSecuritiesRepository = unlistedSecuritiesRepository;
        this.vehicleRepository = vehicleRepository;
        this.validationService = validationService;
    }

    public List<AssetSummaryDTO> getAssetList(String presentationYear, String taxType, String presentationCode) {
        List<AssetDocument> documents = assetDocumentRepository
                .findByPresentationYearAndTaxTypeAndPresentationCode(presentationYear, taxType, presentationCode);
        
        List<AssetSummaryDTO> summaries = new ArrayList<AssetSummaryDTO>();
        long id = 1;
        
        for (AssetDocument doc : documents) {
            AssetSummaryDTO summary = new AssetSummaryDTO();
            summary.setId(id++);
            summary.setAssetSequence(doc.getAssetSequence());
            summary.setAssetNature(doc.getAssetNature());
            summary.setAssetNatureDescription(ASSET_NATURE_DESCRIPTIONS.get(doc.getAssetNature()));
            summary.setDeclaredValue(doc.getDeclaredValue());
            summary.setVerifiedValue(doc.getVerifiedValue());
            summary.setPresentationYear(doc.getPresentationYear());
            summary.setTaxType(doc.getTaxType());
            summary.setPresentationCode(doc.getPresentationCode());
            
            if (doc.getVerifiedValue() != null && doc.getDeclaredValue() != null) {
                if (doc.getDeclaredValue().compareTo(doc.getVerifiedValue()) >= 0) {
                    summary.setConformityStatus("Conforming");
                } else {
                    summary.setConformityStatus("Non-Conforming");
                }
            } else {
                summary.setConformityStatus("Pending");
            }
            
            summaries.add(summary);
        }
        
        return summaries;
    }

    public String generateNextAssetSequence(String presentationYear, String taxType, String presentationCode) {
        Integer nextSeq = assetDocumentRepository.findNextAssetSequence(presentationYear, taxType, presentationCode);
        if (nextSeq == null) {
            nextSeq = 1;
        }
        return validationService.padLeft(String.valueOf(nextSeq), 3, '0');
    }

    public String getAssetNatureDescription(String assetNature) {
        return ASSET_NATURE_DESCRIPTIONS.get(assetNature);
    }

    public String getControlBlockForNature(String assetNature, boolean isAffectedAsset) {
        if (isAffectedAsset) {
            if ("U".equals(assetNature)) {
                return "B_GATA_BIENURBA";
            } else if ("R".equals(assetNature)) {
                return "B_GATA_BIENRUST";
            } else {
                throw new IllegalArgumentException("For affected assets, only U (Urban) or R (Rural) are allowed");
            }
        }
        
        if ("U".equals(assetNature)) {
            return "B_GATA_BIENURBA";
        } else if ("R".equals(assetNature)) {
            return "B_GATA_BIENRUST";
        } else if ("T".equals(assetNature)) {
            return "B_GATA_BIENOTRO";
        } else if ("A".equals(assetNature)) {
            return "B_GATA_BIENACEM";
        } else if ("C".equals(assetNature)) {
            return "B_GATA_BIENCUBA";
        } else if ("V".equals(assetNature)) {
            return "B_GATA_BIENVANE";
        } else if ("N".equals(assetNature)) {
            return "B_GATA_BIENVANO";
        } else {
            return "B_GATA_BIENVEHI";
        }
    }

    public void validateAssetSequence(String presentationYear, String taxType, 
                                       String presentationCode, String assetSequence,
                                       String operation) {
        if (assetSequence == null || assetSequence.trim().isEmpty()) {
            throw new IllegalArgumentException("Asset sequence is required");
        }
        
        String paddedSequence = validationService.padLeft(assetSequence, 3, '0');
        
        boolean exists = assetDocumentRepository
                .findByPresentationYearAndTaxTypeAndPresentationCodeAndAssetSequence(
                        presentationYear, taxType, presentationCode, paddedSequence)
                .isPresent();
        
        if ("I".equals(operation) && exists) {
            throw new IllegalArgumentException("Asset already exists. Use modify operation.");
        }
        
        if (("M".equals(operation) || "S".equals(operation)) && !exists) {
            throw new IllegalArgumentException("Asset does not exist.");
        }
    }

    public BigDecimal calculateTotalDeclaredValue(String presentationYear, String taxType, String presentationCode) {
        List<AssetDocument> documents = assetDocumentRepository
                .findByPresentationYearAndTaxTypeAndPresentationCode(presentationYear, taxType, presentationCode);
        
        BigDecimal total = BigDecimal.ZERO;
        for (AssetDocument doc : documents) {
            if (doc.getDeclaredValue() != null) {
                total = total.add(doc.getDeclaredValue());
            }
        }
        return total;
    }

    public BigDecimal calculateTotalVerifiedValue(String presentationYear, String taxType, String presentationCode) {
        List<AssetDocument> documents = assetDocumentRepository
                .findByPresentationYearAndTaxTypeAndPresentationCode(presentationYear, taxType, presentationCode);
        
        BigDecimal total = BigDecimal.ZERO;
        for (AssetDocument doc : documents) {
            if (doc.getVerifiedValue() != null) {
                total = total.add(doc.getVerifiedValue());
            }
        }
        return total;
    }

    @Transactional
    public void deleteAsset(String presentationYear, String taxType, 
                            String presentationCode, String assetSequence) {
        AssetDocument.AssetDocumentId id = new AssetDocument.AssetDocumentId();
        id.setPresentationYear(presentationYear);
        id.setTaxType(taxType);
        id.setPresentationCode(presentationCode);
        id.setAssetSequence(assetSequence);
        
        AssetDocument doc = assetDocumentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Asset not found"));
        
        String nature = doc.getAssetNature();
        
        if ("U".equals(nature)) {
            urbanPropertyRepository.findByPresentationYearAndTaxTypeAndPresentationCodeAndAssetSequence(
                    presentationYear, taxType, presentationCode, assetSequence)
                    .ifPresent(urbanPropertyRepository::delete);
        } else if ("R".equals(nature)) {
            ruralPropertyRepository.findByPresentationYearAndTaxTypeAndPresentationCodeAndAssetSequence(
                    presentationYear, taxType, presentationCode, assetSequence)
                    .ifPresent(ruralPropertyRepository::delete);
        } else if ("A".equals(nature)) {
            businessAssetRepository.findByPresentationYearAndTaxTypeAndPresentationCodeAndAssetSequence(
                    presentationYear, taxType, presentationCode, assetSequence)
                    .ifPresent(businessAssetRepository::delete);
        } else if ("T".equals(nature)) {
            otherAssetRepository.findByPresentationYearAndTaxTypeAndPresentationCodeAndAssetSequence(
                    presentationYear, taxType, presentationCode, assetSequence)
                    .ifPresent(otherAssetRepository::delete);
        } else if ("C".equals(nature)) {
            bankAccountRepository.findByPresentationYearAndTaxTypeAndPresentationCodeAndAssetSequence(
                    presentationYear, taxType, presentationCode, assetSequence)
                    .ifPresent(bankAccountRepository::delete);
        } else if ("V".equals(nature)) {
            listedSecuritiesRepository.findByPresentationYearAndTaxTypeAndPresentationCodeAndAssetSequence(
                    presentationYear, taxType, presentationCode, assetSequence)
                    .ifPresent(listedSecuritiesRepository::delete);
        } else if ("N".equals(nature)) {
            unlistedSecuritiesRepository.findByPresentationYearAndTaxTypeAndPresentationCodeAndAssetSequence(
                    presentationYear, taxType, presentationCode, assetSequence)
                    .ifPresent(unlistedSecuritiesRepository::delete);
        } else {
            vehicleRepository.findByPresentationYearAndTaxTypeAndPresentationCodeAndAssetSequence(
                    presentationYear, taxType, presentationCode, assetSequence)
                    .ifPresent(vehicleRepository::delete);
        }
        
        assetDocumentRepository.delete(doc);
    }
}