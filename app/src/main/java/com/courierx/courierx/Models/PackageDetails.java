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
    private String status;
    private String description;
    private Float weight;
    private String scheduledDate;
    private Boolean fragile;
    private Boolean isTracked;
    private String status;
    private List<TrackInfo> trackInfoList;
}
