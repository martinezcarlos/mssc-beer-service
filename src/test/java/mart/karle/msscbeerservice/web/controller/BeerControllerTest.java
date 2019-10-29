package mart.karle.msscbeerservice.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import mart.karle.msscbeerservice.web.model.BeerDto;
import mart.karle.msscbeerservice.web.model.BeerStyleEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BeerController.class)
class BeerControllerTest {

  @Autowired private MockMvc mockMvc;

  @Autowired private ObjectMapper objectMapper;

  @BeforeEach
  void setUp() {}

  @Test
  void getBeerById() throws Exception {
    mockMvc
        .perform(
            get(BeerController.BASE_URL + "/" + UUID.randomUUID())
                .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  void saveNewBeer() throws Exception {
    // Given
    final String value = objectMapper.writeValueAsString(buildDto());
    // When
    // Then
    mockMvc
        .perform(
            post(BeerController.BASE_URL + "/new")
                .content(value)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isCreated());
  }

  @Test
  void updateBeer() throws Exception {
    // Given
    final String value = objectMapper.writeValueAsString(buildDto());
    // When
    // Then
    mockMvc
        .perform(
            put(BeerController.BASE_URL + "/" + UUID.randomUUID())
                .content(value)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isNoContent());
  }

  private BeerDto buildDto() {
    return BeerDto.builder()
        .name("My Beer")
        .style(BeerStyleEnum.ALE)
        .price(BigDecimal.ONE)
        .upc(123243453L)
        .build();
  }
}
