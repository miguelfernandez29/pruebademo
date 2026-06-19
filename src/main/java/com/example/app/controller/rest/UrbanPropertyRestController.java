package com.example.app.controller.rest;

import com.example.app.dto.UrbanPropertyDTO;
import com.example.app.service.UrbanPropertyService;
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
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/urban-properties")
@Tag(name = "Urban Properties", description = "Urban property management operations")
public class UrbanPropertyRestController {

    private final UrbanPropertyService urbanPropertyService;

    public UrbanPropertyRestController(UrbanPropertyService urbanPropertyService) {
        this.urbanPropertyService = urbanPropertyService;
    }

    @GetMapping
    @Operation(summary = "Get all urban properties")
    public ResponseEntity<List<UrbanPropertyDTO>> findAll() {
        return ResponseEntity.ok(urbanPropertyService.findAll());
    }

    @GetMapping("/{presentationYear}/{taxType}/{presentationCode}/{assetSequence}")
    @Operation(summary = "Get urban property by ID")
    public ResponseEntity<UrbanPropertyDTO> findById(
            @PathVariable String presentationYear,
            @PathVariable String taxType,
            @PathVariable String presentationCode,
            @PathVariable String assetSequence) {
        return urbanPropertyService.findById(presentationYear, taxType, presentationCode, assetSequence)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Create a new urban property")
    public ResponseEntity<UrbanPropertyDTO> create(@Valid @RequestBody UrbanPropertyDTO dto) {
        UrbanPropertyDTO created = urbanPropertyService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{presentationYear}/{taxType}/{presentationCode}/{assetSequence}")
    @Operation(summary = "Update an urban property")
    public ResponseEntity<UrbanPropertyDTO> update(
            @PathVariable String presentationYear,
            @PathVariable String taxType,
            @PathVariable String presentationCode,
            @PathVariable String assetSequence,
            @Valid @RequestBody UrbanPropertyDTO dto) {
        dto.setPresentationYear(presentationYear);
        dto.setTaxType(taxType);
        dto.setPresentationCode(presentationCode);
        dto.setAssetSequence(assetSequence);
        UrbanPropertyDTO updated = urbanPropertyService.update(dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{presentationYear}/{taxType}/{presentationCode}/{assetSequence}")
    @Operation(summary = "Delete an urban property")
    public ResponseEntity<Void> delete(
            @PathVariable String presentationYear,
            @PathVariable String taxType,
            @PathVariable String presentationCode,
            @PathVariable String assetSequence) {
        urbanPropertyService.delete(presentationYear, taxType, presentationCode, assetSequence);
        return ResponseEntity.noContent().build();
    }
}