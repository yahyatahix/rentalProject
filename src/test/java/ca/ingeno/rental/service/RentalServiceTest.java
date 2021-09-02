package ca.ingeno.rental.service;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import ca.ingeno.rental.dto.filter.RequestFilter;
import ca.ingeno.rental.dto.rental.ResponseRentalByIdDto;
import ca.ingeno.rental.dto.rental.ResponseRentalDto;
import ca.ingeno.rental.entities.Rental;
import ca.ingeno.rental.repositories.RentalRepository;

@ExtendWith(MockitoExtension.class)
public class RentalServiceTest {

	private RentalService rentalService;

	@Mock
	private RentalRepository rentalRepository;

	@BeforeEach
	public void init() {
		rentalService = new RentalServiceImpl(rentalRepository, new ModelMapper());
	}

	@Test
	public void getRentalById() {
		// given
		String rentalId = "0eaf54e4-39a3-436b-9108-63ddc438a2a2";
		Rental rental = new Rental(rentalId, "Kingsey Falls", "H3U8N1", 98, 1, 1, "Dell Barczynski", 5, "description");
		when(rentalRepository.findByRentalId(rental.getRentalId())).thenReturn(Optional.of(rental));

		// when
		ResponseRentalByIdDto response = rentalService.getRentalById(rentalId);

		// then
		assertEquals(response.getPrice(), 98);
	}

	@Test
	public void getAllRentals() {
		// given
		List<Rental> rentals = new ArrayList<>();
		rentals.add(new Rental("1", "Kingsey Falls", "H3U8N1", 98, 1, 1, "Dell Barczynski", 5, "description"));
		rentals.add(new Rental("2", "Kingsey Falls", "H3U8N1", 97, 1, 1, "Dell Barczynski", 5, "description"));
		rentals.add(new Rental("3", "Kingsey Falls", "H3U8N1", 95, 1, 1, "Dell Barczynski", 5, "description"));
		rentals.add(new Rental("4", "Kingsey Falls", "H3U8N1", 93, 1, 1, "Dell Barczynski", 5, "description"));
		rentals.add(new Rental("5", "Kingsey Falls", "H3U8N1", 90, 1, 1, "Dell Barczynski", 5, "description"));
		when(rentalRepository.findAll()).thenReturn(rentals);
		// when

		List<ResponseRentalDto> dtos = rentalService.getAllRentals();
		// then

		assertThat(dtos.size(), is(5));
		assertThat(dtos, containsInAnyOrder(
				new ResponseRentalDto("1", "H3U8N1", 98, 1, 5),
				new ResponseRentalDto("2", "H3U8N1", 97, 1, 5),
				new ResponseRentalDto("3", "H3U8N1", 95, 1, 5),
				new ResponseRentalDto("4", "H3U8N1", 93, 1, 5),
				new ResponseRentalDto("5", "H3U8N1", 90, 1, 5)
				));
	}
	
	@Test
	public void getRentalsByFilter() {
		//Given
		List<Rental> rentals = new ArrayList<>();
		rentals.add(new Rental("1", "Kingsey Falls", "H3U8N1", 98, 1, 1, "Dell Barczynski", 5, "description"));
		rentals.add(new Rental("2", "Kingsey Falls", "H3U8N1", 97, 4, 1, "Dell Barczynski", 5, "description"));
		rentals.add(new Rental("3", "Kingsey Falls", "H3U8N1", 95, 5, 1, "Dell Barczynski", 5, "description"));
		rentals.add(new Rental("4", "Kingsey Falls", "H3U8N1", 93, 3, 1, "Dell Barczynski", 5, "description"));
		rentals.add(new Rental("5", "Kingsey Falls", "H3U8N1", 90, 6, 1, "Dell Barczynski", 5, "description"));
		RequestFilter filter = new RequestFilter(2, "H____", null, 100);
		when(rentalRepository.findRentalsByFilters(filter.getMinNbBeds(), filter.getPostalCode(), filter.getMinPrice(), filter.getMaxPrice()))
		.thenReturn(Arrays.asList(new Rental("1", "Kingsey Falls", "H3U8N1", 98, 1, 1, "Dell Barczynski", 5, "description")));
		
		//When
		List<ResponseRentalDto> dtos = rentalService.getRentalsByFilter(filter);
		
		//Then
		assertThat(dtos.size(), is(1));
	}

}
