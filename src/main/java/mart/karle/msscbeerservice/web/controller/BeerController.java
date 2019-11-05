package mart.karle.msscbeerservice.web.controller;

import lombok.RequiredArgsConstructor;
import mart.karle.msscbeerservice.service.BeerService;
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
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(BeerController.BASE_URL)
public class BeerController {
  static final String BASE_URL = "/api/v1/beers";
  private static final String FIND_BY_ID = "/{beerId}";

  private final BeerService beerService;

  @GetMapping(FIND_BY_ID)
  public ResponseEntity<BeerDto> getBeerById(@PathVariable final UUID beerId) {
    return ResponseEntity.ok(beerService.getById(beerId));
  }

  @PostMapping("/new")
  public ResponseEntity<BeerDto> saveNewBeer(@Valid @RequestBody final BeerDto beerDto) {
    final BeerDto savedDto = beerService.save(beerDto);
    final URI location =
        UriComponentsBuilder.fromPath(BASE_URL + FIND_BY_ID)
            .buildAndExpand(savedDto.getId())
            .toUri();
    return ResponseEntity.created(location).body(savedDto);
  }

  @PutMapping("/{beerId}")
  public ResponseEntity<Void> updateBeer(
      @PathVariable final UUID beerId, @Valid @RequestBody final BeerDto beerDto) {
    beerService.update(beerId, beerDto);
    final URI location =
        UriComponentsBuilder.fromPath(BASE_URL + FIND_BY_ID).buildAndExpand(beerId).toUri();
    return ResponseEntity.noContent().location(location).build();
  }

  @DeleteMapping("/{beerId}")
  public ResponseEntity<Void> deleteBeer(@PathVariable final UUID beerId) {
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
