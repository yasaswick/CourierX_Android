package com.courierx.courierx.Models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Feedback {
    String feedbackId;
    String userId;
    String userName;
    String title;
    Long date;
    String content;
    int read;
}
