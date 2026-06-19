package com.example.app.controller.web;

import com.example.app.dto.AssetSummaryDTO;
import com.example.app.service.AssetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/assets")
public class AssetWebController {

    private final AssetService assetService;

    public AssetWebController(AssetService assetService) {
        this.assetService = assetService;
    }

    @GetMapping
    public String listAssets(@RequestParam(defaultValue = "2024") String presentationYear,
                             @RequestParam(defaultValue = "01") String taxType,
                             @RequestParam(defaultValue = "0000000001") String presentationCode,
                             Model model) {
        List<AssetSummaryDTO> assets = assetService.getAssetList(presentationYear, taxType, presentationCode);
        BigDecimal totalDeclared = assetService.calculateTotalDeclaredValue(presentationYear, taxType, presentationCode);
        BigDecimal totalVerified = assetService.calculateTotalVerifiedValue(presentationYear, taxType, presentationCode);
        
        model.addAttribute("assets", assets);
        model.addAttribute("presentationYear", presentationYear);
        model.addAttribute("taxType", taxType);
        model.addAttribute("presentationCode", presentationCode);
        model.addAttribute("totalDeclaredValue", totalDeclared);
        model.addAttribute("totalVerifiedValue", totalVerified);
        model.addAttribute("title", "Asset List");
        
        return "assets/list";
    }

    @GetMapping("/add")
    public String showAddForm(@RequestParam String presentationYear,
                              @RequestParam String taxType,
                              @RequestParam String presentationCode,
                              Model model) {
        String nextSequence = assetService.generateNextAssetSequence(presentationYear, taxType, presentationCode);
        
        model.addAttribute("presentationYear", presentationYear);
        model.addAttribute("taxType", taxType);
        model.addAttribute("presentationCode", presentationCode);
        model.addAttribute("assetSequence", nextSequence);
        model.addAttribute("title", "Add New Asset");
        
        return "assets/add";
    }

    @PostMapping("/delete/{presentationYear}/{taxType}/{presentationCode}/{assetSequence}")
    public String deleteAsset(@PathVariable String presentationYear,
                              @PathVariable String taxType,
                              @PathVariable String presentationCode,
                              @PathVariable String assetSequence,
                              RedirectAttributes redirectAttributes) {
        try {
            assetService.deleteAsset(presentationYear, taxType, presentationCode, assetSequence);
            redirectAttributes.addFlashAttribute("successMessage", "Asset deleted successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error deleting asset: " + e.getMessage());
        }
        return "redirect:/assets?presentationYear=" + presentationYear + 
               "&taxType=" + taxType + "&presentationCode=" + presentationCode;
    }
}