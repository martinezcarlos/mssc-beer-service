package guru.sfg.brewery.msscbeerservice.repository;

import guru.sfg.brewery.msscbeerservice.domain.Beer;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeerRepository extends JpaRepository<Beer, UUID> {
  Page<Beer> findAllByNameAndStyle(String name, String style, PageRequest pageRequest);

  Page<Beer> findAllByName(String name, PageRequest pageRequest);

  Page<Beer> findAllByStyle(String style, PageRequest pageRequest);

  Optional<Beer> findByUpc(String upc);
}
