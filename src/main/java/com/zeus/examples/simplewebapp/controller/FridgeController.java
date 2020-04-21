package com.zeus.examples.simplewebapp.controller;

import com.zeus.examples.simplewebapp.db.FoodItem;
import com.zeus.examples.simplewebapp.domain.FoodType;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fridge")
public class FridgeController {

    public FoodItem getFoodItem(FoodType foodType) {
        return null;
    }

    public void postFood(UUID fridgeId, FoodType foodType) {

    }

    public void deleteFood(UUID fridgeId, FoodType food) {
    }
}
