package com.zeus.examples.simplewebapp.db;

import com.zeus.examples.simplewebapp.domain.FoodType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FoodItemRepositoryTest {

    @Autowired
    public FoodItemRepository foodItemRepository;

    @Test
    public void findByFridgeIdAndFoodTypeTest(){
        // Given
        var food1 = new FoodItem(1, FoodType.FOOD, 1);
        var food2 = new FoodItem(1, FoodType.FOOD, 1);
        foodItemRepository.save(food1);
        foodItemRepository.save(food2);

        // When
        List<FoodItem> retrievedFood = foodItemRepository.findByFridgeIdAndFoodType(1, FoodType.FOOD);

        // Then
        Assertions.assertNotNull(retrievedFood);
        Assertions.assertFalse(retrievedFood.isEmpty());
        Assertions.assertEquals(food1, retrievedFood.get(0));
    }

}