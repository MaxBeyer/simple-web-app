package com.zeus.examples.simplewebapp.controller;

import com.zeus.examples.simplewebapp.db.FoodItem;
import com.zeus.examples.simplewebapp.domain.FoodType;
import com.zeus.examples.simplewebapp.service.FridgeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/fridge")
public class FridgeController {
    @Autowired
    FridgeService fridgeService;

    @GetMapping("/{fridgeId}/food/{foodType}")
    public ResponseEntity<FoodItem> getFoodItem(@PathVariable UUID fridgeId, @PathVariable FoodType foodType) {
        log.info("getting " + foodType.toString() + " from fridge: " + fridgeId.toString());
        FoodItem foodItem = fridgeService.getFood(fridgeId, foodType);
        return ResponseEntity.ok(foodItem);
    }

    @PostMapping("/{fridgeId}/food/{foodType}")
    public ResponseEntity<Void> postFood(@PathVariable UUID fridgeId, @PathVariable FoodType foodType) {
        log.info("putting " + foodType.toString() + " into fridge: " + fridgeId.toString());
        fridgeService.storeFood(fridgeId, foodType);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{fridgeId}/food/{foodType}")
    public ResponseEntity<Void> deleteFood(@PathVariable UUID fridgeId, @PathVariable FoodType foodType) {
        log.info("removing "+ foodType.toString()+ " from fridge: " + fridgeId.toString());
        fridgeService.removeFood(fridgeId, foodType);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
