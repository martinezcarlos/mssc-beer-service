package mart.karle.msscbeerservice.service;

import mart.karle.msscbeerservice.web.model.BeerDto;

import java.util.UUID;

public interface BeerService {
  BeerDto getById(UUID beerId);

  BeerDto save(BeerDto beerDto);

  void update(UUID beerId, BeerDto beerDto);
}
