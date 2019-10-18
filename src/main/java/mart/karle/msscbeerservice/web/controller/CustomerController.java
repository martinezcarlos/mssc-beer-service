package mart.karle.msscbeerservice.web.controller;

import mart.karle.msscbeerservice.web.command.CustomerDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(CustomerController.BASE_URL)
public class CustomerController {
  static final String BASE_URL = "/api/v1/customers";

  @GetMapping("/{id}")
  public ResponseEntity<CustomerDto> getCustomerById(@PathVariable final UUID id) {
    return ResponseEntity.status(HttpStatus.OK).body(CustomerDto.builder().name("Test").build());
  }

  @PostMapping
  public ResponseEntity<CustomerDto> saveNewCustomer(@RequestBody final CustomerDto customerDto) {
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> updateCustomer(
      @PathVariable final UUID id, @RequestBody final CustomerDto customerDto) {
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCustomer(@PathVariable final UUID id) {
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
