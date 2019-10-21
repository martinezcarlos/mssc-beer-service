package mart.karle.msscbeerservice.bootstrap;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import mart.karle.msscbeerservice.domain.Beer;
import mart.karle.msscbeerservice.repository.BeerRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Log4j2
@Component
@RequiredArgsConstructor
public class BeerLoader implements ApplicationRunner {

  private final BeerRepository beerRepository;

  @Override
  public void run(final ApplicationArguments args) {
    loadBeers();
  }

  private void loadBeers() {
    if (beerRepository.count() == 0) {
      log.info("Loading beers into database");
      beerRepository.save(
          Beer.builder()
              .name("Mango Bobs")
              .style("IPA")
              .quantityToBrew(200)
              .minOnHand(12)
              .upc(33781998239L)
              .price(BigDecimal.valueOf(12.95))
              .build());
      beerRepository.save(
          Beer.builder()
              .name("Galaxy Cat")
              .style("PALE_ALE")
              .quantityToBrew(200)
              .minOnHand(12)
              .upc(33781998432L)
              .price(BigDecimal.valueOf(11.95))
              .build());
      log.info("Loaded beers: {}", beerRepository.count());
    }
  }
}
