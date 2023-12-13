package com.example.demo.src.restaurant;



import com.example.demo.src.restaurant.model.GetRestaurantRes;
import com.example.demo.src.user.model.GetUserRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class RestaurantDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public List<GetRestaurantRes> getRestaurants(){
        String getRestaurantsQuery = "select * from Restaurant";
        return this.jdbcTemplate.query(getRestaurantsQuery,
                (rs,rowNum) -> new GetRestaurantRes(
                        rs.getInt("restaurantId"),
                        rs.getInt("categoryId"),
                        rs.getInt("minOrderPrice"),
                        rs.getInt("reviewNumber"),
                        rs.getString("restaurantName"),
                        rs.getString("phoneNumber"),
                        rs.getString("address"),
                        rs.getInt("status"),
                        rs.getInt("favoriteNumber"))
        );
    }


    public GetRestaurantRes getRestaurant(int restaurantId){
        String getRestaurantQuery = "select * from Restaurant where restaurantId = ?";
        int getRestaurantParams = restaurantId;
        return this.jdbcTemplate.queryForObject(getRestaurantQuery,
                (rs, rowNum) -> new GetRestaurantRes(
                        rs.getInt("restaurantId"),
                        rs.getInt("categoryId"),
                        rs.getInt("minOrderPrice"),
                        rs.getInt("reviewNumber"),
                        rs.getString("restaurantName"),
                        rs.getString("phoneNumber"),
                        rs.getString("address"),
                        rs.getInt("status"),
                        rs.getInt("favoriteNumber")),
                getRestaurantParams);
    }

}
