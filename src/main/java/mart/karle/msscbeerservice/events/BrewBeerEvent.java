package mart.karle.msscbeerservice.events;

import mart.karle.msscbeerservice.web.model.BeerDto;

public class BrewBeerEvent extends BeerEvent {
  private static final long serialVersionUID = 5338014347411749117L;

  BrewBeerEvent(final BeerDto beerDto) {
    super(beerDto);
  }
}
