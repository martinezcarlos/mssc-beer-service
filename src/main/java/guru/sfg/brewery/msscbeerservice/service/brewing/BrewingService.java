package guru.sfg.brewery.msscbeerservice.service.brewing;

import guru.sfg.brewery.model.events.BrewBeerEvent;
import guru.sfg.brewery.msscbeerservice.config.JmsConfig;
import guru.sfg.brewery.msscbeerservice.domain.Beer;
import guru.sfg.brewery.msscbeerservice.repository.BeerRepository;
import guru.sfg.brewery.msscbeerservice.service.inventory.BeerInventoryService;
import guru.sfg.brewery.msscbeerservice.web.mapper.BeerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class BrewingService {

  private final BeerRepository beerRepository;
  private final BeerInventoryService beerInventoryService;
  private final JmsTemplate jmsTemplate;
  private final BeerMapper beerMapper;

  @Scheduled(fixedRate = 5000) // Every 5 sec
  public void checkForLowInventory() {
    beerRepository.findAll().forEach(this::checkInventory);
  }

  private void checkInventory(final Beer b) {
    final Integer onHandInventory = beerInventoryService.getOnHandInventory(b.getId());
    log.debug("Min on hand is {}", b.getMinOnHand());
    log.debug("Inventory is {}", onHandInventory);
    if (b.getMinOnHand() >= onHandInventory) {
      log.info("Requesting new inventory for beer {}", b.getName());
      jmsTemplate.convertAndSend(
          JmsConfig.BREWING_REQUEST_QUEUE, new BrewBeerEvent(beerMapper.entityToDto(b)));
    }
  }
}
