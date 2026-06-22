package com.example.app.controller.rest;

import com.example.app.dto.UrbanPropertyDTO;
import com.example.app.entity.UrbanProperty;
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
@Tag(name = "Urban Properties", description = "Urban property management API")
public class UrbanPropertyRestController {

    private final UrbanPropertyService urbanPropertyService;

    @Autowired
    public UrbanPropertyRestController(UrbanPropertyService urbanPropertyService) {
        this.urbanPropertyService = urbanPropertyService;
    }

    @GetMapping
    @Operation(summary = "Get all urban properties for a declaration")
    public ResponseEntity<List<UrbanProperty>> getAll(
            @RequestParam String aapresenta,
            @RequestParam String vftipoimpu,
            @RequestParam String cdpresenta) {
        List<UrbanProperty> properties = urbanPropertyService.findAllByDeclaration(aapresenta, vftipoimpu, cdpresenta);
        return ResponseEntity.ok(properties);
    }

    @GetMapping("/{cdsecubien}")
    @Operation(summary = "Get urban property by ID")
    public ResponseEntity<UrbanProperty> getById(
            @RequestParam String aapresenta,
            @RequestParam String vftipoimpu,
            @RequestParam String cdpresenta,
            @PathVariable String cdsecubien) {
        return urbanPropertyService.findById(aapresenta, vftipoimpu, cdpresenta, cdsecubien)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Create a new urban property")
    public ResponseEntity<UrbanProperty> create(@Valid @RequestBody UrbanPropertyDTO dto) {
        UrbanProperty created = urbanPropertyService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{cdsecubien}")
    @Operation(summary = "Update an existing urban property")
    public ResponseEntity<UrbanProperty> update(
            @PathVariable String cdsecubien,
            @Valid @RequestBody UrbanPropertyDTO dto) {
        dto.setCdsecubien(cdsecubien);
        UrbanProperty updated = urbanPropertyService.update(dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{cdsecubien}")
    @Operation(summary = "Delete an urban property")
    public ResponseEntity<Void> delete(
            @RequestParam String aapresenta,
            @RequestParam String vftipoimpu,
            @RequestParam String cdpresenta,
            @PathVariable String cdsecubien) {
        urbanPropertyService.delete(aapresenta, vftipoimpu, cdpresenta, cdsecubien);
        return ResponseEntity.noContent().build();
    }
}