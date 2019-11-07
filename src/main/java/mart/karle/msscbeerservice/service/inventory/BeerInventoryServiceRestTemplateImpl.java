package mart.karle.msscbeerservice.service.inventory;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import mart.karle.msscbeerservice.config.InventoryServiceConfig;
import mart.karle.msscbeerservice.service.inventory.model.BeerInventoryDto;
import mart.karle.msscbeerservice.utils.Constants;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Log4j2
@Service
@RequiredArgsConstructor
public class BeerInventoryServiceRestTemplateImpl implements BeerInventoryService {

  private final RestTemplate restTemplate;
  private final InventoryServiceConfig inventoryServiceConfig;

  @Override
  public Integer getOnHandInventory(final UUID id) {
    log.debug("Calling inventory service");
    final Map<String, Object> uriVars = Collections.singletonMap(Constants.BEER_ID, id.toString());
    final URI uri = inventoryServiceConfig.getUri(Constants.FETCH_BEER_INVENTORY, uriVars);
    final ResponseEntity<List<BeerInventoryDto>> responseEntity =
        restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<>() {});
    return Objects.requireNonNull(responseEntity.getBody()).stream()
        .mapToInt(BeerInventoryDto::getQuantityOnHand)
        .sum();
  }
}
