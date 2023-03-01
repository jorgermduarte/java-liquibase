package pt.jorgeduarte.liquibase.app.mappers;

import org.mapstruct.Mapper;
import pt.jorgeduarte.liquibase.app.contracts.listings.ListingContract;
import pt.jorgeduarte.liquibase.domain.entities.Listing;

@Mapper(config = GlobalMapperConfig.class)
public interface ListingMapper {
    Listing mapToListing(ListingContract listingContract);
    ListingContract mapToListingContract(Listing listing);
}