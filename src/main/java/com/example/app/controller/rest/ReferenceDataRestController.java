package com.example.app.controller.rest;

import com.example.app.entity.ReferenceData;
import com.example.app.entity.Province;
import com.example.app.entity.Municipality;
import com.example.app.service.ReferenceDataService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reference-data")
@Tag(name = "Reference Data", description = "Reference data API")
public class ReferenceDataRestController {

    private final ReferenceDataService referenceDataService;

    @Autowired
    public ReferenceDataRestController(ReferenceDataService referenceDataService) {
        this.referenceDataService = referenceDataService;
    }

    @GetMapping("/nature-types")
    @Operation(summary = "Get all asset nature types")
    public ResponseEntity<List<ReferenceData>> getNatureTypes() {
        return ResponseEntity.ok(referenceDataService.getNatureTypes());
    }

    @GetMapping("/position-types")
    @Operation(summary = "Get all position types")
    public ResponseEntity<List<ReferenceData>> getPositionTypes() {
        return ResponseEntity.ok(referenceDataService.getPositionTypes());
    }

    @GetMapping("/provinces")
    @Operation(summary = "Get all provinces")
    public ResponseEntity<List<Province>> getProvinces() {
        return ResponseEntity.ok(referenceDataService.getAllProvinces());
    }

    @GetMapping("/municipalities")
    @Operation(summary = "Get municipalities by province")
    public ResponseEntity<List<Municipality>> getMunicipalities(@RequestParam String cdprov) {
        return ResponseEntity.ok(referenceDataService.getMunicipalitiesByProvince(cdprov));
    }
}