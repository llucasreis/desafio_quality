package com.bootcamp.desafio.seuimovelapi.integration.districts;

import static org.mockito.ArgumentMatchers.any;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.bootcamp.desafio.seuimovelapi.modules.districts.domain.District;
import com.bootcamp.desafio.seuimovelapi.modules.districts.dtos.DistrictFormDTO;
import com.bootcamp.desafio.seuimovelapi.shared.errors.ValidationError;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
public class DistrictControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void shouldCreateADistrict() throws Exception {
        DistrictFormDTO formDTO = new DistrictFormDTO("Vila Olímpica", BigDecimal.valueOf(12345.12));

        String payload = mapper.writeValueAsString(formDTO);

        mvc.perform(post("/district")
                .contentType("application/json")
                .content(payload))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(5L));
    }

    @Test
    public void shouldNotCreateADistrictWithInvalidParams() throws Exception {
        DistrictFormDTO formDTO = new DistrictFormDTO("", BigDecimal.valueOf(-1));

        String payload = mapper.writeValueAsString(formDTO);

        mvc.perform(post("/district")
                .contentType("application/json")
                .content(payload))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error", Matchers.hasSize(2)))
                .andExpect(jsonPath("$.error[*].field", Matchers.containsInAnyOrder(
                        "prop_district", "value_district_m2")));
    }

    @Test
    public void shouldNotCreateADistrictIfAlreadyExist() throws Exception {
        DistrictFormDTO formDTO = new DistrictFormDTO("Japiim 2", BigDecimal.valueOf(2933.5));

        String payload = mapper.writeValueAsString(formDTO);
        String expectedError = "Bairro já existe";

        mvc.perform(post("/district")
                .contentType("application/json")
                .content(payload))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.error").value(expectedError));
    }
}
