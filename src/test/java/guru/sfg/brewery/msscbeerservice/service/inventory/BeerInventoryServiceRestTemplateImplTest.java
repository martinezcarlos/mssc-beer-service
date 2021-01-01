package guru.sfg.brewery.msscbeerservice.service.inventory;

import java.util.UUID;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled
@SpringBootTest
class BeerInventoryServiceRestTemplateImplTest {

  private static final UUID BEER_1_UUID = UUID.fromString("0a818933-087d-47f2-ad83-2f986ed087eb");

  @Autowired private BeerInventoryService beerInventoryService;

  @Test
  void getOnHandInventory() {
    // Given
    // When
    final Integer onHandInventory = beerInventoryService.getOnHandInventory(BEER_1_UUID);
    // Then
    assertThat(onHandInventory).isGreaterThan(0);
  }
}
