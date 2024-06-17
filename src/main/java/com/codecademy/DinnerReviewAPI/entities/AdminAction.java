package com.codecademy.DinnerReviewAPI.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;


public class AdminAction {
    @Column(name = "APPROVAL")
    Boolean adminAccept;
}
