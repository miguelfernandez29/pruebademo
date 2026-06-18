package com.example.app.controller.rest;

import com.example.app.dto.UrbanPropertyDTO;
import com.example.app.service.UrbanPropertyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/urban-properties")
@Tag(name = "Urban Properties", description = "Urban Property Management API")
public class UrbanPropertyRestController {

    private final UrbanPropertyService urbanPropertyService;

    @Autowired
    public UrbanPropertyRestController(UrbanPropertyService urbanPropertyService) {
        this.urbanPropertyService = urbanPropertyService;
    }

    @GetMapping
    @Operation(summary = "Get urban properties by document")
    public ResponseEntity<List<UrbanPropertyDTO>> getPropertiesByDocument(
            @RequestParam String presentationYear,
            @RequestParam String taxType,
            @RequestParam String presentationCode) {
        List<UrbanPropertyDTO> properties = urbanPropertyService.findByDocument(
                presentationYear, taxType, presentationCode);
        return ResponseEntity.ok(properties);
    }

    @GetMapping("/{assetSequence}")
    @Operation(summary = "Get urban property by ID")
    public ResponseEntity<UrbanPropertyDTO> getPropertyById(
            @RequestParam String presentationYear,
            @RequestParam String taxType,
            @RequestParam String presentationCode,
            @PathVariable String assetSequence) {
        UrbanPropertyDTO property = urbanPropertyService.findById(
                presentationYear, taxType, presentationCode, assetSequence);
        return ResponseEntity.ok(property);
    }

    @PostMapping
    @Operation(summary = "Create urban property")
    public ResponseEntity<UrbanPropertyDTO> createProperty(@Valid @RequestBody UrbanPropertyDTO dto) {
        UrbanPropertyDTO created = urbanPropertyService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{assetSequence}")
    @Operation(summary = "Update urban property")
    public ResponseEntity<UrbanPropertyDTO> updateProperty(
            @PathVariable String assetSequence,
            @Valid @RequestBody UrbanPropertyDTO dto) {
        dto.setAssetSequence(assetSequence);
        UrbanPropertyDTO updated = urbanPropertyService.update(dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{assetSequence}")
    @Operation(summary = "Delete urban property")
    public ResponseEntity<Void> deleteProperty(
            @RequestParam String presentationYear,
            @RequestParam String taxType,
            @RequestParam String presentationCode,
            @PathVariable String assetSequence) {
        urbanPropertyService.delete(presentationYear, taxType, presentationCode, assetSequence);
        return ResponseEntity.noContent().build();
    }
}