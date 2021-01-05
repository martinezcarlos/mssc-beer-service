package guru.sfg.brewery.service.web.mapper;

import guru.sfg.brewery.service.domain.Beer;
import guru.sfg.brewery.model.BeerDto;
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
