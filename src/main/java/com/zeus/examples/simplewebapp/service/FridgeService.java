package com.zeus.examples.simplewebapp.service;

import com.zeus.examples.simplewebapp.db.FoodItem;
import com.zeus.examples.simplewebapp.db.FoodItemRepository;
import com.zeus.examples.simplewebapp.domain.FoodType;
import com.zeus.examples.simplewebapp.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class FridgeService {

    @Autowired
    FoodItemRepository foodItemRepository;

    // get a specific food item
    public Optional<FoodItem> getFood(UUID foodItemId) {
            return foodItemRepository.findById(foodItemId);
    }

    public FoodItem storeFood(FoodItem foodItem) {
        if (foodItem.getFoodType().equals(FoodType.SODA_CAN)){
            var sodaList = foodItemRepository.countByFridgeNameAndFoodType(foodItem.getFridgeName(), FoodType.SODA_CAN);
            if (sodaList >= 12) {
                throw new ApiException(HttpStatus.FORBIDDEN, "There are already 12 cans of soda in this fridge.  Try a different one.");
            }
        }
        return foodItemRepository.save(foodItem);
    }

    public void removeFood(UUID foodItemId) {
        foodItemRepository.deleteById(foodItemId);
    }
}
