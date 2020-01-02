package mart.karle.msscbeerservice.events;

import java.io.Serializable;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import mart.karle.msscbeerservice.web.model.BeerDto;

@Data
@Builder
@RequiredArgsConstructor
public class BeerEvent implements Serializable {

  private static final long serialVersionUID = 4432271109954287209L;

  private final BeerDto beerDto;
}
