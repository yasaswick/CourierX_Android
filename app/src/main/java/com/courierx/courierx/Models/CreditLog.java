package com.courierx.courierx.Models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreditLog {
    private String date;
    private Float amount;
    private String type;
}
