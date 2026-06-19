package com.example.app.controller.rest;

import com.example.app.dto.LegacyBeneficiaryDTO;
import com.example.app.service.LegacyBeneficiaryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/legacy-beneficiaries")
@Tag(name = "Legacy Beneficiaries", description = "Legacy beneficiary management operations")
public class LegacyBeneficiaryRestController {

    private final LegacyBeneficiaryService legacyBeneficiaryService;

    public LegacyBeneficiaryRestController(LegacyBeneficiaryService legacyBeneficiaryService) {
        this.legacyBeneficiaryService = legacyBeneficiaryService;
    }

    @GetMapping
    @Operation(summary = "Get legacy beneficiaries for an asset")
    public ResponseEntity<List<LegacyBeneficiaryDTO>> findByAsset(
            @RequestParam String presentationYear,
            @RequestParam String taxType,
            @RequestParam String presentationCode,
            @RequestParam String assetSequence) {
        List<LegacyBeneficiaryDTO> beneficiaries = legacyBeneficiaryService.findByAsset(
                presentationYear, taxType, presentationCode, assetSequence);
        return ResponseEntity.ok(beneficiaries);
    }

    @PostMapping
    @Operation(summary = "Create a new legacy beneficiary")
    public ResponseEntity<LegacyBeneficiaryDTO> create(@Valid @RequestBody LegacyBeneficiaryDTO dto) {
        LegacyBeneficiaryDTO created = legacyBeneficiaryService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping
    @Operation(summary = "Update a legacy beneficiary")
    public ResponseEntity<LegacyBeneficiaryDTO> update(@Valid @RequestBody LegacyBeneficiaryDTO dto) {
        LegacyBeneficiaryDTO updated = legacyBeneficiaryService.update(dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping
    @Operation(summary = "Delete a legacy beneficiary")
    public ResponseEntity<Void> delete(
            @RequestParam String presentationYear,
            @RequestParam String taxType,
            @RequestParam String presentationCode,
            @RequestParam String assetSequence,
            @RequestParam String causantNif,
            @RequestParam String causantSubcode,
            @RequestParam String beneficiaryNif,
            @RequestParam String beneficiarySubcode) {
        legacyBeneficiaryService.delete(presentationYear, taxType, presentationCode, assetSequence,
                causantNif, causantSubcode, beneficiaryNif, beneficiarySubcode);
        return ResponseEntity.noContent().build();
    }
}