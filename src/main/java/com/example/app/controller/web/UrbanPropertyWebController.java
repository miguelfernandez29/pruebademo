package com.example.app.controller.web;

import com.example.app.dto.UrbanPropertyDTO;
import com.example.app.service.UrbanPropertyService;
import com.example.app.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/urban-properties")
public class UrbanPropertyWebController {

    private final UrbanPropertyService urbanPropertyService;

    @Autowired
    public UrbanPropertyWebController(UrbanPropertyService urbanPropertyService) {
        this.urbanPropertyService = urbanPropertyService;
    }

    @GetMapping("/detail/{assetSequence}")
    public String propertyDetail(
            @RequestParam String presentationYear,
            @RequestParam String taxType,
            @RequestParam String presentationCode,
            @PathVariable String assetSequence,
            Model model) {
        
        UrbanPropertyDTO property = urbanPropertyService.findById(
                presentationYear, taxType, presentationCode, assetSequence);
        
        model.addAttribute("property", property);
        model.addAttribute("title", "Urban Property Detail");
        return "urban-properties/detail";
    }

    @GetMapping("/edit/{assetSequence}")
    public String editPropertyForm(
            @RequestParam String presentationYear,
            @RequestParam String taxType,
            @RequestParam String presentationCode,
            @PathVariable String assetSequence,
            Model model) {
        
        UrbanPropertyDTO property = urbanPropertyService.findById(
                presentationYear, taxType, presentationCode, assetSequence);
        
        model.addAttribute("property", property);
        model.addAttribute("title", "Edit Urban Property");
        return "urban-properties/form";
    }

    @PostMapping("/update")
    public String updateProperty(
            @Valid @ModelAttribute("property") UrbanPropertyDTO property,
            BindingResult result,
            RedirectAttributes redirectAttributes,
            Model model) {
        
        if (result.hasErrors()) {
            model.addAttribute("title", "Edit Urban Property");
            return "urban-properties/form";
        }
        
        try {
            urbanPropertyService.update(property);
            redirectAttributes.addFlashAttribute("successMessage", "Property updated successfully");
            return "redirect:/assets?presentationYear=" + property.getPresentationYear() +
                    "&taxType=" + property.getTaxType() +
                    "&presentationCode=" + property.getPresentationCode();
        } catch (ValidationException e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("title", "Edit Urban Property");
            return "urban-properties/form";
        }
    }
}