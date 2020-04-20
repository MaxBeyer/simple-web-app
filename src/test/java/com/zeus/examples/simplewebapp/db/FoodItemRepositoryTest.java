package com.zeus.examples.simplewebapp.db;

import com.zeus.examples.simplewebapp.domain.FoodType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FoodItemRepositoryTest {

    @Autowired
    public FoodItemRepository foodItemRepository;

    private FoodItem foodItemEntity(){
        return new FoodItem(1, FoodType.FOOD, 1);
    }

    @Test
    public void findByFridgeIdAndFoodTypeTest(){
        // Given
        FoodItem food = foodItemEntity();
        foodItemRepository.save(food);

        // When
        FoodItem retrievedFood = foodItemRepository.findByFridgeIdAndFoodType(1, FoodType.FOOD);

        // Then
        assertNotNull(retrievedFood);
        assertEquals(food, retrievedFood);
    }

}