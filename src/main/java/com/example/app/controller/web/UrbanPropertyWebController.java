package com.example.app.controller.web;

import com.example.app.dto.UrbanPropertyDTO;
import com.example.app.entity.UrbanProperty;
import com.example.app.service.UrbanPropertyService;
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
@RequestMapping("/urban-properties")
public class UrbanPropertyWebController {

    private final UrbanPropertyService urbanPropertyService;
    private final ReferenceDataService referenceDataService;

    @Autowired
    public UrbanPropertyWebController(UrbanPropertyService urbanPropertyService, ReferenceDataService referenceDataService) {
        this.urbanPropertyService = urbanPropertyService;
        this.referenceDataService = referenceDataService;
    }

    @GetMapping
    public String list(
            @RequestParam(defaultValue = "2024") String aapresenta,
            @RequestParam(defaultValue = "01") String vftipoimpu,
            @RequestParam(defaultValue = "00000000000001") String cdpresenta,
            Model model) {
        List<UrbanProperty> properties = urbanPropertyService.findAllByDeclaration(aapresenta, vftipoimpu, cdpresenta);
        model.addAttribute("properties", properties);
        model.addAttribute("aapresenta", aapresenta);
        model.addAttribute("vftipoimpu", vftipoimpu);
        model.addAttribute("cdpresenta", cdpresenta);
        return "urban-properties/list";
    }

    @GetMapping("/new")
    public String newProperty(
            @RequestParam String aapresenta,
            @RequestParam String vftipoimpu,
            @RequestParam String cdpresenta,
            @RequestParam String cdsecubien,
            Model model) {
        UrbanPropertyDTO dto = new UrbanPropertyDTO();
        dto.setAapresenta(aapresenta);
        dto.setVftipoimpu(vftipoimpu);
        dto.setCdpresenta(cdpresenta);
        dto.setCdsecubien(cdsecubien);
        dto.setCdpais("ESP");

        populateFormModel(model, dto, true);
        return "urban-properties/form";
    }

    @GetMapping("/edit/{cdsecubien}")
    public String edit(
            @RequestParam String aapresenta,
            @RequestParam String vftipoimpu,
            @RequestParam String cdpresenta,
            @PathVariable String cdsecubien,
            Model model) {
        UrbanProperty property = urbanPropertyService.findById(aapresenta, vftipoimpu, cdpresenta, cdsecubien)
                .orElseThrow(() -> new RuntimeException("Bien urbano no encontrado"));

        UrbanPropertyDTO dto = mapEntityToDto(property);
        populateFormModel(model, dto, false);
        return "urban-properties/form";
    }

    @PostMapping("/save")
    public String save(
            @Valid @ModelAttribute("property") UrbanPropertyDTO dto,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            populateFormModel(model, dto, false);
            return "urban-properties/form";
        }

        try {
            boolean exists = urbanPropertyService.findById(dto.getAapresenta(), dto.getVftipoimpu(), dto.getCdpresenta(), dto.getCdsecubien()).isPresent();
            if (exists) {
                urbanPropertyService.update(dto);
                redirectAttributes.addFlashAttribute("successMessage", "Bien urbano actualizado correctamente.");
            } else {
                urbanPropertyService.create(dto);
                redirectAttributes.addFlashAttribute("successMessage", "Bien urbano creado correctamente.");
            }
        } catch (BusinessValidationException e) {
            model.addAttribute("errorMessage", e.getMessage());
            populateFormModel(model, dto, false);
            return "urban-properties/form";
        }

        return "redirect:/assets?aapresenta=" + dto.getAapresenta() +
                "&vftipoimpu=" + dto.getVftipoimpu() +
                "&cdpresenta=" + dto.getCdpresenta();
    }

    @PostMapping("/delete/{cdsecubien}")
    public String delete(
            @RequestParam String aapresenta,
            @RequestParam String vftipoimpu,
            @RequestParam String cdpresenta,
            @PathVariable String cdsecubien,
            RedirectAttributes redirectAttributes) {
        try {
            urbanPropertyService.delete(aapresenta, vftipoimpu, cdpresenta, cdsecubien);
            redirectAttributes.addFlashAttribute("successMessage", "Bien urbano eliminado correctamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error al eliminar: " + e.getMessage());
        }
        return "redirect:/assets?aapresenta=" + aapresenta +
                "&vftipoimpu=" + vftipoimpu +
                "&cdpresenta=" + cdpresenta;
    }

    private void populateFormModel(Model model, UrbanPropertyDTO dto, boolean isNew) {
        model.addAttribute("property", dto);
        model.addAttribute("provinces", referenceDataService.getAllProvinces());
        model.addAttribute("isNew", isNew);
    }

    private UrbanPropertyDTO mapEntityToDto(UrbanProperty entity) {
        UrbanPropertyDTO dto = new UrbanPropertyDTO();
        dto.setAapresenta(entity.getAapresenta());
        dto.setVftipoimpu(entity.getVftipoimpu());
        dto.setCdpresenta(entity.getCdpresenta());
        dto.setCdsecubien(entity.getCdsecubien());
        dto.setCdprovinci(entity.getCdprovinci());
        dto.setCdmunicipi(entity.getCdmunicipi());
        dto.setCdpais(entity.getCdpais());
        dto.setTlrefecata(entity.getTlrefecata());
        dto.setCdtipobien(entity.getCdtipobien());
        dto.setCdtipoviap(entity.getCdtipoviap());
        dto.setTlnombviap(entity.getTlnombviap());
        dto.setTlnumeviap(entity.getTlnumeviap());
        dto.setTlcodipost(entity.getTlcodipost());
        dto.setTlescalera(entity.getTlescalera());
        dto.setTlpiso(entity.getTlpiso());
        dto.setTlpuerta(entity.getTlpuerta());
        dto.setVfduplicad(entity.getVfduplicad());
        dto.setAaconstruc(entity.getAaconstruc());
        dto.setCdsituaci1(entity.getCdsituaci1());
        dto.setCdsituaci2(entity.getCdsituaci2());
        dto.setItarrendam(entity.getItarrendam());
        dto.setAacontarre(entity.getAacontarre());
        dto.setItprotofic(entity.getItprotofic());
        dto.setItdescalif(entity.getItdescalif());
        dto.setItvivihabi(entity.getItvivihabi());
        dto.setPtvivihabi(entity.getPtvivihabi());
        dto.setNmunidades(entity.getNmunidades());
        dto.setNmsuperfic(entity.getNmsuperfic());
        dto.setPtmaxventa(entity.getPtmaxventa());
        dto.setPtdeclarad(entity.getPtdeclarad());
        dto.setPtcomproba(entity.getPtcomproba());
        dto.setPctransmis(entity.getPctransmis());
        dto.setCdposbien2(entity.getCdposbien2());
        dto.setItvalorref(entity.getItvalorref());
        dto.setItvrvalido(entity.getItvrvalido());
        dto.setItvalbdbi(entity.getItvalbdbi());
        dto.setPtvalorref(entity.getPtvalorref());
        dto.setCdzonaurba(entity.getCdzonaurba());
        dto.setTlobservac(entity.getTlobservac());
        return dto;
    }
}