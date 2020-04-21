package com.zeus.examples.simplewebapp.service;

import com.zeus.examples.simplewebapp.db.FoodItem;
import com.zeus.examples.simplewebapp.db.FoodItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
        return foodItemRepository.save(foodItem);
    }

    public void removeFood(UUID foodItemId) {
        foodItemRepository.deleteById(foodItemId);
    }
}
