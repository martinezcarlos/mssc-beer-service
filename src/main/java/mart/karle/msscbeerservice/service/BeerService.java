package mart.karle.msscbeerservice.service;

import mart.karle.msscbeerservice.web.model.BeerDto;
import mart.karle.msscbeerservice.web.model.BeerPagedList;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

public interface BeerService {
  BeerDto getById(UUID beerId, Boolean showInventoryOnHand);

  BeerDto save(BeerDto beerDto);

  void update(UUID beerId, BeerDto beerDto);

  BeerPagedList listBeers(String name, String style, PageRequest of, Boolean showInventoryOnHand);

  BeerDto getByUpc(String upc);
}
