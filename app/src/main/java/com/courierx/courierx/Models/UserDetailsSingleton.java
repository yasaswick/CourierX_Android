package com.courierx.courierx.Models;


import lombok.Data;

@Data
public class UserDetailsSingleton {

    private CourierXUser courierXUser;
    private static final UserDetailsSingleton userDetailsSingleton = new UserDetailsSingleton();

    private UserDetailsSingleton(){};

    public static UserDetailsSingleton getInstance(){
        return userDetailsSingleton;
    };


}
