package mart.karle.msscbeerservice.config.service;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(value = "services.inventory", ignoreUnknownFields = false)
public class InventoryServiceConfig extends ServiceConfig {}
