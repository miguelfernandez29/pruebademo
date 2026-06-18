package com.example.app.controller.web;

import com.example.app.dto.AssetDocumentDTO;
import com.example.app.service.AssetDocumentService;
import com.example.app.exception.ValidationException;
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

    private final AssetDocumentService assetDocumentService;

    @Autowired
    public AssetWebController(AssetDocumentService assetDocumentService) {
        this.assetDocumentService = assetDocumentService;
    }

    @GetMapping
    public String listAssets(
            @RequestParam(required = false) String presentationYear,
            @RequestParam(required = false) String taxType,
            @RequestParam(required = false) String presentationCode,
            Model model) {
        
        if (presentationYear != null && taxType != null && presentationCode != null) {
            List<AssetDocumentDTO> assets = assetDocumentService.findByDocument(
                    presentationYear, taxType, presentationCode);
            model.addAttribute("assets", assets);
            model.addAttribute("presentationYear", presentationYear);
            model.addAttribute("taxType", taxType);
            model.addAttribute("presentationCode", presentationCode);
        }
        
        model.addAttribute("title", "Asset List");
        return "assets/list";
    }

    @GetMapping("/new")
    public String newAssetForm(
            @RequestParam String presentationYear,
            @RequestParam String taxType,
            @RequestParam String presentationCode,
            Model model) {
        
        AssetDocumentDTO asset = new AssetDocumentDTO();
        asset.setPresentationYear(presentationYear);
        asset.setTaxType(taxType);
        asset.setPresentationCode(presentationCode);
        asset.setAssetSequence(assetDocumentService.getNextAssetSequence(
                presentationYear, taxType, presentationCode));
        
        model.addAttribute("asset", asset);
        model.addAttribute("title", "New Asset");
        model.addAttribute("action", "create");
        return "assets/form";
    }

    @PostMapping("/create")
    public String createAsset(
            @Valid @ModelAttribute("asset") AssetDocumentDTO asset,
            BindingResult result,
            RedirectAttributes redirectAttributes,
            Model model) {
        
        if (result.hasErrors()) {
            model.addAttribute("title", "New Asset");
            model.addAttribute("action", "create");
            return "assets/form";
        }
        
        try {
            assetDocumentService.create(asset);
            redirectAttributes.addFlashAttribute("successMessage", "Asset created successfully");
            return "redirect:/assets?presentationYear=" + asset.getPresentationYear() +
                    "&taxType=" + asset.getTaxType() +
                    "&presentationCode=" + asset.getPresentationCode();
        } catch (ValidationException e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("title", "New Asset");
            model.addAttribute("action", "create");
            return "assets/form";
        }
    }

    @GetMapping("/edit/{assetSequence}")
    public String editAssetForm(
            @RequestParam String presentationYear,
            @RequestParam String taxType,
            @RequestParam String presentationCode,
            @PathVariable String assetSequence,
            Model model) {
        
        AssetDocumentDTO asset = assetDocumentService.findById(
                presentationYear, taxType, presentationCode, assetSequence);
        
        model.addAttribute("asset", asset);
        model.addAttribute("title", "Edit Asset");
        model.addAttribute("action", "update");
        return "assets/form";
    }

    @PostMapping("/update")
    public String updateAsset(
            @Valid @ModelAttribute("asset") AssetDocumentDTO asset,
            BindingResult result,
            RedirectAttributes redirectAttributes,
            Model model) {
        
        if (result.hasErrors()) {
            model.addAttribute("title", "Edit Asset");
            model.addAttribute("action", "update");
            return "assets/form";
        }
        
        try {
            assetDocumentService.update(asset);
            redirectAttributes.addFlashAttribute("successMessage", "Asset updated successfully");
            return "redirect:/assets?presentationYear=" + asset.getPresentationYear() +
                    "&taxType=" + asset.getTaxType() +
                    "&presentationCode=" + asset.getPresentationCode();
        } catch (ValidationException e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("title", "Edit Asset");
            model.addAttribute("action", "update");
            return "assets/form";
        }
    }

    @PostMapping("/delete/{assetSequence}")
    public String deleteAsset(
            @RequestParam String presentationYear,
            @RequestParam String taxType,
            @RequestParam String presentationCode,
            @PathVariable String assetSequence,
            RedirectAttributes redirectAttributes) {
        
        try {
            assetDocumentService.delete(presentationYear, taxType, presentationCode, assetSequence);
            redirectAttributes.addFlashAttribute("successMessage", "Asset deleted successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error deleting asset: " + e.getMessage());
        }
        
        return "redirect:/assets?presentationYear=" + presentationYear +
                "&taxType=" + taxType +
                "&presentationCode=" + presentationCode;
    }

    @GetMapping("/detail/{assetSequence}")
    public String assetDetail(
            @RequestParam String presentationYear,
            @RequestParam String taxType,
            @RequestParam String presentationCode,
            @PathVariable String assetSequence,
            Model model) {
        
        AssetDocumentDTO asset = assetDocumentService.findById(
                presentationYear, taxType, presentationCode, assetSequence);
        
        model.addAttribute("asset", asset);
        model.addAttribute("title", "Asset Detail");
        
        String nature = asset.getAssetNature();
        if ("U".equals(nature)) {
            return "redirect:/urban-properties/detail/" + assetSequence +
                    "?presentationYear=" + presentationYear +
                    "&taxType=" + taxType +
                    "&presentationCode=" + presentationCode;
        }
        
        return "assets/detail";
    }
}