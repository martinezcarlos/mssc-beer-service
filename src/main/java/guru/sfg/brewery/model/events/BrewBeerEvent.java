package guru.sfg.brewery.model.events;

import lombok.NoArgsConstructor;
import guru.sfg.brewery.service.web.model.BeerDto;

@NoArgsConstructor
public class BrewBeerEvent extends BeerEvent {
  private static final long serialVersionUID = 5338014347411749117L;

  public BrewBeerEvent(final BeerDto beerDto) {
    super(beerDto);
  }
}
