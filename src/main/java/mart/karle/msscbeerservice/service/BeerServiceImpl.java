package mart.karle.msscbeerservice.service;

import lombok.RequiredArgsConstructor;
import mart.karle.msscbeerservice.domain.Beer;
import mart.karle.msscbeerservice.repository.BeerRepository;
import mart.karle.msscbeerservice.web.controller.NotFoundException;
import mart.karle.msscbeerservice.web.mapper.BeerMapper;
import mart.karle.msscbeerservice.web.model.BeerDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {

  private final BeerRepository beerRepository;
  private final BeerMapper beerMapper;

  @Override
  public BeerDto getById(final UUID beerId) {
    return beerMapper.entityToDto(
        beerRepository.findById(beerId).orElseThrow(NotFoundException::new));
  }

  @Override
  public BeerDto save(final BeerDto beerDto) {
    return beerMapper.entityToDto(beerRepository.save(beerMapper.dtoToEntity(beerDto)));
  }

  @Override
  public void update(final UUID beerId, final BeerDto beerDto) {
    final Beer beer = beerRepository.findById(beerId).orElseThrow(NotFoundException::new);
    beer.setName(beerDto.getName());
    beer.setStyle(beerDto.getStyle().name());
    beer.setPrice(beerDto.getPrice());
    beer.setUpc(beerDto.getUpc());
    beerRepository.save(beer);
  }
}
