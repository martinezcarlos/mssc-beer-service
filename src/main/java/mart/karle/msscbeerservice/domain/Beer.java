package mart.karle.msscbeerservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Beer {

  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  @Type(type = "org.hibernate.type.UUIDCharType")
  @Column(length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false)
  private UUID id;

  @Version private Long version;

  @Column(updatable = false)
  @CreationTimestamp
  private Timestamp createdDate;

  @UpdateTimestamp private Timestamp lastModifiedDate;

  private String name;
  private String style;

  @Column(unique = true)
  private String upc;

  private BigDecimal price;
  private Integer minOnHand;
  private Integer quantityToBrew;
}
