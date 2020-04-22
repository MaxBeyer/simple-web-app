package com.zeus.examples.simplewebapp.acceptance;

import com.zeus.examples.simplewebapp.db.FoodItem;
import com.zeus.examples.simplewebapp.db.FoodItemRepository;
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

        String fridgeId1 = "Test Fridge";
        String fridgeId2 = "Beer Fridge";
        UUID foodItemId1 = UUID.randomUUID();
        UUID foodItemId2 = UUID.randomUUID();
        UUID foodItemId3 = UUID.randomUUID();
        FoodItem request1 = new FoodItem(foodItemId1, "Test Fridge", FoodType.FOOD);
        FoodItem request2 = new FoodItem(foodItemId1, "Beer Fridge", FoodType.FOOD);

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

            // repo not empty
            assertTrue(foodItemRepository.findById(foodItemId1).isPresent());
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
