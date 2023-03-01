package pt.jorgeduarte.liquibase.domain.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pt.jorgeduarte.liquibase.domain.entities.Listing;
import pt.jorgeduarte.liquibase.domain.repositories.ListingRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ListingsService {
    private ListingRepository listingRepository;

    public ListingsService(ListingRepository listingRepository) {
        this.listingRepository = listingRepository;
    }
    public Listing getListingById(Long id) {
        return listingRepository.findAllById(List.of(id)).stream().limit(1).findFirst().orElseThrow(() -> new RuntimeException("Listing not found"));
    }

    public Long createListing(Listing listing) {
        listing.setCreated(LocalDateTime.now());
        listing.setUpdated(LocalDateTime.now());
        listing.setEnabled(true);
        return listingRepository.saveAll(List.of(listing)).get(0).getId();
    }

    public Listing updateListing(Listing listing, Long id) {
        Listing currentListing = listingRepository.findAllById(List.of(id)).stream().limit(1).findFirst().orElseThrow(() -> new RuntimeException("Listing not found"));
        currentListing.setTitle(listing.getTitle());
        currentListing.setDescription(listing.getDescription());
        currentListing.setPrice(listing.getPrice());
        currentListing.setUpdated(LocalDateTime.now());
        listingRepository.saveAll(List.of(currentListing));

        return currentListing;
    }

    public void deleteListing(Long id) {
        listingRepository.findAllById(List.of(id)).stream().limit(1).findFirst().orElseThrow(() -> new RuntimeException("Listing not found"));
        listingRepository.deleteAllByIdInBatch(List.of(id));
    }

    public Page<Listing> getAllListings(Pageable pageable) {
        return listingRepository.findAll(pageable);
    }

}
