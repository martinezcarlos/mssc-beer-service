package guru.sfg.brewery.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BeerDto implements Serializable {
  private static final long serialVersionUID = -7110563199250095280L;

  @Null private UUID id;
  @Null private Integer version;

  @Null
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ", shape = JsonFormat.Shape.STRING)
  private OffsetDateTime createdDate;

  @Null
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ", shape = JsonFormat.Shape.STRING)
  private OffsetDateTime lastModifiedDate;

  @NotBlank private String name;
  @NotNull private BeerStyleEnum style;
  @NotNull private String upc;

  @NotNull
  @Positive
  @JsonFormat(shape = JsonFormat.Shape.STRING)
  private BigDecimal price;

  private Integer quantityOnHand;
}
