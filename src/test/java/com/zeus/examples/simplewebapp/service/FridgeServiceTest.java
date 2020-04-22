package com.zeus.examples.simplewebapp.service;

import com.zeus.examples.simplewebapp.db.FoodItem;
import com.zeus.examples.simplewebapp.db.FoodItemRepository;
import com.zeus.examples.simplewebapp.domain.FoodType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FridgeServiceTest {

    @Mock
    FoodItemRepository foodItemRepository;

    @InjectMocks
    private FridgeService fridgeService = new FridgeService();

    @Test
    void getFood() {
        // Given
        UUID foodItemId = UUID.randomUUID();
        FoodItem foodItem = new FoodItem(foodItemId, "Test Fridge", FoodType.FOOD);
        when(foodItemRepository.findById(any())).thenReturn(Optional.of(foodItem));

        // When
        var result = fridgeService.getFood(foodItemId);

        // Then
        assertNotNull(result);
        assertTrue(result.isPresent());
        assertThat(result.get()).isEqualToComparingFieldByField(foodItem);
        verify(foodItemRepository, times(1)).findById(foodItemId);
    }

    @Test
    void noFoodToGet() {
        // Given
        UUID foodItemId = UUID.randomUUID();
        when(foodItemRepository.findById(any())).thenReturn(null);

        // When
        var result = fridgeService.getFood(foodItemId);

        // Then
        assertNull(result);
        verify(foodItemRepository, times(1)).findById(foodItemId);
    }

    @Test
    void storeFood() {
        // Given
        UUID foodItemId = UUID.randomUUID();
        FoodItem foodItem = new FoodItem(foodItemId, "Test Fridge", FoodType.FOOD);
        when(foodItemRepository.save(any())).thenReturn(foodItem);

        // When
        var result = fridgeService.storeFood(foodItem);

        // Then
        assertNotNull(result);
        assertThat(result).isEqualToComparingFieldByField(foodItem);
        verify(foodItemRepository, times(1)).save(foodItem);
    }

    @Test
    void removeFood() {
        // Given
        UUID foodItemId = UUID.randomUUID();
        FoodItem foodItem = new FoodItem(foodItemId, "Test Fridge", FoodType.FOOD);

        // When
        fridgeService.removeFood(foodItemId);

        // Then
        verify(foodItemRepository, times(1)).deleteById(any());
    }
}