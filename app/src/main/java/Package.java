import java.util.Date;

public class Package {
    String packageId;
    String description;
    float weight;
    Date sheduledDate;
    Boolean fragile;

    public Package(String packageId, String description, float weight, Date sheduledDate, Boolean fragile) {
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

    public Date getSheduledDate() {
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

    public void setSheduledDate(Date sheduledDate) {
        this.sheduledDate = sheduledDate;
    }

    public void setFragile(Boolean fragile) {
        this.fragile = fragile;
    }
}
