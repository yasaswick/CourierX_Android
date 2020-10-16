package com.courierx.courierx.Models;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class PackageDetails {
    private String sender;
    private String receiver;
    private String note;
    private String pickedDate;
    private String packageId;
    private String pickDate;
    private String addedDate;
    private String status;
    private String pickedStatus;
    private String description;
    private Float weight;
    private String scheduledDate;
    private String fragile;
    private String isTracked;
    private List<TrackInfo> trackInfoList;
}
