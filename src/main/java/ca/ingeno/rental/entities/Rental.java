package ca.ingeno.rental.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name="RENTALS")
public class Rental {

    @Id
    @NotNull(message = "Id cannot be null")
    @Column(name = "Id")
    private String rentalId;

    @Column(name = "City")
    private String city;

    @Column(name = "Postal_Code")
    private String postalCode;

    @Column(name = "Price")
    private float price;

    @Column(name = "Nb_Beds")
    private int nbBeds;

    @Column(name = "Nb_Baths")
    private int nbBaths;

    @Column(name = "Owner")
    private String owner;

    @Column(name = "Rating")
    private float rating;

    @Column(name = "Description")
    private String description;
}
