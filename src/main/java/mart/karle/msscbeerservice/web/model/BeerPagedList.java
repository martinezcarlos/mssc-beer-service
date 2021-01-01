package mart.karle.msscbeerservice.web.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.Serializable;
import java.util.List;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class BeerPagedList extends PageImpl<BeerDto> implements Serializable {
  private static final long serialVersionUID = -2718399308467384233L;

  @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
  public BeerPagedList(
      @JsonProperty("content") final List<BeerDto> content,
      @JsonProperty("number") final int number,
      @JsonProperty("size") final int size,
      @JsonProperty("totalElements") final Long totalElements,
      @JsonProperty("pageable") final JsonNode pageable,
      @JsonProperty("last") final boolean last,
      @JsonProperty("totalPages") final int totalPages,
      @JsonProperty("sort") final JsonNode sort,
      @JsonProperty("first") final boolean first,
      @JsonProperty("numberOfElements") final int numberOfElements) {
    super(content, PageRequest.of(number, size), totalElements);
  }

  public BeerPagedList(final List<BeerDto> content, final Pageable pageable, final long total) {
    super(content, pageable, total);
  }

  public BeerPagedList(final List<BeerDto> content) {
    super(content);
  }
}
