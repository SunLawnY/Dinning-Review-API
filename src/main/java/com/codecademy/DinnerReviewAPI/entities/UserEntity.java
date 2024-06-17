package com.codecademy.DinnerReviewAPI.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(unique = true)
    @NonNull
    String name;

    @Column(name = "CITY")
    String city;

    @Column(name = "STATE")
    String state;

    @Column(name = "ZIPCODE")
    String zipcode;

    @Column(name = "ALLERGIES_PEANUT")
    Boolean allergiesPeanut;

    @Column(name = "ALLERGIES_EGG")
    Boolean allergiesEgg;

    @Column(name = "ALLERGIES_DAIRY")
    Boolean allergiesdairy;
}
