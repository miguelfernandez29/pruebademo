package com.example.app.controller.rest;

import com.example.app.dto.AssetSummaryDTO;
import com.example.app.service.AssetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/assets")
@Tag(name = "Assets", description = "Asset management operations")
public class AssetRestController {

    private final AssetService assetService;

    public AssetRestController(AssetService assetService) {
        this.assetService = assetService;
    }

    @GetMapping
    @Operation(summary = "Get asset list for a declaration")
    public ResponseEntity<List<AssetSummaryDTO>> getAssetList(
            @RequestParam String presentationYear,
            @RequestParam String taxType,
            @RequestParam String presentationCode) {
        List<AssetSummaryDTO> assets = assetService.getAssetList(presentationYear, taxType, presentationCode);
        return ResponseEntity.ok(assets);
    }

    @GetMapping("/next-sequence")
    @Operation(summary = "Get next available asset sequence number")
    public ResponseEntity<Map<String, String>> getNextSequence(
            @RequestParam String presentationYear,
            @RequestParam String taxType,
            @RequestParam String presentationCode) {
        String nextSequence = assetService.generateNextAssetSequence(presentationYear, taxType, presentationCode);
        Map<String, String> response = new HashMap<String, String>();
        response.put("nextSequence", nextSequence);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/totals")
    @Operation(summary = "Get total declared and verified values")
    public ResponseEntity<Map<String, BigDecimal>> getTotals(
            @RequestParam String presentationYear,
            @RequestParam String taxType,
            @RequestParam String presentationCode) {
        BigDecimal totalDeclared = assetService.calculateTotalDeclaredValue(presentationYear, taxType, presentationCode);
        BigDecimal totalVerified = assetService.calculateTotalVerifiedValue(presentationYear, taxType, presentationCode);
        
        Map<String, BigDecimal> totals = new HashMap<String, BigDecimal>();
        totals.put("totalDeclaredValue", totalDeclared);
        totals.put("totalVerifiedValue", totalVerified);
        return ResponseEntity.ok(totals);
    }

    @DeleteMapping("/{presentationYear}/{taxType}/{presentationCode}/{assetSequence}")
    @Operation(summary = "Delete an asset")
    public ResponseEntity<Void> deleteAsset(
            @PathVariable String presentationYear,
            @PathVariable String taxType,
            @PathVariable String presentationCode,
            @PathVariable String assetSequence) {
        assetService.deleteAsset(presentationYear, taxType, presentationCode, assetSequence);
        return ResponseEntity.noContent().build();
    }
}