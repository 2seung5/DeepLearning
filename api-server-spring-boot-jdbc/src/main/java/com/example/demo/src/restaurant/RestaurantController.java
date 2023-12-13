package com.example.demo.src.restaurant;

import com.example.demo.src.restaurant.model.GetRestaurantRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.restaurant.model.*;
import com.example.demo.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


import static com.example.demo.config.BaseResponseStatus.*;
import static com.example.demo.utils.ValidationRegex.isRegexEmail;

@RestController
@RequestMapping("/app/restaurants")
public class RestaurantController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final RestaurantProvider restaurantProvider;
    @Autowired
    private final RestaurantService restaurantService;
    @Autowired
    private final JwtService jwtService;

    public RestaurantController(RestaurantProvider restaurantProvider, RestaurantService restaurantService, JwtService jwtService){
        this.restaurantProvider = restaurantProvider;
        this.restaurantService = restaurantService;
        this.jwtService = jwtService;
    }

    /**
     * 특정 가게 조회 API
     * [GET] / restaurants
     * [GET] /restaurants? Email=
     * @return BaseResponse<List<GetRestaurantRes>>
     */
    //Query String
    @ResponseBody
    @GetMapping("{restaurantId") // (GET) 127.0.0.1:9000/app/restaurants
    public BaseResponse<GetRestaurantRes> getRestaurant(@PathVariable("restaurantId") int restaurantId) throws BaseException {
        try{

                GetRestaurantRes getRestaurantRes = restaurantProvider.getRestaurant(restaurantId);
                return new BaseResponse<>(getRestaurantRes);

        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

}
