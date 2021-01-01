package guru.sfg.brewery.msscbeerservice.web.mapper;

import guru.sfg.brewery.model.BeerDto;
import guru.sfg.brewery.msscbeerservice.domain.Beer;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = DateMapper.class)
@DecoratedWith(BeerMapperDecorator.class)
public interface BeerMapper {
  @Mapping(target = "quantityOnHand", ignore = true)
  BeerDto entityToDto(Beer entity);

  @Mapping(target = "quantityToBrew", ignore = true)
  @Mapping(target = "minOnHand", ignore = true)
  Beer dtoToEntity(BeerDto dto);

  @Mapping(target = "quantityOnHand", ignore = true)
  BeerDto entityToDtoWithInventory(Beer beer);
}
