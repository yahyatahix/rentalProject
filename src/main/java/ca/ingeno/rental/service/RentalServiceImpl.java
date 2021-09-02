package ca.ingeno.rental.service;


import ca.ingeno.rental.dto.filter.RequestFilter;
import ca.ingeno.rental.dto.rental.ResponseRentalByIdDto;
import ca.ingeno.rental.dto.rental.ResponseRentalDto;
import ca.ingeno.rental.entities.Rental;
import ca.ingeno.rental.exception.ElementNotFoundException;
import ca.ingeno.rental.repositories.RentalRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RentalServiceImpl implements RentalService {

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private ModelMapper modelMapper;

 
	public RentalServiceImpl(RentalRepository rentalRepository, ModelMapper modelMapper) {
		super();
		this.rentalRepository = rentalRepository;
		this.modelMapper = modelMapper;
	}

	@Override
    public List<ResponseRentalDto> getAllRentals() {
        List<Rental> rentals = rentalRepository.findAll();
        return mappingResults(rentals);
    }

    @Override
    public ResponseRentalByIdDto getRentalById(String rentalId) {
        Rental rental = rentalRepository.findByRentalId(rentalId)
                .orElseThrow(() -> new ElementNotFoundException("Rental with id " + rentalId));
        ResponseRentalByIdDto responseRentalByIdDto = new ResponseRentalByIdDto();
        modelMapper.map(rental, responseRentalByIdDto);
        return responseRentalByIdDto;
    }

    @Override
    public List<ResponseRentalDto> getRentalsByFilter(RequestFilter requestFilterRentalsDto) {
        List<Rental> rentals = rentalRepository.findRentalsByFilters(
        		requestFilterRentalsDto.getMinNbBeds(),
                requestFilterRentalsDto.getPostalCode(),
                requestFilterRentalsDto.getMinPrice(),
                requestFilterRentalsDto.getMaxPrice());
        return mappingResults(rentals);

    }

    private List<ResponseRentalDto> mappingResults(List<Rental> rentals){
        List<ResponseRentalDto> responseRentalDtoList = new ArrayList<>();
        for(Rental rental: rentals){
            
            responseRentalDtoList.add(modelMapper.map(rental,ResponseRentalDto.class));
        }
        return responseRentalDtoList;
    }
}
