package mart.karle.msscbeerservice.service.brewing;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import mart.karle.msscbeerservice.config.JmsConfig;
import mart.karle.msscbeerservice.domain.Beer;
import mart.karle.msscbeerservice.events.BeerEvent;
import mart.karle.msscbeerservice.events.BrewBeerEvent;
import mart.karle.msscbeerservice.events.NewInventoryEvent;
import mart.karle.msscbeerservice.repository.BeerRepository;
import mart.karle.msscbeerservice.web.model.BeerDto;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class BrewBeerListener {

  private final BeerRepository beerRepository;
  private final JmsTemplate jmsTemplate;

  @JmsListener(destination = JmsConfig.BREWING_REQUEST_QUEUE)
  public void listen(final BrewBeerEvent event) {
    final BeerDto beerDto = event.getBeerDto();
    final Beer one = beerRepository.getOne(beerDto.getId());
    beerDto.setQuantityOnHand(one.getQuantityToBrew());
    final BeerEvent invEvent = NewInventoryEvent.builder().beerDto(beerDto).build();
    log.debug("Brewed beer {}. New quantity {}", one.getMinOnHand(), beerDto.getQuantityOnHand());
    jmsTemplate.convertAndSend(JmsConfig.NEW_INVENTORY_QUEUE, invEvent);
  }
}
