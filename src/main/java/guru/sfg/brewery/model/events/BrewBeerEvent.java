package guru.sfg.brewery.model.events;

import guru.sfg.brewery.model.BeerDto;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BrewBeerEvent extends BeerEvent {
  private static final long serialVersionUID = 5338014347411749117L;

  public BrewBeerEvent(final BeerDto beerDto) {
    super(beerDto);
  }
}
