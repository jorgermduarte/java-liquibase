package pt.jorgeduarte.liquibase.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.jorgeduarte.liquibase.domain.entities.Listing;

@Repository
public interface ListingRepository extends JpaRepository<Listing, Long> {

}
