package guru.sfg.brewery.service.service.inventory;

import java.util.UUID;

public interface BeerInventoryService {
  Integer getOnHandInventory(UUID id);
}
