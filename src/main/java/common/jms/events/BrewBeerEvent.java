package common.jms.events;

import lombok.NoArgsConstructor;
import mart.karle.msscbeerservice.web.model.BeerDto;

@NoArgsConstructor
public class BrewBeerEvent extends BeerEvent {
  private static final long serialVersionUID = 5338014347411749117L;

  public BrewBeerEvent(final BeerDto beerDto) {
    super(beerDto);
  }
}
