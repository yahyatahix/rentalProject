package ca.ingeno.rental.dto.rental;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ResponseRentalByIdDto {

	@JsonProperty(value = "city")
    private String city;

    @JsonProperty(value = "postalcode")
    private String postalCode;

    @JsonProperty(value = "price")
    private float price;

    @JsonProperty(value = "nb_beds")
    private int nbBeds;

    @JsonProperty(value = "nb_baths")
    private int nbBaths;

    @JsonProperty(value = "owner")
    private String owner;

    @JsonProperty(value = "rating")
    private float rating;

    @JsonProperty(value = "description")
    private String description;
}
