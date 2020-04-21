package com.zeus.examples.simplewebapp.service;

import com.zeus.examples.simplewebapp.db.FoodItem;
import com.zeus.examples.simplewebapp.domain.FoodType;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FridgeService {
    public FoodItem getFood(UUID fridgeId, FoodType foodType) {
        return null;
    }

    public FoodItem storeFood(UUID fridgeId, FoodType foodType) {
        return null;
    }

    public void removeFood(UUID any, FoodType any1) {

    }
}
