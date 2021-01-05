package guru.sfg.brewery.model.events;

import java.util.UUID;

import guru.sfg.brewery.model.BeerOrderDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ValidateOrderResult {
  private UUID orderId;
  private Boolean isValid;
}
