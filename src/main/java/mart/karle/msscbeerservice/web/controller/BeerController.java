package mart.karle.msscbeerservice.web.controller;

import mart.karle.msscbeerservice.web.model.BeerDto;
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

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(BeerController.BASE_URL)
public class BeerController {
  static final String BASE_URL = "/api/v1/beers";

  @GetMapping("/{beerId}")
  public ResponseEntity<BeerDto> getBeerById(@PathVariable final UUID beerId) {
    return ResponseEntity.status(HttpStatus.OK).body(BeerDto.builder().name("Test").build());
  }

  @PostMapping("/new")
  public ResponseEntity<BeerDto> saveNewBeer(@Valid @RequestBody final BeerDto beerDto) {
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PutMapping("/{beerId}")
  public ResponseEntity<Void> updateBeer(
      @PathVariable final UUID beerId, @Valid @RequestBody final BeerDto beerDto) {
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @DeleteMapping("/{beerId}")
  public ResponseEntity<Void> deleteBeer(@PathVariable final UUID beerId) {
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
