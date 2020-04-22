package com.zeus.examples.simplewebapp.db;

import com.zeus.examples.simplewebapp.domain.FoodType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FoodItemRepository extends JpaRepository<FoodItem, UUID> {
    Integer countByFridgeNameAndFoodType(String fridgeName, FoodType foodType);
}
