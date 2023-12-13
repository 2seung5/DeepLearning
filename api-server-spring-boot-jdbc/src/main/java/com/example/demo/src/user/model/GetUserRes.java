package com.example.demo.src.user.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetUserRes {
    private int customerId;
    private String userId;
    private String password;
    private String userName;
    private String email;
    private String phoneNumber;
    private int status;
}
