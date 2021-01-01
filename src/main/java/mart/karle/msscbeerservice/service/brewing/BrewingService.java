package mart.karle.msscbeerservice.service.brewing;

import common.jms.events.BrewBeerEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import mart.karle.msscbeerservice.config.JmsConfig;
import mart.karle.msscbeerservice.domain.Beer;
import mart.karle.msscbeerservice.repository.BeerRepository;
import mart.karle.msscbeerservice.service.inventory.BeerInventoryService;
import mart.karle.msscbeerservice.web.mapper.BeerMapper;
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
