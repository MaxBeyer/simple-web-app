package com.zeus.examples.simplewebapp.controller;

import com.zeus.examples.simplewebapp.db.FoodItem;
import com.zeus.examples.simplewebapp.domain.FoodType;
import com.zeus.examples.simplewebapp.service.FridgeService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/fridges")
public class FridgeController {
    @Autowired
    FridgeService fridgeService;

    @GetMapping("/food/{foodType}")
    public ResponseEntity<FoodItem> getFoodItem(@PathVariable UUID foodItemId) {
        log.info("getting food: " + foodItemId.toString() + " from the fridges...");
        Optional<FoodItem> foodItem = fridgeService.getFood(foodItemId);
        if (foodItem.isPresent()){
            log.info("foodItem: " + foodItemId.toString() + " found.");
            return ResponseEntity.ok(foodItem.get());
        }
        log.info("foodItem: " + foodItemId.toString() + " is not in any fridge.");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ApiOperation("Add or update food in any fridge using a foodItem UUID")
    @PostMapping
    public ResponseEntity<Void> postFood(@RequestBody FoodItem foodItem) {
        log.info("putting " + foodItem.getFoodType().toString() + " into fridge: " + foodItem.getFridgeId().toString() + "...");
        fridgeService.storeFood(foodItem);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{fridgeId}/food/{foodType}")
    public ResponseEntity<Void> deleteFood(@PathVariable UUID foodItemId) {
        log.info("removing foodItem "+ foodItemId.toString()+ " from the fridges...");
        if (getFoodItem(foodItemId).getStatusCode().equals(HttpStatus.OK)){
            fridgeService.removeFood(foodItemId);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
