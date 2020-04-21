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

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
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
        var foodItemId = UUID.randomUUID();
        var fridgeId = UUID.randomUUID();
        var foodItem= new FoodItem(foodItemId, fridgeId, FoodType.FOOD);
        when(fridgeService.getFood(any()))
                .thenReturn(Optional.of(foodItem));

        // When
        var result = fridgeController.getFoodItem(foodItemId);

        // Then
        assertNotNull(result.getBody());
        assertEquals(result.getStatusCode(), HttpStatus.OK);
        assertThat(result.getBody()).isEqualToComparingFieldByField(new FoodItem(foodItemId, fridgeId, FoodType.FOOD));
        verify(fridgeService, times(1)).getFood(foodItemId);
    }

    @Test
    public void noFoodItemFound() {
        // Given
        var foodItemId = UUID.randomUUID();
        var fridgeId = UUID.randomUUID();
        var foodItem= new FoodItem(foodItemId, fridgeId, FoodType.FOOD);
        when(fridgeService.getFood(any()))
                .thenReturn(Optional.empty());

        // When
        var result = fridgeController.getFoodItem(foodItemId);

        // Then
        assertNull(result.getBody());
        assertEquals(result.getStatusCode(), HttpStatus.NO_CONTENT);
        verify(fridgeService, times(1)).getFood(foodItemId);
    }

    @Test
    public void storeFoodItem() {
        // Given
        var fridgeId = UUID.randomUUID();
        var foodItem = new FoodItem(UUID.randomUUID(), fridgeId, FoodType.FOOD);
        when(fridgeService.storeFood(any())).thenReturn(foodItem);

        // When
        var result = fridgeController.postFood(foodItem);

        // Then
        assertEquals(result.getStatusCode(), HttpStatus.NO_CONTENT);
        verify(fridgeService).storeFood(foodItem);
    }

    @Test
    public void removeFoodItem() {
        // Given
        var foodItemId = UUID.randomUUID();
        when(fridgeService.getFood(any())).thenReturn(Optional.of(new FoodItem(foodItemId, UUID.randomUUID(), FoodType.FOOD)));
        doNothing().when(fridgeService).removeFood(any());

        // When
        var result = fridgeController.deleteFood(foodItemId);

        // Then
        assertEquals(result.getStatusCode(), HttpStatus.NO_CONTENT);
        verify(fridgeService, times(1)).getFood(foodItemId);
        verify(fridgeService).removeFood(foodItemId);
    }
}
