package ca.ingeno.rental.repositories;

import ca.ingeno.rental.entities.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RentalRepository extends JpaRepository<Rental, String> {

    Optional<Rental> findByRentalId(String rentalId);

    @Query(value="select * from rentals where ( ?1 is null or nb_beds <= ?1 )  and (?2 is null or postal_code like %?2%) and (?3 is null or price >= ?3 ) and (?4 is null or price <= ?4)",
            nativeQuery = true)
    List<Rental> findRentalsByFilters(Integer minNbBeds, String postalCode, Integer minPrice, Integer maxPrice);


}
