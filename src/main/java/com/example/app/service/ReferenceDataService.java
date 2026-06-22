package com.example.app.service;

import com.example.app.entity.ReferenceData;
import com.example.app.entity.Province;
import com.example.app.entity.Municipality;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReferenceDataService {

    public List<ReferenceData> getNatureTypes() {
        List<ReferenceData> types = new ArrayList<>();
        types.add(new ReferenceData("01", "Inmueble urbano"));
        types.add(new ReferenceData("02", "Inmueble rústico"));
        types.add(new ReferenceData("03", "Vehículo"));
        types.add(new ReferenceData("04", "Cuenta bancaria"));
        types.add(new ReferenceData("05", "Valores"));
        types.add(new ReferenceData("06", "Otros bienes muebles"));
        return types;
    }

    public List<ReferenceData> getPositionTypes() {
        List<ReferenceData> types = new ArrayList<>();
        types.add(new ReferenceData("01", "Propietario"));
        types.add(new ReferenceData("02", "Usufructuario"));
        types.add(new ReferenceData("03", "Nudo propietario"));
        return types;
    }

    public List<Province> getAllProvinces() {
        List<Province> provinces = new ArrayList<>();
        provinces.add(new Province("01", "Álava"));
        provinces.add(new Province("02", "Albacete"));
        provinces.add(new Province("03", "Alicante"));
        provinces.add(new Province("28", "Madrid"));
        provinces.add(new Province("08", "Barcelona"));
        return provinces;
    }

    public List<Municipality> getMunicipalitiesByProvince(String cdprov) {
        List<Municipality> municipalities = new ArrayList<>();
        if ("28".equals(cdprov)) {
            municipalities.add(new Municipality("28", "001", "Madrid"));
            municipalities.add(new Municipality("28", "002", "Alcalá de Henares"));
            municipalities.add(new Municipality("28", "003", "Getafe"));
        } else if ("08".equals(cdprov)) {
            municipalities.add(new Municipality("08", "001", "Barcelona"));
            municipalities.add(new Municipality("08", "002", "Hospitalet de Llobregat"));
        }
        return municipalities;
    }
}
