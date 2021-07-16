package com.bootcamp.desafio.seuimovelapi.modules.districts.repositories;

import com.bootcamp.desafio.seuimovelapi.modules.districts.domain.District;
import com.bootcamp.desafio.seuimovelapi.modules.properties.domain.Property;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class DistrictRepositoryImpl implements DistrictRepository {

    private final List<District> districtList;

    public DistrictRepositoryImpl() {
        this.districtList = new ArrayList<>(Arrays.asList(
                new District(1L, "Japiim 2", BigDecimal.valueOf(2933.5)),
                new District(2L, "Vieralves", BigDecimal.valueOf(5432.7)),
                new District(3L, "Ponta Negra", BigDecimal.valueOf(7931.3)),
                new District(4L, "Cachoeirinha", BigDecimal.valueOf(1730.9))
        ));
    }

    private Long getNextId() {
        if (this.districtList.size() == 0) return 1L;

        District lastDistrict = this.districtList.get(this.districtList.size() - 1);

        return lastDistrict.getId()+1;
    }

    @Override
    public boolean createDistrict(District district) {
        district.setId(this.getNextId());

        return this.districtList.add(district);
    }

    @Override
    public District findByName(String name) {
        return this.districtList.stream().filter(d -> d.getProp_district().equals(name)).findFirst().orElse(null);
    }

    @Override
    public District findById(Long id) {
        return this.districtList.stream().filter(d -> d.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public List<District> findAll() {
        return this.districtList;
    }
}
