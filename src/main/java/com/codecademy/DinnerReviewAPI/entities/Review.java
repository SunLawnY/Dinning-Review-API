package com.codecademy.DinnerReviewAPI.entities;

import com.codecademy.DinnerReviewAPI.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Setter
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    UserEntity user;

    @Setter
    @ManyToOne
    @JoinColumn(name = "RESTAURANT_ID")
    Restaurant restaurant;

    @Setter
    @Column(name = "SCORE_PEANUT")
    Integer scorePeanut;

    @Setter
    @Column(name = "SCORE_EGG")
    Integer scoreEgg;

    @Setter
    @Column(name = "SCORE_DAIRY")
    Integer scoreDairy;

    @Setter
    @Column(name = "COMMENT")
    String commentary;

    @Setter
    @Column(name = "STATUS")
    Status status;


}
