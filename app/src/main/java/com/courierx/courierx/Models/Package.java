package com.courierx.courierx.Models;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class Package {
    private String packageId;
    private String description;
    private float weight;
    private String scheduledDate;
    private Boolean fragile;

}
