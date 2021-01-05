package guru.sfg.brewery.model.events;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import guru.sfg.brewery.service.web.model.BeerDto;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BeerEvent implements Serializable {

  private static final long serialVersionUID = 4432271109954287209L;

  private BeerDto beerDto;
}
