package mart.karle.msscbeerservice.web.mapper;

import mart.karle.msscbeerservice.domain.Beer;
import mart.karle.msscbeerservice.web.model.BeerDto;
import org.mapstruct.Mapper;

@Mapper(uses = DateMapper.class)
public interface BeerMapper {
  BeerDto entityToDto(Beer entity);

  Beer dtoToEntity(BeerDto dto);
}
