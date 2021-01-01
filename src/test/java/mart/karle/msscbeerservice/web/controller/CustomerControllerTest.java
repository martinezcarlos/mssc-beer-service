package mart.karle.msscbeerservice.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.UUID;
import mart.karle.msscbeerservice.web.model.CustomerDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

  @Autowired private MockMvc mockMvc;

  @Autowired private ObjectMapper objectMapper;

  @BeforeEach
  void setUp() {}

  @Test
  void getBeerById() throws Exception {
    mockMvc
        .perform(
            get(CustomerController.BASE_URL + "/" + UUID.randomUUID())
                .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  void saveNewBeer() throws Exception {
    // Given
    final CustomerDto customerDto = CustomerDto.builder().name("Jack").build();
    final String value = objectMapper.writeValueAsString(customerDto);
    // When
    // Then
    mockMvc
        .perform(
            post(CustomerController.BASE_URL + "/new")
                .content(value)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isCreated());
  }

  @Test
  void updateBeer() throws Exception {
    // Given
    final CustomerDto customerDto = CustomerDto.builder().name("Jack").build();
    final String value = objectMapper.writeValueAsString(customerDto);
    // When
    // Then
    mockMvc
        .perform(
            put(CustomerController.BASE_URL + "/" + UUID.randomUUID())
                .content(value)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isNoContent());
  }
}
