package guru.sfg.brewery.service.service;

import guru.sfg.brewery.service.web.model.BeerDto;
import guru.sfg.brewery.service.web.model.BeerPagedList;

import org.springframework.data.domain.PageRequest;

import java.util.UUID;

public interface BeerService {
  BeerDto getById(UUID beerId, Boolean showInventoryOnHand);

  BeerDto save(BeerDto beerDto);

  void update(UUID beerId, BeerDto beerDto);

  BeerPagedList listBeers(String name, String style, PageRequest of, Boolean showInventoryOnHand);

  BeerDto getByUpc(String upc);
}
