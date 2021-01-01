package mart.karle.msscbeerservice.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;
import mart.karle.msscbeerservice.service.BeerService;
import mart.karle.msscbeerservice.web.mapper.BeerMapper;
import mart.karle.msscbeerservice.web.model.BeerDto;
import mart.karle.msscbeerservice.web.model.BeerStyleEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.StringUtils;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureRestDocs
@WebMvcTest(BeerController.class)
@ExtendWith(RestDocumentationExtension.class)
class BeerControllerTest {

  @Autowired private MockMvc mockMvc;

  @Autowired private ObjectMapper objectMapper;

  @MockBean private BeerMapper beerMapper;
  @MockBean private BeerService beerService;

  @BeforeEach
  void setUp() {}

  @Test
  void getBeerById() throws Exception {
    final ConstrainedFields fields = new ConstrainedFields(BeerDto.class);

    given(beerService.getById(any(), anyBoolean())).willReturn(buildDto());

    mockMvc
        .perform(
            get(BeerController.BASE_URL + "/{beerId}", UUID.randomUUID().toString())
                .accept(MediaType.APPLICATION_JSON)
                .param("isCold", "yes"))
        .andExpect(status().isOk())
        .andDo(
            document(
                "v1/beer/find",
                pathParameters(
                    parameterWithName("beerId").description("UUID of the desired beer to get.")),
                requestParameters(
                    parameterWithName("isCold").description("Is beer cold query param.")),
                responseFields(
                    fields.withPath("id").description("Id of the beer.").type(UUID.class),
                    fields
                        .withPath("version")
                        .description("Version of the beer.")
                        .type(Integer.class),
                    fields
                        .withPath("createdDate")
                        .description("Creation of the entry")
                        .type(OffsetDateTime.class),
                    fields
                        .withPath("lastModifiedDate")
                        .description("Last modification")
                        .type(OffsetDateTime.class),
                    fields.withPath("name").description("Name of the beer.").type(String.class),
                    fields
                        .withPath("style")
                        .description("Style of the beer.")
                        .type(BeerStyleEnum.class),
                    fields.withPath("upc").description("UPC of the beer.").type(Long.class),
                    fields
                        .withPath("price")
                        .description("Price of the beer.")
                        .type(BigDecimal.class),
                    fields
                        .withPath("quantityOnHand")
                        .description("Quantity on hand.")
                        .type(Integer.class))));
  }

  private BeerDto buildDto() {
    return BeerDto.builder()
        .name("My Beer")
        .style(BeerStyleEnum.ALE)
        .price(BigDecimal.ONE)
        .upc("123243453")
        .build();
  }

  @Test
  void saveNewBeer() throws Exception {
    // Given
    final String value = objectMapper.writeValueAsString(buildDto());
    given(beerService.save(any())).willReturn(buildDto());
    // When
    // Then
    final ConstrainedFields fields = new ConstrainedFields(BeerDto.class);

    mockMvc
        .perform(
            post(BeerController.BASE_URL + "/new")
                .content(value)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andDo(
            document(
                "v1/beer/create",
                requestFields(
                    fields.withPath("id").ignored(),
                    fields.withPath("version").ignored(),
                    fields.withPath("createdDate").ignored(),
                    fields.withPath("lastModifiedDate").ignored(),
                    fields.withPath("name").description("Name of the beer.").type(String.class),
                    fields
                        .withPath("style")
                        .description("Style of the beer.")
                        .type(BeerStyleEnum.class),
                    fields.withPath("upc").description("UPC of the beer.").type(Long.class),
                    fields
                        .withPath("price")
                        .description("Price of the beer.")
                        .type(BigDecimal.class),
                    fields.withPath("quantityOnHand").ignored())));
  }

  @Test
  void updateBeer() throws Exception {
    // Given
    final String value = objectMapper.writeValueAsString(buildDto());
    given(beerService.getById(any(), anyBoolean())).willReturn(buildDto());
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

  private static class ConstrainedFields {

    private final ConstraintDescriptions constraintDescriptions;

    ConstrainedFields(final Class<?> input) {
      constraintDescriptions = new ConstraintDescriptions(input);
    }

    private FieldDescriptor withPath(final String path) {
      return fieldWithPath(path)
          .attributes(
              key("constraints")
                  .value(
                      StringUtils.collectionToDelimitedString(
                          constraintDescriptions.descriptionsForProperty(path), ". ")));
    }
  }
}
