package pt.jorgeduarte.liquibase.app.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import pt.jorgeduarte.liquibase.app.contracts.listings.ListingContract;
import pt.jorgeduarte.liquibase.app.mappers.ListingMapper;
import pt.jorgeduarte.liquibase.domain.entities.Listing;
import pt.jorgeduarte.liquibase.domain.services.ListingsService;

import java.net.URI;

@RestController
@RequestMapping("/listings")
public class ListingsController {
    private final ListingsService service;
    private final ListingMapper mapper;

    public ListingsController(ListingsService listingsService, ListingMapper mapper){
        this.service = listingsService;
        this.mapper = mapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListingContract> getListingById(@PathVariable Long id) {
        ListingContract listing = mapper.mapToListingContract(service.getListingById(id));
        return (listing != null) ? ResponseEntity.ok(listing) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<Page<ListingContract>> getListings(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size)  {
        Pageable pageRequest = PageRequest.of(page, size);
        Page<Listing> pagedResult = service.getAllListings(pageRequest);

        Page<ListingContract> contracts = pagedResult.map(mapper::mapToListingContract);

        return ResponseEntity.ok(contracts);
    }

    @PostMapping
    public ResponseEntity<Void> createListing(@RequestBody ListingContract listingContract) {
        Listing listing = mapper.mapToListing(listingContract);
        Long newListingId = service.createListing(listing);

        URI location = UriComponentsBuilder.fromPath("/listings/{id}")
                .buildAndExpand(newListingId).toUri();
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(location)
                .build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateListing(@RequestBody ListingContract listingContract, @PathVariable Long id){
        Listing listing = mapper.mapToListing(listingContract);
        service.updateListing(listing,id);
        URI location = UriComponentsBuilder.fromPath("/listings/{id}")
                .buildAndExpand(id).toUri();
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(location)
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteListing( @PathVariable Long id) {
        service.deleteListing(id);
        return ResponseEntity.ok("The listing " + id + " has been deleted");
    }

}
