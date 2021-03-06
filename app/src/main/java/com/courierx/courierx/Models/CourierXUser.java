package com.courierx.courierx.Models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
public class CourierXUser {

    private String uid;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private String role;
    private Long balance;
    public CourierXUser(){

    }

}
