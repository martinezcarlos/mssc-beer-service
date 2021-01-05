package guru.sfg.brewery.service.web.controller;

import java.net.URI;
import java.util.UUID;
import javax.validation.Valid;

import guru.sfg.brewery.model.BeerDto;
import guru.sfg.brewery.model.BeerPagedList;
import lombok.RequiredArgsConstructor;
import guru.sfg.brewery.service.service.BeerService;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping(BeerController.BASE_URL)
public class BeerController {
  static final String BASE_URL = "/api/v1/beers";
  private static final String BY_ID = "/{beerId}";
  private static final String BY_UPC = "upc/{upc}";
  private static final String CREATE = "/new";
  private static final Integer DEFAULT_PAGE_NUMBER = 0;
  private static final Integer DEFAULT_PAGE_SIZE = 25;

  private final BeerService beerService;

  @GetMapping
  public ResponseEntity<BeerPagedList> listBeers(
      @RequestParam(required = false) final Integer pageNumber,
      @RequestParam(required = false) final Integer pageSize,
      @RequestParam(required = false) final String name,
      @RequestParam(required = false) final String style,
      @RequestParam(required = false, defaultValue = "false") final Boolean showInventoryOnHand) {
    final Integer page = pageNumber == null || pageNumber < 0 ? DEFAULT_PAGE_NUMBER : pageNumber;
    final Integer size = pageSize == null || pageSize < 1 ? DEFAULT_PAGE_SIZE : pageSize;
    final BeerPagedList beerList =
        beerService.listBeers(name, style, PageRequest.of(page, size), showInventoryOnHand);
    return ResponseEntity.ok(beerList);
  }

  @GetMapping(BY_ID)
  public ResponseEntity<BeerDto> getBeerById(
      @PathVariable final UUID beerId,
      @RequestParam(required = false, defaultValue = "false") final Boolean showInventoryOnHand) {
    return ResponseEntity.ok(beerService.getById(beerId, showInventoryOnHand));
  }

  @GetMapping(BY_UPC)
  public ResponseEntity<BeerDto> getBeerByUpc(@PathVariable final String upc) {
    return ResponseEntity.ok(beerService.getByUpc(upc));
  }

  @PostMapping(CREATE)
  public ResponseEntity<Void> saveNewBeer(@Valid @RequestBody final BeerDto beerDto) {
    final BeerDto savedDto = beerService.save(beerDto);
    final URI location =
        UriComponentsBuilder.fromPath(BASE_URL + BY_ID).buildAndExpand(savedDto.getId()).toUri();
    return ResponseEntity.created(location).build();
  }

  @PutMapping(BY_ID)
  public ResponseEntity<Void> updateBeer(
      @PathVariable final UUID beerId, @Valid @RequestBody final BeerDto beerDto) {
    beerService.update(beerId, beerDto);
    final URI location =
        UriComponentsBuilder.fromPath(BASE_URL + BY_ID).buildAndExpand(beerId).toUri();
    return ResponseEntity.noContent().location(location).build();
  }

  @DeleteMapping(BY_ID)
  public ResponseEntity<Void> deleteBeer(@PathVariable final UUID beerId) {
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
