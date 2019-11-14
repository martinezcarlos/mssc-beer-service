package mart.karle.msscbeerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.artemis.ArtemisAutoConfiguration;

@SpringBootApplication(exclude = ArtemisAutoConfiguration.class)
public class BeerServiceApp {

  public static void main(final String[] args) {
    SpringApplication.run(BeerServiceApp.class, args);
  }
}
