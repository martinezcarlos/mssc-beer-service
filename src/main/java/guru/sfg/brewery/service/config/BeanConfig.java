package guru.sfg.brewery.service.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BeanConfig {

  @Bean
  public RestTemplate buildRestTemplate(final RestTemplateBuilder restTemplateBuilder) {
    return restTemplateBuilder.build();
  }
}
