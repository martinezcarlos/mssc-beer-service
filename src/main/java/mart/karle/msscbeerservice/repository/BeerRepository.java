package mart.karle.msscbeerservice.repository;

import mart.karle.msscbeerservice.domain.Beer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;
import java.util.UUID;

public interface BeerRepository extends PagingAndSortingRepository<Beer, UUID> {
  Page<Beer> findAllByNameAndStyle(String name, String style, PageRequest pageRequest);

  Page<Beer> findAllByName(String name, PageRequest pageRequest);

  Page<Beer> findAllByStyle(String style, PageRequest pageRequest);

  Optional<Beer> findByUpc(String upc);
}
