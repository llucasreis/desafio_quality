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
                new District(1L, "Japiim 2", BigDecimal.valueOf(2933.5)),
                new District(2L, "Vieralves", BigDecimal.valueOf(5432.7)),
                new District(3L, "Ponta Negra", BigDecimal.valueOf(7931.3)),
                new District(4L, "Cachoeirinha", BigDecimal.valueOf(1730.9))
        ));
    }

    public District findByName(String prop_district) {
        return this.districtList.stream().filter(d -> d.getProp_district().equals(prop_district)).findFirst().orElse(null);
    }

}
