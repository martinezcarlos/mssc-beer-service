package guru.sfg.brewery.service.bootstrap;

import guru.sfg.brewery.service.repository.BeerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import guru.sfg.brewery.service.domain.Beer;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;

@Log4j2
@Component
@RequiredArgsConstructor
public class BeerLoader implements ApplicationRunner {

  private static final String BEER_UPC_1 = "039738647827473576";
  private static final String BEER_UPC_2 = "243785898653433783";
  private static final String BEER_UPC_3 = "897586514413236759";

  private final BeerRepository beerRepository;

  @Override
  public void run(final ApplicationArguments args) {
    loadBeers();
  }

  private void loadBeers() {
    if (beerRepository.count() == 0) {
      log.info("Loading beers into database");
      beerRepository.saveAll(
          Arrays.asList(
              Beer.builder()
                  .name("Mango Bobs")
                  .style("IPA")
                  .quantityToBrew(200)
                  .minOnHand(12)
                  .upc(BEER_UPC_1)
                  .price(BigDecimal.valueOf(12.95))
                  .build(),
              Beer.builder()
                  .name("Galaxy Cat")
                  .style("PALE_ALE")
                  .quantityToBrew(200)
                  .minOnHand(12)
                  .upc(BEER_UPC_2)
                  .price(BigDecimal.valueOf(11.95))
                  .build(),
              Beer.builder()
                  .name("Chapinero Porter")
                  .style("PORTER")
                  .quantityToBrew(150)
                  .minOnHand(12)
                  .upc(BEER_UPC_3)
                  .price(BigDecimal.valueOf(4.0))
                  .build()));
      log.info("Loaded beers: {}", beerRepository.count());
    }
  }
}
