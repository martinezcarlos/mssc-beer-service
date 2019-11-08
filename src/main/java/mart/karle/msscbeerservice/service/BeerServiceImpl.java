package mart.karle.msscbeerservice.service;

import lombok.RequiredArgsConstructor;
import mart.karle.msscbeerservice.domain.Beer;
import mart.karle.msscbeerservice.repository.BeerRepository;
import mart.karle.msscbeerservice.web.controller.NotFoundException;
import mart.karle.msscbeerservice.web.mapper.BeerMapper;
import mart.karle.msscbeerservice.web.model.BeerDto;
import mart.karle.msscbeerservice.web.model.BeerPagedList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {

  private final BeerRepository beerRepository;
  private final BeerMapper beerMapper;

  @Override
  public BeerDto getById(final UUID beerId, final Boolean showInventoryOnHand) {
    final Function<Beer, BeerDto> mapperFunction =
        Boolean.TRUE.equals(showInventoryOnHand)
            ? beerMapper::entityToDtoWithInventory
            : beerMapper::entityToDto;
    return mapperFunction.apply(
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

  @Override
  public BeerPagedList listBeers(
      final String name,
      final String style,
      final PageRequest pageRequest,
      final Boolean showInventoryOnHand) {
    final Page<Beer> beerPage;

    if (!StringUtils.isEmpty(name) && !StringUtils.isEmpty(style)) {
      // search both
      beerPage = beerRepository.findAllByNameAndStyle(name, style, pageRequest);
    } else if (!StringUtils.isEmpty(name) && StringUtils.isEmpty(style)) {
      // search beer name
      beerPage = beerRepository.findAllByName(name, pageRequest);
    } else if (StringUtils.isEmpty(name) && !StringUtils.isEmpty(style)) {
      // search beer style
      beerPage = beerRepository.findAllByStyle(style, pageRequest);
    } else {
      beerPage = beerRepository.findAll(pageRequest);
    }

    final Function<Beer, BeerDto> mapperFunction =
        Boolean.TRUE.equals(showInventoryOnHand)
            ? beerMapper::entityToDtoWithInventory
            : beerMapper::entityToDto;

    return new BeerPagedList(
        beerPage.getContent().stream().map(mapperFunction).collect(Collectors.toList()),
        PageRequest.of(
            beerPage.getPageable().getPageNumber(), beerPage.getPageable().getPageSize()),
        beerPage.getTotalElements());
  }
}
