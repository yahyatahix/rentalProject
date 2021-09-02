package ca.ingeno.rental.controllers;

import ca.ingeno.rental.dto.filter.RequestFilter;
import ca.ingeno.rental.dto.rental.ResponseRentalByIdDto;
import ca.ingeno.rental.dto.rental.ResponseRentalDto;
import ca.ingeno.rental.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {

    @Autowired
    private RentalService rentalService;

    @GetMapping
    public List<ResponseRentalDto> getAllRentals(){
        return rentalService.getAllRentals();
    }

    @GetMapping("/{rentalId}")
    public ResponseRentalByIdDto getRentalById(@PathVariable("rentalId") String rentalId){
        return rentalService.getRentalById(rentalId);
    }

    @PostMapping("/filter")
    public List<ResponseRentalDto> searchByFilters(@RequestBody RequestFilter requestFilter){
        return rentalService.getRentalsByFilter(requestFilter);
    }

}
