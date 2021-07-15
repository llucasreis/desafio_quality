package com.bootcamp.desafio.seuimovelapi.modules.properties.repositories;

import com.bootcamp.desafio.seuimovelapi.modules.properties.domain.District;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class DistrictRepository {

    private final List<District> districtList;

    public DistrictRepository() {
        this.districtList = new ArrayList<>(Arrays.asList(
                new District("Japiim 2"),
                new District("Vieralves"),
                new District("Ponta Negra"),
                new District("Cachoeirinha")
        ));
    }

    public District findByName(String prop_district) {
        return this.districtList.stream().filter(d -> d.getProp_district().equals(prop_district)).findFirst().orElse(null);
    }


}
