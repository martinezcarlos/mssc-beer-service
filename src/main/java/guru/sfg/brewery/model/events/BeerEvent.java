package guru.sfg.brewery.model.events;

import guru.sfg.brewery.model.BeerDto;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BeerEvent implements Serializable {

  private static final long serialVersionUID = 4432271109954287209L;

  private BeerDto beerDto;
}
