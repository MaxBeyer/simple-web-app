package com.zeus.examples.simplewebapp.db;

import com.zeus.examples.simplewebapp.domain.FoodType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
@Getter @Setter @NoArgsConstructor @EqualsAndHashCode
public class FoodItem {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Integer id;
    @Column(name = "fidge_id")
    private Integer fridgeId;
    @Enumerated(EnumType.STRING)
    private FoodType foodType;

    public FoodItem(Integer fridgeId, FoodType foodType, Integer quantity){
        this.fridgeId = fridgeId;
        this.foodType = foodType;
    }
}
