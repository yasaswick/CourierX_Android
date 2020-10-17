package com.courierx.courierx.Package;

import com.courierx.courierx.AddPackageDetails;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AddPackageTest {
    private AddPackageDetails addPackageDetails;

    @Before
    public void setAddPackageDetails(){
        addPackageDetails = new AddPackageDetails();
    }

    @Test
    public void testDescriptionTrue(){
        boolean result = addPackageDetails.descriptionValidate("Test Description");
        Assert.assertTrue(result);
    }

    @Test
    public void testDescriptionFalse() {
        boolean result = addPackageDetails.descriptionValidate("");
        Assert.assertFalse(result);
    }

    @Test
    public void testWeightTrue(){
        boolean result = addPackageDetails.weightValidate("1.2");
        Assert.assertTrue(result);
    }

    @Test
    public void testWeightFalse1(){
        boolean result = addPackageDetails.weightValidate("");
        Assert.assertFalse(result);
    }

    @Test
    public void testWeightFalse2(){
        boolean result = addPackageDetails.weightValidate("abc");
        Assert.assertFalse(result);
    }

    @Test
    public void testDateTrue(){
        boolean result = addPackageDetails.dateValidate("1602970340873");
        Assert.assertTrue(result);
    }

    @Test
    public void testDateFalse(){
        boolean result = addPackageDetails.dateValidate("");
        Assert.assertFalse(result);
    }

}
