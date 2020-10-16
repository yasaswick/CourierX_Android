package com.courierx.courierx.Package;

import com.courierx.courierx.UpdatePackage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UpdatePackageTest {
    private UpdatePackage updatePackage;

    @Before
    public void setUpdatePackage(){
        updatePackage = new UpdatePackage();
    }

    @Test
    public void testDescriptionTrue(){
        boolean result = updatePackage.descriptionValidate("Test Description");
        Assert.assertTrue(result);
    }

    @Test
    public void testDescriptionFalse() {
        boolean result = updatePackage.descriptionValidate("");
        Assert.assertFalse(result);
    }

    @Test
    public void testWeightTrue(){
        boolean result = updatePackage.weightValidate("1.2");
        Assert.assertTrue(result);
    }

    @Test
    public void testWeightFalse1(){
        boolean result = updatePackage.weightValidate("");
        Assert.assertFalse(result);
    }

    @Test
    public void testWeightFalse2(){
        boolean result = updatePackage.weightValidate("abc");
        Assert.assertFalse(result);
    }

    @Test
    public void testDateTrue(){
        boolean result = updatePackage.dateValidate("1602970340873");
        Assert.assertTrue(result);
    }

    @Test
    public void testDateFalse(){
        boolean result = updatePackage.dateValidate("");
        Assert.assertFalse(result);
    }

}
