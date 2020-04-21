package com.zeus.examples.simplewebapp.db;

import com.zeus.examples.simplewebapp.domain.FoodType;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FridgeRepositoryTest {

    @Autowired
    FoodItemRepository foodItemRepository;

    @Autowired
    FridgeRepository fridgeRepository;

    @Nested
    @DisplayName("Fridge/Food Items relation functionality")
    class FridgeFoodRelationFunctionality {
        UUID fridgeId1 = UUID.randomUUID();
        UUID foodId1 = UUID.randomUUID();
        UUID foodId2 = UUID.randomUUID();
        Fridge fridge = new Fridge(fridgeId1, "Test Fridge");
        FoodItem food1 = new FoodItem(foodId1, fridgeId1, FoodType.FOOD);
        FoodItem food2 = new FoodItem(foodId2, fridgeId1, FoodType.FOOD);

        @Test
        public void shouldSaveFoodToFridge() {
            fridge.getFoodItems().add(food1);

            // Save fridge, verify fridge is present, has exactly 1 food item, and the food item is in the food item repo
            fridgeRepository.save(fridge);

            // verify garage repo
            Fridge savedFridge = fridgeRepository.findById(fridgeId1).orElse(null);
            assertNotNull(savedFridge);
            assertEquals(fridgeId1, savedFridge.getId());
            assertEquals(1, savedFridge.getFoodItems().size());
            assertEquals(foodId1, savedFridge.getFoodItems().get(0).getId());
            assertEquals(food1.getFoodType(), savedFridge.getFoodItems().get(0).getFoodType());
            assertEquals(fridgeId1, savedFridge.getFoodItems().get(0).getFridgeId());

            // verify food item repo
            FoodItem savedFood = foodItemRepository.findById(foodId1).orElse(null);
            assertNotNull(savedFood);
            assertEquals(foodId1, savedFood.getId());
            assertEquals(food1.getFoodType(), savedFood.getFoodType());
            assertEquals(fridgeId1, savedFood.getFridgeId());

            // db cleanup
            fridgeRepository.deleteById(fridgeId1);
            fridge.getFoodItems().clear();
        }

        @Test
        public void shouldSaveMoreFoodToSameFridge() {
            fridge.getFoodItems().add(food1);
            fridgeRepository.save(fridge);

            // add more food to same fridge, save fridge, and verify the initial data is unaltered
            foodItemRepository.save(food2);

            // verify garage repo
            Fridge savedFridge = fridgeRepository.findById(fridgeId1).orElse(null);
            assertNotNull(savedFridge);
            assertEquals(fridgeId1, savedFridge.getId());
            assertEquals(2, savedFridge.getFoodItems().size());
            assertEquals(foodId1, savedFridge.getFoodItems().get(0).getId());
            assertEquals(food1.getFoodType(), savedFridge.getFoodItems().get(0).getFoodType());
            assertEquals(fridgeId1, savedFridge.getFoodItems().get(0).getFridgeId());
            assertEquals(foodId2, savedFridge.getFoodItems().get(1).getId());
            assertEquals(food2.getFoodType(), savedFridge.getFoodItems().get(1).getFoodType());
            assertEquals(fridgeId1, savedFridge.getFoodItems().get(1).getFridgeId());

            // verify food item repo
            FoodItem savedFood1 = foodItemRepository.findById(foodId1).orElse(null);
            assertNotNull(savedFood1);
            assertEquals(foodId1, savedFood1.getId());
            assertEquals(food1.getFoodType(), savedFood1.getFoodType());
            assertEquals(fridgeId1, savedFood1.getFridgeId());
            FoodItem savedFood2 = foodItemRepository.findById(foodId2).orElse(null);
            assertNotNull(savedFood2);
            assertEquals(foodId2, savedFood2.getId());
            assertEquals(food2.getFoodType(), savedFood2.getFoodType());
            assertEquals(fridgeId1, savedFood1.getFridgeId());

            // db cleanup
            fridgeRepository.deleteById(fridgeId1);
            fridge.getFoodItems().clear();
        }

        @Test
        public void findByFridgeIdAndFoodTypeTest(){
            // Given
            UUID fridgeId = UUID.randomUUID();
            Fridge fridge = new Fridge(fridgeId, "test fridge");
            fridgeRepository.save(fridge);
            UUID id1 = UUID.randomUUID();
            UUID id2 = UUID.randomUUID();
            FoodItem food1 = new FoodItem(id1, fridgeId, FoodType.FOOD);
            FoodItem food2 = new FoodItem(id2, fridgeId, FoodType.FOOD);
            foodItemRepository.save(food1);
            foodItemRepository.save(food2);

            // When
            List<FoodItem> retrievedFood = foodItemRepository.findByFridgeIdAndFoodType(fridgeId, FoodType.FOOD);

            // Then
            Assertions.assertNotNull(retrievedFood);
            Assertions.assertFalse(retrievedFood.isEmpty());
            Assertions.assertEquals(food1, retrievedFood.get(0));
        }

        @Test
        public void deletingFridgeAlsoDeletesFood() {
            fridge.getFoodItems().add(food1);
            fridge.getFoodItems().add(food2);
            fridgeRepository.save(fridge);

            // verify the fridge is saved
            Fridge savedFridge = fridgeRepository.findById(fridgeId1).orElse(null);
            assertNotNull(savedFridge);

            //verify food in foodItemRepo
            FoodItem savedFood = foodItemRepository.findById(foodId1).orElse(null);
            assertNotNull(savedFood);

            //delete fridge
            fridgeRepository.deleteById(fridgeId1);

            FoodItem deletedFood = foodItemRepository.findById(foodId1).orElse(null);
            assertNull(deletedFood);
        }
    }

}