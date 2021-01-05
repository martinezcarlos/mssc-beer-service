package guru.sfg.brewery.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BeerDto extends BaseItem implements Serializable {
  private static final long serialVersionUID = -7110563199250095280L;
  @NotBlank private String name;
  @NotNull private BeerStyleEnum style;
  @NotNull private String upc;

  @NotNull
  @Positive
  @JsonFormat(shape = JsonFormat.Shape.STRING)
  private BigDecimal price;

  private Integer quantityOnHand;
}
