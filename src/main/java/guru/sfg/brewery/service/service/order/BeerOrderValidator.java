package guru.sfg.brewery.service.service.order;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Component;

import guru.sfg.brewery.model.BeerOrderDto;
import guru.sfg.brewery.service.repository.BeerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
@RequiredArgsConstructor
public class BeerOrderValidator {
  private final BeerRepository beerRepository;

  public Boolean validateOrder(BeerOrderDto beerOrderDto) {
    final AtomicInteger beersNotFound = new AtomicInteger();
    beerOrderDto
        .getBeerOrderLines()
        .forEach(
            beerOrderLineDto -> {
              if (beerRepository.findByUpc(beerOrderLineDto.getUpc()).isEmpty()) {
                beersNotFound.incrementAndGet();
              }
            });
    return beersNotFound.get() == 0;
  }
}
