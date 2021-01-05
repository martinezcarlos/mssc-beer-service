package guru.sfg.brewery.service.config.service;

import lombok.Setter;
import org.springframework.util.CollectionUtils;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Setter
public abstract class ServiceConfig {
  private String scheme;
  private String host;
  private String port;
  private String baseContext;
  private String version;
  private Map<String, String> endpoints;

  private UriComponents getUriComponents(
      final String endpointName,
      final Map<String, Object> uriVariables,
      final Map<String, Object> queryParams) {
    final UriComponentsBuilder uriComponentsBuilder =
        UriComponentsBuilder.newInstance()
            .scheme(scheme)
            .host(host)
            .port(port)
            .pathSegment(
                baseContext,
                version,
                Objects.isNull(endpoints) ? null : endpoints.get(endpointName))
            .uriVariables(Optional.ofNullable(uriVariables).orElse(Collections.emptyMap()));
    if (!CollectionUtils.isEmpty(queryParams)) {
      queryParams.forEach(
          (k, v) -> {
            if (v instanceof Collection) {
              uriComponentsBuilder.queryParam(k, ((Collection) v).toArray());
            } else {
              uriComponentsBuilder.queryParam(k, v);
            }
          });
    }
    return uriComponentsBuilder.build();
  }

  public final URI getUri(
      final String endpointName,
      final Map<String, Object> uriVariables,
      final Map<String, Object> queryParams) {
    return getUriComponents(endpointName, uriVariables, queryParams).toUri();
  }

  public final URI getUri(final String endpointName, final Map<String, Object> uriVariables) {
    return getUri(endpointName, uriVariables, null);
  }

  public final URI getUri(final String endpointName) {
    return getUri(endpointName, null);
  }

  public final URI getUri() {
    return getUri(null);
  }

  public final String getUriString(
      final String endpointName,
      final Map<String, Object> uriVariables,
      final Map<String, Object> queryParams) {
    return getUriComponents(endpointName, uriVariables, queryParams).toUriString();
  }

  public final String getUriString(
      final String endpointName, final Map<String, Object> uriVariables) {
    return getUriString(endpointName, uriVariables, null);
  }

  public final String getUriString(final String endpointName) {
    return getUriString(endpointName, null);
  }

  public final String getUriString() {
    return getUriString(null);
  }
}
