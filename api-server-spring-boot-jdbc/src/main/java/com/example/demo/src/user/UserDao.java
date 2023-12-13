package com.example.demo.src.user;


import com.example.demo.src.user.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class UserDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<GetUserRes> getUsers(){
        String getUsersQuery = "select * from Customer";
        return this.jdbcTemplate.query(getUsersQuery,
                (rs,rowNum) -> new GetUserRes(
                        rs.getInt("customerId"),
                        rs.getString("userId"),
                        rs.getString("password"),
                        rs.getString("userName"),
                        rs.getString("email"),
                        rs.getString("phoneNumber"),
                        rs.getInt("status"))
                );
    }

    public List<GetUserRes> getUsersByEmail(String email){
        String getUsersByEmailQuery = "select * from Customer where email =?";
        String getUsersByEmailParams = email;
        return this.jdbcTemplate.query(getUsersByEmailQuery,
                (rs, rowNum) -> new GetUserRes(
                        rs.getInt("customerId"),
                        rs.getString("userId"),
                        rs.getString("password"),
                        rs.getString("userName"),
                        rs.getString("email"),
                        rs.getString("phoneNumber"),
                        rs.getInt("status")),
                getUsersByEmailParams);
    }

    public GetUserRes getUser(int customerId){
        String getUserQuery = "select * from Customer where customerId = ?";
        int getUserParams = customerId;
        return this.jdbcTemplate.queryForObject(getUserQuery,
                (rs, rowNum) -> new GetUserRes(
                        rs.getInt("customerId"),
                        rs.getString("userId"),
                        rs.getString("password"),
                        rs.getString("userName"),
                        rs.getString("email"),
                        rs.getString("phoneNumber"),
                        rs.getInt("status")),
                getUserParams);
    }

    public int getUserStatus(int customerId){
        String getUserQuery = "select status from Customer where customerId = ?";
        int getUserParams = customerId;
        return this.jdbcTemplate.queryForObject(getUserQuery, int.class, getUserParams);
    }

    public int deleteUser(int customerId){
        String deleteUserQuery = "update Customer set status = 0 where customerId = ?";
        int deleteUserParams = customerId;

        return this.jdbcTemplate.update(deleteUserQuery, deleteUserParams);  //return 값 : 영향받은 로우의 수
    }

    public int createUser(PostUserReq postUserReq){
        String createUserQuery = "insert into Customer (userId, password, userName, email, phoneNumber) VALUES (?,?,?,?,?)";
        Object[] createUserParams = new Object[]{postUserReq.getUserName(), postUserReq.getUserId(), postUserReq.getPassword(), postUserReq.getEmail()};
        this.jdbcTemplate.update(createUserQuery, createUserParams);

        String lastInserIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInserIdQuery,int.class);
    }

    public int checkEmail(String email){
        String checkEmailQuery = "select exists(select email from Customer where email = ?)";
        String checkEmailParams = email;
        return this.jdbcTemplate.queryForObject(checkEmailQuery,
                int.class,
                checkEmailParams);

    }

    public int modifyUserName(PatchUserReq patchUserReq){
        String modifyUserNameQuery = "update Customer set userName = ? where customerId = ? ";
        Object[] modifyUserNameParams = new Object[]{patchUserReq.getUserName(), patchUserReq.getCustomerId()};

        return this.jdbcTemplate.update(modifyUserNameQuery,modifyUserNameParams);
    }

    public User getPwd(PostLoginReq postLoginReq){
        String getPwdQuery = "select customerId, userId, password, userName, email, phoneNumber, status from Customer where userId = ?";
        String getPwdParams = postLoginReq.getUserId();

        return this.jdbcTemplate.queryForObject(getPwdQuery,
                (rs,rowNum)-> new User(
                        rs.getInt("customerId"),
                        rs.getString("userId"),
                        rs.getString("password"),
                        rs.getString("password"),
                        rs.getString("userName"),
                        rs.getString("email"),
                        rs.getInt("status")
                ),
                getPwdParams
                );

    }


}
