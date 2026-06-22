package com.example.app.controller.web;

import com.example.app.dto.AssetDocumentDTO;
import com.example.app.entity.AssetDocument;
import com.example.app.service.AssetService;
import com.example.app.service.ReferenceDataService;
import com.example.app.exception.BusinessValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/assets")
public class AssetWebController {

    private final AssetService assetService;
    private final ReferenceDataService referenceDataService;

    @Autowired
    public AssetWebController(AssetService assetService, ReferenceDataService referenceDataService) {
        this.assetService = assetService;
        this.referenceDataService = referenceDataService;
    }

    @GetMapping
    public String list(
            @RequestParam(defaultValue = "2024") String aapresenta,
            @RequestParam(defaultValue = "01") String vftipoimpu,
            @RequestParam(defaultValue = "00000000000001") String cdpresenta,
            Model model) {
        List<AssetDocument> assets = assetService.findAllByDeclaration(aapresenta, vftipoimpu, cdpresenta);
        model.addAttribute("assets", assets);
        model.addAttribute("aapresenta", aapresenta);
        model.addAttribute("vftipoimpu", vftipoimpu);
        model.addAttribute("cdpresenta", cdpresenta);
        model.addAttribute("natureTypes", referenceDataService.getNatureTypes());
        return "assets/list";
    }

    @GetMapping("/new")
    public String newAsset(
            @RequestParam String aapresenta,
            @RequestParam String vftipoimpu,
            @RequestParam String cdpresenta,
            Model model) {
        AssetDocumentDTO dto = new AssetDocumentDTO();
        dto.setAapresenta(aapresenta);
        dto.setVftipoimpu(vftipoimpu);
        dto.setCdpresenta(cdpresenta);
        dto.setCdsecubien(assetService.generateNextSequence(aapresenta, vftipoimpu, cdpresenta));

        model.addAttribute("asset", dto);
        model.addAttribute("natureTypes", referenceDataService.getNatureTypes());
        model.addAttribute("positionTypes", referenceDataService.getPositionTypes());
        model.addAttribute("isNew", true);
        return "assets/form";
    }

    @PostMapping("/save")
    public String save(
            @Valid @ModelAttribute("asset") AssetDocumentDTO dto,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("natureTypes", referenceDataService.getNatureTypes());
            model.addAttribute("positionTypes", referenceDataService.getPositionTypes());
            model.addAttribute("isNew", dto.getCdsecubien() == null);
            return "assets/form";
        }

        try {
            if (dto.getCdsecubien() == null || dto.getCdsecubien().trim().isEmpty()) {
                assetService.create(dto);
                redirectAttributes.addFlashAttribute("successMessage", "Bien creado correctamente.");
            } else {
                assetService.update(dto);
                redirectAttributes.addFlashAttribute("successMessage", "Bien actualizado correctamente.");
            }
        } catch (BusinessValidationException e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("natureTypes", referenceDataService.getNatureTypes());
            model.addAttribute("positionTypes", referenceDataService.getPositionTypes());
            model.addAttribute("isNew", dto.getCdsecubien() == null);
            return "assets/form";
        }

        return "redirect:/assets?aapresenta=" + dto.getAapresenta() +
                "&vftipoimpu=" + dto.getVftipoimpu() +
                "&cdpresenta=" + dto.getCdpresenta();
    }

    @GetMapping("/edit/{cdsecubien}")
    public String edit(
            @RequestParam String aapresenta,
            @RequestParam String vftipoimpu,
            @RequestParam String cdpresenta,
            @PathVariable String cdsecubien,
            Model model) {
        AssetDocument asset = assetService.findById(aapresenta, vftipoimpu, cdpresenta, cdsecubien)
                .orElseThrow(() -> new RuntimeException("Bien no encontrado"));

        AssetDocumentDTO dto = new AssetDocumentDTO();
        dto.setAapresenta(asset.getAapresenta());
        dto.setVftipoimpu(asset.getVftipoimpu());
        dto.setCdpresenta(asset.getCdpresenta());
        dto.setCdsecubien(asset.getCdsecubien());
        dto.setCdnatbien2(asset.getCdnatbien2());
        dto.setCdsecuacem(asset.getCdsecuacem());
        dto.setFccomproba(asset.getFccomproba());
        dto.setIdcomproba(asset.getIdcomproba());
        dto.setPtdeclarad(asset.getPtdeclarad());
        dto.setPtcomproba(asset.getPtcomproba());

        model.addAttribute("asset", dto);
        model.addAttribute("natureTypes", referenceDataService.getNatureTypes());
        model.addAttribute("positionTypes", referenceDataService.getPositionTypes());
        model.addAttribute("isNew", false);
        return "assets/form";
    }

    @PostMapping("/delete/{cdsecubien}")
    public String delete(
            @RequestParam String aapresenta,
            @RequestParam String vftipoimpu,
            @RequestParam String cdpresenta,
            @PathVariable String cdsecubien,
            RedirectAttributes redirectAttributes) {
        try {
            assetService.delete(aapresenta, vftipoimpu, cdpresenta, cdsecubien);
            redirectAttributes.addFlashAttribute("successMessage", "Bien eliminado correctamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error al eliminar: " + e.getMessage());
        }
        return "redirect:/assets?aapresenta=" + aapresenta +
                "&vftipoimpu=" + vftipoimpu +
                "&cdpresenta=" + cdpresenta;
    }
}