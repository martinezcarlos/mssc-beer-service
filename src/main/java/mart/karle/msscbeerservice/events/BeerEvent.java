package mart.karle.msscbeerservice.events;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import mart.karle.msscbeerservice.web.model.BeerDto;

import java.io.Serializable;

@Data
@Builder
@RequiredArgsConstructor
class BeerEvent implements Serializable {

  private static final long serialVersionUID = 4432271109954287209L;

  private final BeerDto beerDto;
}
