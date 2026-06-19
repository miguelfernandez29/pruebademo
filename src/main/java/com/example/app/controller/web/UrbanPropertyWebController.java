package com.example.app.controller.web;

import com.example.app.dto.UrbanPropertyDTO;
import com.example.app.service.UrbanPropertyService;
import com.example.app.repository.ProvinceRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.math.BigDecimal;

@Controller
@RequestMapping("/urban-properties")
public class UrbanPropertyWebController {

    private final UrbanPropertyService urbanPropertyService;
    private final ProvinceRepository provinceRepository;

    public UrbanPropertyWebController(UrbanPropertyService urbanPropertyService,
                                       ProvinceRepository provinceRepository) {
        this.urbanPropertyService = urbanPropertyService;
        this.provinceRepository = provinceRepository;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("properties", urbanPropertyService.findAll());
        model.addAttribute("title", "Urban Properties");
        return "urban-properties/list";
    }

    @GetMapping("/add")
    public String showAddForm(@RequestParam String presentationYear,
                              @RequestParam String taxType,
                              @RequestParam String presentationCode,
                              @RequestParam String assetSequence,
                              Model model) {
        UrbanPropertyDTO dto = new UrbanPropertyDTO();
        dto.setPresentationYear(presentationYear);
        dto.setTaxType(taxType);
        dto.setPresentationCode(presentationCode);
        dto.setAssetSequence(assetSequence);
        dto.setCountryCode("ESP");
        dto.setTransmissionPercentage(new BigDecimal("100"));
        dto.setNumberOfUnits(1);
        
        model.addAttribute("property", dto);
        model.addAttribute("provinces", provinceRepository.findAll());
        model.addAttribute("title", "Add Urban Property");
        model.addAttribute("action", "add");
        
        return "urban-properties/form";
    }

    @GetMapping("/edit/{presentationYear}/{taxType}/{presentationCode}/{assetSequence}")
    public String showEditForm(@PathVariable String presentationYear,
                               @PathVariable String taxType,
                               @PathVariable String presentationCode,
                               @PathVariable String assetSequence,
                               Model model) {
        UrbanPropertyDTO dto = urbanPropertyService.findById(presentationYear, taxType, presentationCode, assetSequence)
                .orElseThrow(() -> new IllegalArgumentException("Urban property not found"));
        
        model.addAttribute("property", dto);
        model.addAttribute("provinces", provinceRepository.findAll());
        model.addAttribute("title", "Edit Urban Property");
        model.addAttribute("action", "edit");
        
        return "urban-properties/form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("property") UrbanPropertyDTO dto,
                       BindingResult bindingResult,
                       @RequestParam String action,
                       Model model,
                       RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("provinces", provinceRepository.findAll());
            model.addAttribute("title", "add".equals(action) ? "Add Urban Property" : "Edit Urban Property");
            model.addAttribute("action", action);
            return "urban-properties/form";
        }
        
        try {
            if ("add".equals(action)) {
                urbanPropertyService.create(dto);
                redirectAttributes.addFlashAttribute("successMessage", "Urban property created successfully");
            } else {
                urbanPropertyService.update(dto);
                redirectAttributes.addFlashAttribute("successMessage", "Urban property updated successfully");
            }
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("provinces", provinceRepository.findAll());
            model.addAttribute("title", "add".equals(action) ? "Add Urban Property" : "Edit Urban Property");
            model.addAttribute("action", action);
            return "urban-properties/form";
        }
        
        return "redirect:/assets?presentationYear=" + dto.getPresentationYear() + 
               "&taxType=" + dto.getTaxType() + "&presentationCode=" + dto.getPresentationCode();
    }

    @GetMapping("/view/{presentationYear}/{taxType}/{presentationCode}/{assetSequence}")
    public String view(@PathVariable String presentationYear,
                       @PathVariable String taxType,
                       @PathVariable String presentationCode,
                       @PathVariable String assetSequence,
                       Model model) {
        UrbanPropertyDTO dto = urbanPropertyService.findById(presentationYear, taxType, presentationCode, assetSequence)
                .orElseThrow(() -> new IllegalArgumentException("Urban property not found"));
        
        model.addAttribute("property", dto);
        model.addAttribute("title", "Urban Property Details");
        
        return "urban-properties/view";
    }

    @PostMapping("/delete/{presentationYear}/{taxType}/{presentationCode}/{assetSequence}")
    public String delete(@PathVariable String presentationYear,
                         @PathVariable String taxType,
                         @PathVariable String presentationCode,
                         @PathVariable String assetSequence,
                         RedirectAttributes redirectAttributes) {
        try {
            urbanPropertyService.delete(presentationYear, taxType, presentationCode, assetSequence);
            redirectAttributes.addFlashAttribute("successMessage", "Urban property deleted successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error deleting property: " + e.getMessage());
        }
        return "redirect:/assets?presentationYear=" + presentationYear + 
               "&taxType=" + taxType + "&presentationCode=" + presentationCode;
    }
}