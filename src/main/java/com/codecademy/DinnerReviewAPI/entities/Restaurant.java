package com.codecademy.DinnerReviewAPI.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "NAME")
    String name;

//    @Column(name = "STREET")
//    String street;
//
//    @Column(name = "CITY")
//    String city;
//
//    @Column(name = "COUNTRY")
//    String country;

    @Column(name = "ZIPCODE")
    String zipCode;

    @Column(name = "AVERAGE_TOTAL")
    Float averageTotal;

    @Column(name = "AVERAGE_PEANUT")
    Float averagePeanutScore;

    @Column(name = "AVERAGE_EGG")
    Float averageEggScore;

    @Column(name = "AVERAGE_DAIRY")
    Float averageDairyScore;

    public void setAverageTotal() {
        this.averageTotal = (averagePeanutScore + averageEggScore + averageDairyScore)/3;
    }
}
