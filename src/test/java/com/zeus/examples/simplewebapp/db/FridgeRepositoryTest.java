package com.zeus.examples.simplewebapp.db;

import com.zeus.examples.simplewebapp.domain.FoodType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FridgeRepositoryTest {

    @Autowired
    FoodItemRepository foodItemRepository;

    @Nested
    @DisplayName("save, update, delete functionality")
    class FridgeFoodRelationFunctionality {

        @BeforeEach
        public void clearDb() {
            foodItemRepository.deleteAll();
        }

        UUID foodId1 = UUID.randomUUID();
        FoodItem foodItem1 = new FoodItem(foodId1, "Beer Fridge", FoodType.FOOD);

        @Test
        public void saveFood() {
            // Given
            assertFalse(foodItemRepository.findById(foodId1).isPresent());

            // When
            foodItemRepository.save(foodItem1);

            // Then
            assertTrue(foodItemRepository.findById(foodId1).isPresent());
        }

        @Test
        public void updateFood() {
            // Given
            foodItemRepository.save(foodItem1);
            assertTrue(foodItemRepository.findById(foodId1).isPresent());
            assertEquals(1, foodItemRepository.count());
            UUID initialId = foodItemRepository.findById(foodId1).orElse(null).getId();

            // When
            FoodItem saved = foodItemRepository.save(new FoodItem(foodId1, "Test Fridge", FoodType.FOOD));

            // Then
            assertEquals(foodItem1.getId(), saved.getId());
            assertEquals(foodItem1.getFoodType(), saved.getFoodType());
            assertNotEquals(foodItem1.getFridgeName(), saved.getFridgeName());
            assertEquals(1, foodItemRepository.count());
        }

        @Test
        public void deleteFood() {
            // Given
            foodItemRepository.save(foodItem1);
            assertTrue(foodItemRepository.findById(foodId1).isPresent());

            // When
            foodItemRepository.deleteById(foodId1);

            // Then
            assertFalse(foodItemRepository.findById(foodId1).isPresent());
            assertEquals(0, foodItemRepository.count());
        }
    }

    @Test
    public void countByFridgeNameAndFoodTypeTest() {
        // Given
        FoodItem food1 = new FoodItem(UUID.randomUUID(), "Beer Fridge", FoodType.FOOD);
        FoodItem food2 = new FoodItem(UUID.randomUUID(), "Beer Fridge", FoodType.FOOD);
        foodItemRepository.save(food1);
        foodItemRepository.save(food2);

        // When
        Integer retrievedFood = foodItemRepository.countByFridgeNameAndFoodType("Beer Fridge", FoodType.FOOD);

        // Then
        assertNotNull(retrievedFood);
        assertEquals(2, retrievedFood);
    }

}