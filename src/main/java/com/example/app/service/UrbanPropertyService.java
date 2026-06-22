package com.example.app.service;

import com.example.app.dto.UrbanPropertyDTO;
import com.example.app.entity.UrbanProperty;
import com.example.app.entity.AssetDocumentId;
import com.example.app.exception.BusinessValidationException;
import com.example.app.exception.ResourceNotFoundException;
import com.example.app.repository.UrbanPropertyRepository;
import com.example.app.repository.ProvinceRepository;
import com.example.app.repository.MunicipalityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UrbanPropertyService {

    private final UrbanPropertyRepository urbanPropertyRepository;
    private final ProvinceRepository provinceRepository;
    private final MunicipalityRepository municipalityRepository;
    private final ValidationService validationService;

    @Autowired
    public UrbanPropertyService(UrbanPropertyRepository urbanPropertyRepository,
                                 ProvinceRepository provinceRepository,
                                 MunicipalityRepository municipalityRepository,
                                 ValidationService validationService) {
        this.urbanPropertyRepository = urbanPropertyRepository;
        this.provinceRepository = provinceRepository;
        this.municipalityRepository = municipalityRepository;
        this.validationService = validationService;
    }

    public List<UrbanProperty> findAllByDeclaration(String aapresenta, String vftipoimpu, String cdpresenta) {
        return urbanPropertyRepository.findByAapresentaAndVftipoimpuAndCdpresenta(aapresenta, vftipoimpu, cdpresenta);
    }

    public Optional<UrbanProperty> findById(String aapresenta, String vftipoimpu, String cdpresenta, String cdsecubien) {
        AssetDocumentId id = new AssetDocumentId(aapresenta, vftipoimpu, cdpresenta, cdsecubien);
        return urbanPropertyRepository.findById(id);
    }

    @Transactional
    public UrbanProperty create(UrbanPropertyDTO dto) {
        validateUrbanProperty(dto, null);

        UrbanProperty entity = mapDtoToEntity(dto);
        applyDefaults(entity);

        return urbanPropertyRepository.save(entity);
    }

    @Transactional
    public UrbanProperty update(UrbanPropertyDTO dto) {
        AssetDocumentId id = new AssetDocumentId(dto.getAapresenta(), dto.getVftipoimpu(), dto.getCdpresenta(), dto.getCdsecubien());
        UrbanProperty existing = urbanPropertyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bien urbano no existente."));

        validateUrbanProperty(dto, existing);

        updateEntityFromDto(existing, dto);

        return urbanPropertyRepository.save(existing);
    }

    @Transactional
    public void delete(String aapresenta, String vftipoimpu, String cdpresenta, String cdsecubien) {
        AssetDocumentId id = new AssetDocumentId(aapresenta, vftipoimpu, cdpresenta, cdsecubien);
        if (!urbanPropertyRepository.existsById(id)) {
            throw new ResourceNotFoundException("Bien urbano no existente.");
        }
        urbanPropertyRepository.deleteById(id);
    }

    private void validateUrbanProperty(UrbanPropertyDTO dto, UrbanProperty existing) {
        validationService.validateProvinceCode(dto.getCdprovinci());
        validationService.validateMunicipalityCode(dto.getCdmunicipi(), dto.getCdprovinci());
        validationService.validateTransmissionPercentage(dto.getPctransmis());
        validationService.validateDeclaredValue(dto.getPtdeclarad());
        validationService.validateVerifiedValue(dto.getPtcomproba());
        validationService.validateConstructionYear(dto.getAaconstruc(), null);
        validationService.validateRentalContractYear(dto.getAacontarre(), null);
        validationService.validateYesNoIndicator(dto.getItarrendam(), "Arrendamiento");
        validationService.validateYesNoIndicator(dto.getItprotofic(), "Protección oficial");
        validationService.validateYesNoIndicator(dto.getItdescalif(), "Descalificación");
        validationService.validateYesNoIndicator(dto.getItvivihabi(), "Vivienda habitual");
        validationService.validateDuplicateIndicator(dto.getVfduplicad());
        validationService.validateCadastralReference(dto.getTlrefecata(), "U");
        validationService.validatePositionType(dto.getCdposbien2());

        if ("S".equals(dto.getItvivihabi())) {
            long count = urbanPropertyRepository.countOtherHabitualResidences(
                    dto.getAapresenta(), dto.getVftipoimpu(), dto.getCdpresenta(), dto.getCdsecubien());
            if (count > 0) {
                throw new BusinessValidationException("Ya existe otro bien urbano como vivienda habitual.");
            }
        }

        if (dto.getPtvivihabi() != null && dto.getPtdeclarad() != null) {
            if (dto.getPtvivihabi().compareTo(dto.getPtdeclarad()) > 0) {
                throw new BusinessValidationException("El valor de vivienda habitual no puede exceder el valor declarado.");
            }
        }

        if (dto.getNmunidades() != null && !"PG".equals(dto.getCdtipobien())) {
            if (dto.getNmunidades() != 1) {
                throw new BusinessValidationException("Único valor permitido para este tipo de bien: 1");
            }
        }
    }

    private UrbanProperty mapDtoToEntity(UrbanPropertyDTO dto) {
        UrbanProperty entity = new UrbanProperty();
        entity.setAapresenta(dto.getAapresenta());
        entity.setVftipoimpu(dto.getVftipoimpu());
        entity.setCdpresenta(dto.getCdpresenta());
        entity.setCdsecubien(dto.getCdsecubien());
        updateEntityFromDto(entity, dto);
        return entity;
    }

    private void updateEntityFromDto(UrbanProperty entity, UrbanPropertyDTO dto) {
        entity.setCdprovinci(validationService.padLeft(dto.getCdprovinci(), 2, '0'));
        entity.setCdmunicipi(dto.getCdmunicipi() != null ? validationService.padLeft(dto.getCdmunicipi(), 3, '0') : null);
        entity.setCdpais(dto.getCdpais());
        entity.setTlrefecata(dto.getTlrefecata() != null ? dto.getTlrefecata().replaceAll("\\s+", "") : null);
        entity.setCdtipobien(dto.getCdtipobien() != null ? validationService.padLeft(dto.getCdtipobien(), 2, '0') : null);
        entity.setCdtipoviap(dto.getCdtipoviap());
        entity.setTlnombviap(dto.getTlnombviap());
        entity.setTlnumeviap(dto.getTlnumeviap());
        entity.setTlcodipost(dto.getTlcodipost());
        entity.setTlescalera(dto.getTlescalera());
        entity.setTlpiso(dto.getTlpiso());
        entity.setTlpuerta(dto.getTlpuerta());
        entity.setVfduplicad(dto.getVfduplicad());
        entity.setAaconstruc(dto.getAaconstruc());
        entity.setCdsituaci1(dto.getCdsituaci1());
        entity.setCdsituaci2(dto.getCdsituaci2());
        entity.setItarrendam(dto.getItarrendam());
        entity.setAacontarre(dto.getAacontarre());
        entity.setItprotofic(dto.getItprotofic());
        entity.setItdescalif(dto.getItdescalif());
        entity.setItvivihabi(dto.getItvivihabi());
        entity.setPtvivihabi(truncateValue(dto.getPtvivihabi(), 2));
        entity.setNmunidades(dto.getNmunidades());
        entity.setNmsuperfic(truncateValue(dto.getNmsuperfic(), 2));
        entity.setPtmaxventa(truncateValue(dto.getPtmaxventa(), 2));
        entity.setPtdeclarad(truncateValue(dto.getPtdeclarad(), 2));
        entity.setPtcomproba(truncateValue(dto.getPtcomproba(), 2));
        entity.setPctransmis(dto.getPctransmis());
        entity.setCdposbien2(dto.getCdposbien2());
        entity.setItvalorref(dto.getItvalorref());
        entity.setItvrvalido(dto.getItvrvalido());
        entity.setItvalbdbi(dto.getItvalbdbi());
        entity.setPtvalorref(dto.getPtvalorref());
        entity.setCdzonaurba(dto.getCdzonaurba());
        entity.setTlobservac(dto.getTlobservac());
    }

    private void applyDefaults(UrbanProperty entity) {
        if (entity.getCdpais() == null || entity.getCdpais().trim().isEmpty()) {
            entity.setCdpais("ESP");
        }
        if (entity.getPctransmis() == null) {
            entity.setPctransmis(new BigDecimal("100"));
        }
        if (entity.getItarrendam() == null) {
            entity.setItarrendam("N");
        }
        if (entity.getItprotofic() == null) {
            entity.setItprotofic("N");
        }
        if (entity.getItdescalif() == null) {
            entity.setItdescalif("N");
        }
        if (entity.getItvivihabi() == null) {
            entity.setItvivihabi("N");
        }
    }

    private BigDecimal truncateValue(BigDecimal value, int scale) {
        if (value == null) {
            return null;
        }
        return value.setScale(scale, RoundingMode.DOWN);
    }

    public BigDecimal calculateVerifiedValuePercentage(BigDecimal ptcomproba, BigDecimal pctransmis, int decimalPlaces) {
        if (ptcomproba == null || pctransmis == null) {
            return null;
        }
        return ptcomproba.multiply(pctransmis).divide(new BigDecimal("100"), decimalPlaces, RoundingMode.HALF_UP);
    }

    public String calculateConformity(String itvalorref, String itvrvalido, BigDecimal ptvalorref, BigDecimal ptdeclarad) {
        if ("S".equals(itvalorref) && "S".equals(itvrvalido) && ptvalorref != null) {
            if (ptdeclarad != null && ptdeclarad.compareTo(ptvalorref) >= 0) {
                return "S";
            }
        }
        return "N";
    }
}