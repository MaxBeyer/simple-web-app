package com.zeus.examples.simplewebapp.controller;

import com.zeus.examples.simplewebapp.db.FoodItem;
import com.zeus.examples.simplewebapp.domain.FoodType;
import com.zeus.examples.simplewebapp.service.FridgeService;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class FridgeControllerTest {

    private FridgeController fridgeController;
    private FridgeService fridgeService = mock(FridgeService.class);

    @Test
    public void getsFoodItem() {
        // Given
        when(fridgeService.getFood(any()))
                .thenReturn(new FoodItem(UUID.randomUUID(), UUID.randomUUID(), FoodType.FOOD));

        // When
        var result = fridgeController.getFoodItem(FoodType.FOOD);

        // Then
        assertEquals(result.getFoodType(), FoodType.FOOD);
    }

    @Test
    public void storeFoodItem() {
        // Given
        var fridgeId = UUID.randomUUID();
        doCallRealMethod().when(fridgeService).storeFood(any(UUID.class), any(FoodType.class));

        // When
        fridgeController.postFood(fridgeId, FoodType.FOOD);

        // Then
        verify(fridgeService).storeFood(fridgeId, FoodType.FOOD);
    }

    @Test
    public void removeFoodItem() {
        // Given
        var fridgeId = UUID.randomUUID();
        doCallRealMethod().when(fridgeService).removeFood(any(UUID.class), any(FoodType.class));

        // When
        fridgeController.deleteFood(fridgeId, FoodType.FOOD);

        // Then
        verify(fridgeService).removeFood(fridgeId, FoodType.FOOD);
    }
}
