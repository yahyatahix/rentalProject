package ca.ingeno.rental.service;

import ca.ingeno.rental.dto.filter.RequestFilter;
import ca.ingeno.rental.dto.rental.ResponseRentalByIdDto;
import ca.ingeno.rental.dto.rental.ResponseRentalDto;

import java.util.List;

public interface RentalService {

    public List<ResponseRentalDto> getAllRentals();

    public ResponseRentalByIdDto getRentalById(String rentalId);

    public List<ResponseRentalDto> getRentalsByFilter(RequestFilter requestFilterRentalsDto);
}
