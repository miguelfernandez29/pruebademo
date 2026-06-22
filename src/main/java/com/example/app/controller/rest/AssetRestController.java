package com.example.app.controller.rest;

import com.example.app.dto.AssetDocumentDTO;
import com.example.app.entity.AssetDocument;
import com.example.app.service.AssetService;
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
@Tag(name = "Assets", description = "Asset management API")
public class AssetRestController {

    private final AssetService assetService;

    @Autowired
    public AssetRestController(AssetService assetService) {
        this.assetService = assetService;
    }

    @GetMapping
    @Operation(summary = "Get all assets for a declaration")
    public ResponseEntity<List<AssetDocument>> getAll(
            @RequestParam String aapresenta,
            @RequestParam String vftipoimpu,
            @RequestParam String cdpresenta) {
        List<AssetDocument> assets = assetService.findAllByDeclaration(aapresenta, vftipoimpu, cdpresenta);
        return ResponseEntity.ok(assets);
    }

    @GetMapping("/{cdsecubien}")
    @Operation(summary = "Get asset by ID")
    public ResponseEntity<AssetDocument> getById(
            @RequestParam String aapresenta,
            @RequestParam String vftipoimpu,
            @RequestParam String cdpresenta,
            @PathVariable String cdsecubien) {
        return assetService.findById(aapresenta, vftipoimpu, cdpresenta, cdsecubien)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Create a new asset")
    public ResponseEntity<AssetDocument> create(@Valid @RequestBody AssetDocumentDTO dto) {
        AssetDocument created = assetService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{cdsecubien}")
    @Operation(summary = "Update an existing asset")
    public ResponseEntity<AssetDocument> update(
            @PathVariable String cdsecubien,
            @Valid @RequestBody AssetDocumentDTO dto) {
        dto.setCdsecubien(cdsecubien);
        AssetDocument updated = assetService.update(dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{cdsecubien}")
    @Operation(summary = "Delete an asset")
    public ResponseEntity<Void> delete(
            @RequestParam String aapresenta,
            @RequestParam String vftipoimpu,
            @RequestParam String cdpresenta,
            @PathVariable String cdsecubien) {
        assetService.delete(aapresenta, vftipoimpu, cdpresenta, cdsecubien);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/next-sequence")
    @Operation(summary = "Get next asset sequence number")
    public ResponseEntity<String> getNextSequence(
            @RequestParam String aapresenta,
            @RequestParam String vftipoimpu,
            @RequestParam String cdpresenta) {
        String nextSeq = assetService.generateNextSequence(aapresenta, vftipoimpu, cdpresenta);
        return ResponseEntity.ok(nextSeq);
    }
}