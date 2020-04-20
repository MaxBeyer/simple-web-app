package com.zeus.examples.simplewebapp.db;

import com.zeus.examples.simplewebapp.domain.FoodType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter @NoArgsConstructor @EqualsAndHashCode
public class FoodItem {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Integer id;
    @Column(name = "fidge_id")
    private Integer fridgeId;
    @Enumerated(EnumType.STRING)
    private FoodType foodType;
    private Integer quantity;

    public FoodItem(Integer fridgeId, FoodType foodType, Integer quantity){
        this.fridgeId = fridgeId;
        this.foodType = foodType;
        this.quantity = quantity;
    }
}
