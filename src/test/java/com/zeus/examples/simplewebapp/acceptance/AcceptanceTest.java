package com.zeus.examples.simplewebapp.acceptance;

import com.zeus.examples.simplewebapp.db.FoodItem;
import com.zeus.examples.simplewebapp.db.FoodItemRepository;
import com.zeus.examples.simplewebapp.db.Fridge;
import com.zeus.examples.simplewebapp.db.FridgeRepository;
import com.zeus.examples.simplewebapp.domain.FoodType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AcceptanceTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    FridgeRepository fridgeRepository;

    @Autowired
    FoodItemRepository foodItemRepository;

    @Nested
    @DisplayName("test the endpoints")
    class TestTheEndpoints {

        UUID fridgeId1 = UUID.fromString("f7af576b-84e4-49b0-ab00-842d9db903d1");
        UUID fridgeId2 = UUID.fromString("92f19b62-946d-4ed6-a260-fe78ef854bcc");
        UUID fridgeId3 = UUID.fromString("d685f5ca-875a-4d06-8262-81d1b0a04fea");
        UUID foodItemId1 = UUID.randomUUID();
        UUID foodItemId2 = UUID.randomUUID();
        UUID foodItemId3 = UUID.randomUUID();
        FoodItem request1 = new FoodItem(foodItemId1, fridgeId1, FoodType.FOOD);
        FoodItem request2 = new FoodItem(foodItemId1, fridgeId2, FoodType.FOOD);

        @Test
        void addFood(){
            // Given
            // food should not be in DB initially
            assertFalse(foodItemRepository.findById(foodItemId1).isPresent());

            // fridge is empty
            Fridge fridge1 = fridgeRepository.findById(fridgeId1).orElse(null);
            assertTrue(fridge1.getFoodItems().isEmpty());

            // When
            ResponseEntity<String> response = testRestTemplate.postForEntity(
                    "/fridges/",
                    request1,
                    String.class);

            // Then
            assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
            assertTrue(foodItemRepository.findById(foodItemId1).isPresent());

            // fridge not empty
//            assertFalse(fridge1.getFoodItems().isEmpty());
        }

//        @Test
//        void updateFood(){
//            // food should be in DB from last test
//            assertTrue(foodItemRepository.findById(foodItemId1).isPresent());
//            // when
//            // move food to different fridge
//            ResponseEntity<String> response = testRestTemplate.postForEntity(
//                    "/fridges/",
//                    request2,
//                    String.class);
//
//            // then
//            assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
//            assertTrue(foodItemRepository.findById(foodItemId1).isPresent());
//        }
    }
}
