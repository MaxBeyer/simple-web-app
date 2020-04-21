package com.zeus.examples.simplewebapp.service;

import com.zeus.examples.simplewebapp.db.FoodItem;
import com.zeus.examples.simplewebapp.db.FoodItemRepository;
import com.zeus.examples.simplewebapp.db.Fridge;
import com.zeus.examples.simplewebapp.db.FridgeRepository;
import com.zeus.examples.simplewebapp.domain.FoodType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FridgeServiceTest {

    @Mock
    private FridgeRepository fridgeRepository;

    @Mock
    private FoodItemRepository foodItemRepository;

    @InjectMocks
    private FridgeService fridgeService = new FridgeService();

    @Test
    void getFood() {
        // Given
        UUID foodItemId = UUID.randomUUID();
        UUID fridgeId = UUID.randomUUID();
        when(foodItemRepository.findByFridgeIdAndFoodType(any(UUID.class), any(FoodType.class))).thenReturn(List.of(new FoodItem(foodItemId, fridgeId, FoodType.FOOD)));

        // When
        var result = fridgeService.getFood(fridgeId, FoodType.FOOD);

        // Then
        assertNotNull(result);
        assertThat(result).isEqualToComparingFieldByField(new FoodItem(foodItemId, fridgeId, FoodType.FOOD));
    }

    @Test
    void storeFood() {
        // Given
        UUID foodItemId = UUID.randomUUID();
        UUID fridgeId = UUID.randomUUID();
        Fridge fridge = new Fridge(UUID.randomUUID(), "test fridge");
        fridge.getFoodItems().add(new FoodItem(foodItemId, fridgeId, FoodType.FOOD));
        when(fridgeRepository.save(any(Fridge.class))).thenReturn(fridge);

        // When
        var result = fridgeService.storeFood(fridgeId, FoodType.FOOD);

        // Then
        assertNotNull(result);
        assertEquals(result.getFridgeId(), fridgeId);
        assertEquals(result.getFoodType(), FoodType.FOOD);
    }

    @Test
    void removeFood() {
        // Given
        UUID foodItemId = UUID.randomUUID();
        UUID fridgeId = UUID.randomUUID();
        FoodItem foodItem = new FoodItem(foodItemId, fridgeId, FoodType.FOOD);
        when(foodItemRepository.findByFridgeIdAndFoodType(any(UUID.class), any(FoodType.class))).thenReturn(List.of(foodItem));
        doCallRealMethod().when(foodItemRepository).delete(any(FoodItem.class));

        // When
        fridgeService.removeFood(fridgeId, FoodType.FOOD);

        // Then
        verify(foodItemRepository).delete(foodItem);
    }
}