package com.example.demo.src.restaurant.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetRestaurantRes {
    private int restaurantId;
    private int categoryId;
    private int minOrderPrice;
    private int reviewNumber;
    private String restaurantName;
    private String phoneNumber;
    private String address;
    private int status;
    private int favoriteNumber;
}
