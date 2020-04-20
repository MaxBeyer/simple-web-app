package com.zeus.examples.simplewebapp.db;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter @Setter @NoArgsConstructor @EqualsAndHashCode
public class Fridge implements Serializable {
    @Id
    private UUID id;
    private String fridgeName;
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JoinColumn(
            name = "fridge_id"
    )
    private List<FoodItem> foodItems;

    public Fridge(UUID id, String fridgeName){
        this.id = id;
        this.fridgeName = fridgeName;
        this.foodItems = new ArrayList<>();
    }
}
