package com.courierx.courierx;

import java.util.Date;

public class Package {
    String packageId;
    String description;
    float weight;
    String sheduledDate;
    Boolean fragile;

    public Package(){
    }

    public Package(String packageId, String description, float weight, String sheduledDate, Boolean fragile) {
        this.packageId = packageId;
        this.description = description;
        this.weight = weight;
        this.sheduledDate = sheduledDate;
        this.fragile = fragile;
    }

    public String getPackageId() {
        return packageId;
    }

    public String getDescription() {
        return description;
    }

    public float getWeight() {
        return weight;
    }

    public String getSheduledDate() {
        return sheduledDate;
    }

    public Boolean getFragile() {
        return fragile;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public void setSheduledDate(String sheduledDate) {
        this.sheduledDate = sheduledDate;
    }

    public void setFragile(Boolean fragile) {
        this.fragile = fragile;
    }
}
