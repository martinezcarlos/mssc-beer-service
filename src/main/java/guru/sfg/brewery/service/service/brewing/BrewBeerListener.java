package guru.sfg.brewery.service.service.brewing;

import guru.sfg.brewery.model.events.BrewBeerEvent;
import guru.sfg.brewery.model.events.NewInventoryEvent;
import guru.sfg.brewery.service.config.JmsConfig;
import guru.sfg.brewery.service.domain.Beer;
import guru.sfg.brewery.service.repository.BeerRepository;
import guru.sfg.brewery.model.BeerDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@RequiredArgsConstructor
public class BrewBeerListener {

  private final BeerRepository beerRepository;
  private final JmsTemplate jmsTemplate;

  @Transactional
  @JmsListener(destination = JmsConfig.BREWING_REQUEST_QUEUE)
  public void listen(final BrewBeerEvent event) {
    final BeerDto beerDto = event.getBeerDto();
    final Beer one = beerRepository.getOne(beerDto.getId());
    beerDto.setQuantityOnHand(one.getQuantityToBrew());
    final NewInventoryEvent invEvent = new NewInventoryEvent(beerDto);
    log.debug("Brewed beer {}. New quantity {}", one.getMinOnHand(), beerDto.getQuantityOnHand());
    jmsTemplate.convertAndSend(JmsConfig.NEW_INVENTORY_QUEUE, invEvent);
  }
}
