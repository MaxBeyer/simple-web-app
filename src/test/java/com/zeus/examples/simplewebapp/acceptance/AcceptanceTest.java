package com.zeus.examples.simplewebapp.acceptance;

import com.zeus.examples.simplewebapp.db.FoodItem;
import com.zeus.examples.simplewebapp.db.FoodItemRepository;
import com.zeus.examples.simplewebapp.domain.FoodType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AcceptanceTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    FoodItemRepository foodItemRepository;

    @Nested
    @DisplayName("test the endpoints")
    class TestTheEndpoints {

        @BeforeEach
        public void clearDb() {
            foodItemRepository.deleteAll();
        }

        String fridgeId1 = "Test Fridge";
        String fridgeId2 = "Beer Fridge";
        UUID foodItemId1 = UUID.randomUUID();
        UUID foodItemId2 = UUID.randomUUID();
        UUID foodItemId3 = UUID.randomUUID();
        FoodItem request1 = new FoodItem(foodItemId1, "Test Fridge", FoodType.FOOD);
        FoodItem request2 = new FoodItem(foodItemId1, "Beer Fridge", FoodType.SODA_CAN);

        @Test
        void getFood(){
            // Given
            // food should not be in DB initially
            foodItemRepository.save(request1);
            assertTrue(foodItemRepository.findById(foodItemId1).isPresent());

            // When
            ResponseEntity<String> response = testRestTemplate.getForEntity(
                    "/fridges/food/{foodItemId}",
                    String.class,
                    Map.of("foodItemId", foodItemId1)
            );

            // Then
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals("{\"id\":\"" + foodItemId1.toString() + "\",\"fridgeName\":\"Test Fridge\",\"foodType\":\"FOOD\"}", response.getBody());
        }

        @Test
        void addFood(){
            // Given
            // food should not be in DB initially
            assertFalse(foodItemRepository.findById(foodItemId1).isPresent());

            // When
            ResponseEntity<String> response = testRestTemplate.postForEntity(
                    "/fridges/",
                    request1,
                    String.class);

            // Then
            assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
            assertTrue(foodItemRepository.findById(foodItemId1).isPresent());
        }

        @Test
        void updateFood(){
            // food should be in DB from last test
            foodItemRepository.save(request1);
            assertTrue(foodItemRepository.findById(foodItemId1).isPresent());

            // when
            // move food to different fridge
            ResponseEntity<String> response = testRestTemplate.postForEntity(
                    "/fridges/",
                    request2,
                    String.class);

            // then
            assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
            assertTrue(foodItemRepository.findById(foodItemId1).isPresent());
        }

        @Test
        public void deleteFood() {
            // Given
            foodItemRepository.save(request1);
            assertTrue(foodItemRepository.findById(foodItemId1).isPresent());

            // When
            foodItemRepository.deleteById(foodItemId1);

            // Then
            assertFalse(foodItemRepository.findById(foodItemId1).isPresent());
            assertEquals(0, foodItemRepository.count());
        }

        @Test
        void guardrailTest(){
            // put a 12pk in the beer fridge
            foodItemRepository.save(new FoodItem(UUID.randomUUID(), "Beer Fridge", FoodType.SODA_CAN));
            foodItemRepository.save(new FoodItem(UUID.randomUUID(), "Beer Fridge", FoodType.SODA_CAN));
            foodItemRepository.save(new FoodItem(UUID.randomUUID(), "Beer Fridge", FoodType.SODA_CAN));
            foodItemRepository.save(new FoodItem(UUID.randomUUID(), "Beer Fridge", FoodType.SODA_CAN));
            foodItemRepository.save(new FoodItem(UUID.randomUUID(), "Beer Fridge", FoodType.SODA_CAN));
            foodItemRepository.save(new FoodItem(UUID.randomUUID(), "Beer Fridge", FoodType.SODA_CAN));
            foodItemRepository.save(new FoodItem(UUID.randomUUID(), "Beer Fridge", FoodType.SODA_CAN));
            foodItemRepository.save(new FoodItem(UUID.randomUUID(), "Beer Fridge", FoodType.SODA_CAN));
            foodItemRepository.save(new FoodItem(UUID.randomUUID(), "Beer Fridge", FoodType.SODA_CAN));
            foodItemRepository.save(new FoodItem(UUID.randomUUID(), "Beer Fridge", FoodType.SODA_CAN));
            foodItemRepository.save(new FoodItem(UUID.randomUUID(), "Beer Fridge", FoodType.SODA_CAN));
            foodItemRepository.save(new FoodItem(UUID.randomUUID(), "Beer Fridge", FoodType.SODA_CAN));

            assertEquals(12, foodItemRepository.countByFridgeNameAndFoodType("Beer Fridge", FoodType.SODA_CAN));

            // when
            // add 13th can of soda
            ResponseEntity<String> response = testRestTemplate.postForEntity(
                    "/fridges/",
                    request2,
                    String.class);

            // then
            assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
            assertFalse(foodItemRepository.findById(request1.getId()).isPresent());
        }
    }
}
