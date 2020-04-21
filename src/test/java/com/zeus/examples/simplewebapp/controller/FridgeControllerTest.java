package com.zeus.examples.simplewebapp.controller;

import com.zeus.examples.simplewebapp.db.FoodItem;
import com.zeus.examples.simplewebapp.domain.FoodType;
import com.zeus.examples.simplewebapp.service.FridgeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FridgeControllerTest {

    @Mock
    private FridgeService fridgeService;

    @InjectMocks
    private FridgeController fridgeController;

    @Test
    public void getsFoodItem() {
        // Given
        var fridgeId = UUID.randomUUID();
        when(fridgeService.getFood(any(UUID.class), any(FoodType.class)))
                .thenReturn(new FoodItem(UUID.randomUUID(), fridgeId, FoodType.FOOD));

        // When
        var result = fridgeController.getFoodItem(fridgeId, FoodType.FOOD);

        // Then
        assertNotNull(result.getBody());
        assertNotNull(result.getBody().getFoodType());
        assertNotNull(result.getBody().getFridgeId());
        assertEquals(result.getBody().getFoodType(), FoodType.FOOD);
        assertEquals(result.getBody().getFridgeId(), fridgeId);
    }

    @Test
    public void storeFoodItem() {
        // Given
        var fridgeId = UUID.randomUUID();
        doCallRealMethod().when(fridgeService).storeFood(any(UUID.class), any(FoodType.class));

        // When
        var result = fridgeController.postFood(fridgeId, FoodType.FOOD);

        // Then
        assertEquals(result.getStatusCode(), HttpStatus.NO_CONTENT);
        verify(fridgeService).storeFood(fridgeId, FoodType.FOOD);
    }

    @Test
    public void removeFoodItem() {
        // Given
        var fridgeId = UUID.randomUUID();
        doCallRealMethod().when(fridgeService).removeFood(any(UUID.class), any(FoodType.class));

        // When
        var result = fridgeController.deleteFood(fridgeId, FoodType.FOOD);

        // Then
        assertEquals(result.getStatusCode(), HttpStatus.NO_CONTENT);
        verify(fridgeService).removeFood(fridgeId, FoodType.FOOD);
    }
}
