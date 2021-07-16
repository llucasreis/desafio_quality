package com.bootcamp.desafio.seuimovelapi.integration.properties;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.bootcamp.desafio.seuimovelapi.modules.properties.domain.Property;
import com.bootcamp.desafio.seuimovelapi.modules.properties.dtos.PropertyFormDTO;
import com.bootcamp.desafio.seuimovelapi.modules.properties.dtos.RoomFormDTO;
import com.bootcamp.desafio.seuimovelapi.modules.properties.dtos.RoomSquareMetersDTO;
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
import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
public class PropertyControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void shouldCreateAProperty() throws Exception {
        PropertyFormDTO formDTO = new PropertyFormDTO("Casa1", 1L, List.of(
                new RoomFormDTO("Quarto", 25.0, 25.0)
        ));

        String payload = mapper.writeValueAsString(formDTO);

        mvc.perform(post("/property")
                .contentType("application/json")
                .content(payload))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    public void shouldNotCreateAPropertyWithInvalidParams() throws Exception {
        PropertyFormDTO formDTO = new PropertyFormDTO("", 1L, new ArrayList<>());

        String payload = mapper.writeValueAsString(formDTO);

        mvc.perform(post("/property")
                .contentType("application/json")
                .content(payload))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error", Matchers.hasSize(3)))
                .andExpect(jsonPath("$.error[*].field", Matchers.containsInAnyOrder(
                        "prop_name", "prop_name", "rooms"
                )));
    }

    @Test
    public void shouldNotCreateAPropertyWithANonExistingDistrict() throws Exception {
        PropertyFormDTO formDTO = new PropertyFormDTO("Casa1", 5L, List.of(
                new RoomFormDTO("Quarto", 25.0, 25.0)
        ));

        String payload = mapper.writeValueAsString(formDTO);
        String expectedMessage = "Bairro não encontrado";

        mvc.perform(post("/property")
                .contentType("application/json")
                .content(payload))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value(expectedMessage));
    }

    @Test
    public void shouldCalculateTotalSquareMeters() throws Exception {
        PropertyFormDTO formDTO = new PropertyFormDTO("Casa1", 1L, List.of(
                new RoomFormDTO("Quarto", 25.0, 25.0)
        ));

        String payload = mapper.writeValueAsString(formDTO);

        TypeReference<Property> typeReference = new TypeReference<>() {};
        double expectedSquareMeters = 625.0;

        mvc.perform(post("/property")
                .contentType("application/json")
                .content(payload))
                .andExpect(status().isCreated())
                .andDo(result -> {
                    Property property = mapper.readValue(result.getResponse().getContentAsString(), typeReference);
                    String request = "/property/" + property.getId() + "/totalSquareMeters";

                    mvc.perform(get(request))
                            .andExpect(jsonPath("$.id").value(property.getId()))
                            .andExpect(jsonPath("$.total_square_meters").value(expectedSquareMeters));
                });
    }

    @Test
    public void shouldCalculateTotalValue() throws Exception {
        PropertyFormDTO formDTO = new PropertyFormDTO("Casa1", 1L, List.of(
                new RoomFormDTO("Quarto", 25.0, 25.0)
        ));

        String payload = mapper.writeValueAsString(formDTO);

        TypeReference<Property> typeReference = new TypeReference<>() {};
        BigDecimal expectedTotalValue = BigDecimal.valueOf(2933.5 * 625.0);

        mvc.perform(post("/property")
                .contentType("application/json")
                .content(payload))
                .andExpect(status().isCreated())
                .andDo(result -> {
                    Property property = mapper.readValue(result.getResponse().getContentAsString(), typeReference);
                    String request = "/property/" + property.getId() + "/totalValue";

                    mvc.perform(get(request))
                            .andExpect(jsonPath("$.id").value(property.getId()))
                            .andExpect(jsonPath("$.total_value").value(expectedTotalValue));
                });
    }

    @Test
    public void shouldGetTheBiggestRoom() throws Exception {
        PropertyFormDTO formDTO = new PropertyFormDTO("Casa1", 1L, List.of(
                new RoomFormDTO("Quarto", 25.0, 25.0),
                new RoomFormDTO("Banheiro", 15.0, 15.0)
        ));

        String payload = mapper.writeValueAsString(formDTO);

        TypeReference<Property> typeReference = new TypeReference<>() {};
        String expectedName = "Quarto";
        double expectedSquareMeters = 625.0;

        mvc.perform(post("/property")
                .contentType("application/json")
                .content(payload))
                .andExpect(status().isCreated())
                .andDo(result -> {
                    Property property = mapper.readValue(result.getResponse().getContentAsString(), typeReference);
                    String request = "/property/" + property.getId() + "/biggestRoom";

                    mvc.perform(get(request))
                            .andExpect(jsonPath("$.room_name").value(expectedName))
                            .andExpect(jsonPath("$.square_meters").value(expectedSquareMeters));
                });
    }

    @Test
    public void shouldGetTheRooms() throws Exception {
        PropertyFormDTO formDTO = new PropertyFormDTO("Casa1", 1L, List.of(
                new RoomFormDTO("Quarto", 25.0, 25.0),
                new RoomFormDTO("Banheiro", 15.0, 15.0)
        ));

        String payload = mapper.writeValueAsString(formDTO);

        TypeReference<Property> typeReference = new TypeReference<>() {};

        mvc.perform(post("/property")
                .contentType("application/json")
                .content(payload))
                .andExpect(status().isCreated())
                .andDo(result -> {
                    Property property = mapper.readValue(result.getResponse().getContentAsString(), typeReference);
                    String request = "/property/" + property.getId() + "/rooms";

                    mvc.perform(get(request))
                            .andExpect(jsonPath("$[*].room_name", Matchers.hasSize(2)))
                            .andExpect(jsonPath("$[*].room_name", Matchers.containsInAnyOrder(
                                    "Quarto", "Banheiro")))
                            .andExpect(jsonPath("$[*].square_meters", Matchers.containsInAnyOrder(
                                    625.0, 225.0
                            )));
                });
    }
}
