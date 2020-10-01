package com.courierx.courierx.Models;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class Package {
    private String sender;
    private String receiver;
    private String note;
    private String packageId;
    private String description;
    private Float weight;
    private String scheduledDate;
    private Boolean fragile;
    private Boolean isTracked;
    private List<TrackInfo> trackInfoList;
}
