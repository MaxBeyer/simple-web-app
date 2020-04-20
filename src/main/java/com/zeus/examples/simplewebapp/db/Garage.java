package com.zeus.examples.simplewebapp.db;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor @EqualsAndHashCode
public class Garage implements Serializable {
    @Id
    private Integer id;
    private String fridgeName;
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JoinColumn(
            name = "fridge_id",
            insertable = true
    )
    private List<FoodItem> foodItems;

    public Garage(String fridgeName, List<FoodItem> foodItems){
        this.fridgeName = fridgeName;
        this.foodItems = foodItems;
    }
}
