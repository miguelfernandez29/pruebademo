package com.example.app.service;

import com.example.app.dto.VehicleDTO;
import com.example.app.entity.Vehicle;
import com.example.app.entity.AssetDocumentId;
import com.example.app.exception.ResourceNotFoundException;
import com.example.app.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final ValidationService validationService;

    @Autowired
    public VehicleService(VehicleRepository vehicleRepository, ValidationService validationService) {
        this.vehicleRepository = vehicleRepository;
        this.validationService = validationService;
    }

    public List<Vehicle> findAllByDeclaration(String aapresenta, String vftipoimpu, String cdpresenta) {
        return vehicleRepository.findByAapresentaAndVftipoimpu AndCdpresenta(aapresenta, vftipoimpu, cdpresenta);
    }

    public Optional<Vehicle> findById(String aapresenta, String vftipoimpu, String cdpresenta, String cdsecubien) {
        AssetDocumentId id = new AssetDocumentId(aapresenta, vftipoimpu, cdpresenta, cdsecubien);
        return vehicleRepository.findById(id);
    }

    @Transactional
    public Vehicle create(VehicleDTO dto, LocalDate fcdevengo) {
        validateVehicle(dto, fcdevengo);

        Vehicle entity = new Vehicle();
        entity.setAapresenta(dto.getAapresenta());
        entity.setVftipoimpu(dto.getVftipoimpu());
        entity.setCdpresenta(dto.getCdpresenta());
        entity.setCdsecubien(dto.getCdsecubien());
        updateEntityFromDto(entity, dto);

        return vehicleRepository.save(entity);
    }

    @Transactional
    public Vehicle update(VehicleDTO dto, LocalDate fcdevengo) {
        AssetDocumentId id = new AssetDocumentId(dto.getAapresenta(), dto.getVftipoimpu(), dto.getCdpresenta(), dto.getCdsecubien());
        Vehicle existing = vehicleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehículo no existente."));

        validateVehicle(dto, fcdevengo);
        updateEntityFromDto(existing, dto);

        return vehicleRepository.save(existing);
    }

    @Transactional
    public void delete(String aapresenta, String vftipoimpu, String cdpresenta, String cdsecubien) {
        AssetDocumentId id = new AssetDocumentId(aapresenta, vftipoimpu, cdpresenta, cdsecubien);
        if (!vehicleRepository.existsById(id)) {
            throw new ResourceNotFoundException("Vehículo no existente.");
        }
        vehicleRepository.deleteById(id);
    }

    private void validateVehicle(VehicleDTO dto, LocalDate fcdevengo) {
        validationService.validateVehicleRegistrationDate(dto.getFcmatricul(), fcdevengo);
        validationService.validateDeclaredValue(dto.getPtdeclarad());
        validationService.validateVerifiedValue(dto.getPtcomproba());
    }

    private void updateEntityFromDto(Vehicle entity, VehicleDTO dto) {
        entity.setFcmatricul(dto.getFcmatricul());
        entity.setFcvehicata(dto.getFcvehicata());
        entity.setCdvehitipo(dto.getCdvehitipo());
        entity.setCdvehimarc(dto.getCdvehimarc());
        entity.setCdvehimode(dto.getCdvehimode());
        entity.setNmcilindcc(dto.getNmcilindcc());
        entity.setPtdeclarad(truncateValue(dto.getPtdeclarad(), 2));
        entity.setPtcomproba(truncateValue(dto.getPtcomproba(), 2));
        entity.setPctitulari(dto.getPctitulari());
        entity.setTlobservac(dto.getTlobservac());
    }

    private BigDecimal truncateValue(BigDecimal value, int scale) {
        if (value == null) {
            return null;
        }
        return value.setScale(scale, RoundingMode.DOWN);
    }

    public BigDecimal calculateVerifiedValue(BigDecimal catalogValue, LocalDate registrationDate, LocalDate accrualDate) {
        if (catalogValue == null || registrationDate == null || accrualDate == null) {
            return null;
        }
        int yearsOld = accrualDate.getYear() - registrationDate.getYear();
        if (yearsOld < 0) {
            yearsOld = 0;
        }
        BigDecimal depreciationFactor = getDepreciationFactor(yearsOld);
        return catalogValue.multiply(depreciationFactor).setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal getDepreciationFactor(int yearsOld) {
        if (yearsOld <= 1) return new BigDecimal("1.00");
        if (yearsOld == 2) return new BigDecimal("0.84");
        if (yearsOld == 3) return new BigDecimal("0.67");
        if (yearsOld == 4) return new BigDecimal("0.56");
        if (yearsOld == 5) return new BigDecimal("0.47");
        if (yearsOld == 6) return new BigDecimal("0.39");
        if (yearsOld == 7) return new BigDecimal("0.34");
        if (yearsOld == 8) return new BigDecimal("0.28");
        if (yearsOld == 9) return new BigDecimal("0.24");
        if (yearsOld == 10) return new BigDecimal("0.19");
        if (yearsOld == 11) return new BigDecimal("0.17");
        return new BigDecimal("0.10");
    }
}