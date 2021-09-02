package ca.ingeno.rental.dto.rental;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ResponseRentalDto {

    @JsonProperty(value = "id")
    private String rentalId;

    @JsonProperty(value = "postalcode")
    private String postalCode;

    @JsonProperty(value = "price")
    private float price;

    @JsonProperty(value = "nb_beds")
    private int nbBeds;

    @JsonProperty(value = "rating")
    private float rating;

	@Override
	public int hashCode() {
		return Objects.hash(nbBeds, postalCode, price, rating, rentalId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResponseRentalDto other = (ResponseRentalDto) obj;
		return nbBeds == other.nbBeds && Objects.equals(postalCode, other.postalCode)
				&& Float.floatToIntBits(price) == Float.floatToIntBits(other.price)
				&& Float.floatToIntBits(rating) == Float.floatToIntBits(other.rating)
				&& Objects.equals(rentalId, other.rentalId);
	}
}
