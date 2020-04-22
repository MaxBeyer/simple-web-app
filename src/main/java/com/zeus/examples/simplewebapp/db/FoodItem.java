package com.zeus.examples.simplewebapp.db;

import com.zeus.examples.simplewebapp.domain.FoodType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.util.UUID;


@Entity
@Getter @Setter @NoArgsConstructor @EqualsAndHashCode
public class FoodItem {
    @Id
    private UUID id;
    private String fridgeName;
    @Enumerated(EnumType.STRING)
    private FoodType foodType;

    public FoodItem(UUID id, String fridgeName, FoodType foodType){
        this.id = id;
        this.fridgeName = fridgeName;
        this.foodType = foodType;
    }
}
