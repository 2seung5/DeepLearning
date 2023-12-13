package com.example.demo.src.restaurant;


import com.example.demo.config.BaseException;
import com.example.demo.src.restaurant.model.*;
//import com.example.demo.src.restaurant.model.GetRestaurantRes;
import com.example.demo.utils.JwtService;
import com.example.demo.utils.SHA256;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

//Provider : Read의 비즈니스 로직 처리
@Service
public class RestaurantProvider {
    private final RestaurantDao restaurantDao;
    private final JwtService jwtService;


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public RestaurantProvider(RestaurantDao restaurantDao, JwtService jwtService) {
        this.restaurantDao = restaurantDao;
        this.jwtService = jwtService;
    }

    public GetRestaurantRes getRestaurant(int restaurantId) throws BaseException {
        try {
            GetRestaurantRes getRestaurantRes = restaurantDao.getRestaurant(restaurantId);
            return getRestaurantRes;
        } catch (Exception exception) {
            logger.error("App - getUser Provider Error", exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
