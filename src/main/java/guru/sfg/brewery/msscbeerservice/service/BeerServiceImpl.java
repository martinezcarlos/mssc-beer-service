package guru.sfg.brewery.msscbeerservice.service;

import guru.sfg.brewery.model.BeerDto;
import guru.sfg.brewery.model.BeerPagedList;
import guru.sfg.brewery.msscbeerservice.domain.Beer;
import guru.sfg.brewery.msscbeerservice.repository.BeerRepository;
import guru.sfg.brewery.msscbeerservice.web.controller.NotFoundException;
import guru.sfg.brewery.msscbeerservice.web.mapper.BeerMapper;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@Log4j2
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {

  private final BeerRepository beerRepository;
  private final BeerMapper beerMapper;

  @Cacheable(cacheNames = "beerCache", key = "#beerId", condition = "#showInventoryOnHand == false")
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

  @Cacheable(cacheNames = "beerListCache", condition = "#showInventoryOnHand == false")
  @Override
  public BeerPagedList listBeers(
      final String name,
      final String style,
      final PageRequest pageRequest,
      final Boolean showInventoryOnHand) {
    log.info("Fetching beer list from the repository");
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

  @Cacheable(cacheNames = "beerUpcCache")
  @Override
  public BeerDto getByUpc(final String upc) {
    final Beer byUpc = beerRepository.findByUpc(upc).orElseThrow(NotFoundException::new);
    return beerMapper.entityToDto(byUpc);
  }
}
