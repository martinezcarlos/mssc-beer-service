package guru.sfg.brewery.msscbeerservice.service;

import guru.sfg.brewery.model.BeerDto;
import guru.sfg.brewery.model.BeerPagedList;
import java.util.UUID;
import org.springframework.data.domain.PageRequest;

public interface BeerService {
  BeerDto getById(UUID beerId, Boolean showInventoryOnHand);

  BeerDto save(BeerDto beerDto);

  void update(UUID beerId, BeerDto beerDto);

  BeerPagedList listBeers(String name, String style, PageRequest of, Boolean showInventoryOnHand);

  BeerDto getByUpc(String upc);
}
