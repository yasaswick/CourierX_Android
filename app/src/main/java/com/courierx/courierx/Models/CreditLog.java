package com.courierx.courierx.Models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreditLog {
    private  String creditLogId;
    private Long date;
    private Long amount;
    private String type;
}
