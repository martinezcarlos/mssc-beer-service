package mart.karle.msscbeerservice.web.mapper;

import mart.karle.msscbeerservice.domain.Beer;
import mart.karle.msscbeerservice.service.inventory.BeerInventoryService;
import mart.karle.msscbeerservice.web.model.BeerDto;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BeerMapperDecorator implements BeerMapper {

  // Constructor-based dependency injection is not supported by MapStruct.
  @Autowired private BeerMapper beerMapper;
  @Autowired private BeerInventoryService beerInventoryService;

  @Override
  public BeerDto entityToDto(final Beer entity) {
    return beerMapper.entityToDto(entity);
  }

  @Override
  public BeerDto entityToDtoWithInventory(final Beer entity) {
    final BeerDto beerDto = beerMapper.entityToDto(entity);
    beerDto.setQuantityOnHand(beerInventoryService.getOnHandInventory(entity.getId()));
    return beerDto;
  }

  @Override
  public Beer dtoToEntity(final BeerDto dto) {
    return beerMapper.dtoToEntity(dto);
  }
}
