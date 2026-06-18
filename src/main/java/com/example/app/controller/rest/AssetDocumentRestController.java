package com.example.app.controller.rest;

import com.example.app.dto.AssetDocumentDTO;
import com.example.app.service.AssetDocumentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/assets")
@Tag(name = "Asset Documents", description = "Asset Document Management API")
public class AssetDocumentRestController {

    private final AssetDocumentService assetDocumentService;

    @Autowired
    public AssetDocumentRestController(AssetDocumentService assetDocumentService) {
        this.assetDocumentService = assetDocumentService;
    }

    @GetMapping
    @Operation(summary = "Get assets by document")
    public ResponseEntity<List<AssetDocumentDTO>> getAssetsByDocument(
            @RequestParam String presentationYear,
            @RequestParam String taxType,
            @RequestParam String presentationCode) {
        List<AssetDocumentDTO> assets = assetDocumentService.findByDocument(
                presentationYear, taxType, presentationCode);
        return ResponseEntity.ok(assets);
    }

    @GetMapping("/{assetSequence}")
    @Operation(summary = "Get asset by ID")
    public ResponseEntity<AssetDocumentDTO> getAssetById(
            @RequestParam String presentationYear,
            @RequestParam String taxType,
            @RequestParam String presentationCode,
            @PathVariable String assetSequence) {
        AssetDocumentDTO asset = assetDocumentService.findById(
                presentationYear, taxType, presentationCode, assetSequence);
        return ResponseEntity.ok(asset);
    }

    @PostMapping
    @Operation(summary = "Create new asset")
    public ResponseEntity<AssetDocumentDTO> createAsset(@Valid @RequestBody AssetDocumentDTO dto) {
        AssetDocumentDTO created = assetDocumentService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{assetSequence}")
    @Operation(summary = "Update asset")
    public ResponseEntity<AssetDocumentDTO> updateAsset(
            @PathVariable String assetSequence,
            @Valid @RequestBody AssetDocumentDTO dto) {
        dto.setAssetSequence(assetSequence);
        AssetDocumentDTO updated = assetDocumentService.update(dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{assetSequence}")
    @Operation(summary = "Delete asset")
    public ResponseEntity<Void> deleteAsset(
            @RequestParam String presentationYear,
            @RequestParam String taxType,
            @RequestParam String presentationCode,
            @PathVariable String assetSequence) {
        assetDocumentService.delete(presentationYear, taxType, presentationCode, assetSequence);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/next-sequence")
    @Operation(summary = "Get next asset sequence")
    public ResponseEntity<String> getNextSequence(
            @RequestParam String presentationYear,
            @RequestParam String taxType,
            @RequestParam String presentationCode) {
        String nextSequence = assetDocumentService.getNextAssetSequence(
                presentationYear, taxType, presentationCode);
        return ResponseEntity.ok(nextSequence);
    }
}