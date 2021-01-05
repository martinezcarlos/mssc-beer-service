package guru.sfg.brewery.model.events;

import lombok.NoArgsConstructor;
import guru.sfg.brewery.model.BeerDto;

@NoArgsConstructor
public class NewInventoryEvent extends BeerEvent {
  private static final long serialVersionUID = 5338014347411749117L;

  public NewInventoryEvent(final BeerDto beerDto) {
    super(beerDto);
  }
}
