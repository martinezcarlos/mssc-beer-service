package mart.karle.msscbeerservice.web.mapper;

import mart.karle.msscbeerservice.domain.Beer;
import mart.karle.msscbeerservice.web.model.BeerDto;
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
}
