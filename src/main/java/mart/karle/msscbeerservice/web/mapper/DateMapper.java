package mart.karle.msscbeerservice.web.mapper;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

@Component
public class DateMapper {
  public OffsetDateTime asOffsetDateTime(final Timestamp timestamp) {
    return Optional.ofNullable(timestamp)
        .map(
            ts ->
                OffsetDateTime.of(
                    ts.toLocalDateTime().getYear(),
                    ts.toLocalDateTime().getMonthValue(),
                    ts.toLocalDateTime().getDayOfMonth(),
                    ts.toLocalDateTime().getHour(),
                    ts.toLocalDateTime().getMinute(),
                    ts.toLocalDateTime().getSecond(),
                    ts.toLocalDateTime().getNano(),
                    ZoneOffset.UTC))
        .orElse(null);
  }

  public Timestamp asTimestamp(final OffsetDateTime offsetDateTime) {
    return Optional.ofNullable(offsetDateTime)
        .map(d -> Timestamp.valueOf(d.atZoneSameInstant(ZoneOffset.UTC).toLocalDateTime()))
        .orElse(null);
  }
}
