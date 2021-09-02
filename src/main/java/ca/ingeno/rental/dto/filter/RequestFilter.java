package ca.ingeno.rental.dto.filter;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RequestFilter {

    private Integer minNbBeds;

    private String postalCode;

    private Integer minPrice;

    private Integer maxPrice;
}