package guru.sfg.brewery.msscbeerservice.service.inventory;

import java.util.UUID;

public interface BeerInventoryService {
  Integer getOnHandInventory(UUID id);
}
