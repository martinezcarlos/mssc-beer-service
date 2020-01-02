package mart.karle.msscbeerservice.repository;

import java.util.Optional;
import java.util.UUID;
import mart.karle.msscbeerservice.domain.Beer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeerRepository extends JpaRepository<Beer, UUID> {
  Page<Beer> findAllByNameAndStyle(String name, String style, PageRequest pageRequest);

  Page<Beer> findAllByName(String name, PageRequest pageRequest);

  Page<Beer> findAllByStyle(String style, PageRequest pageRequest);

  Optional<Beer> findByUpc(String upc);
}
