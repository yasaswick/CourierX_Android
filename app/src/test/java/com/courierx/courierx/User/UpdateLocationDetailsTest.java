package com.courierx.courierx.User;

import com.courierx.courierx.Feedback.AddFeedback;
import com.courierx.courierx.UpdateLocationDetails;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UpdateLocationDetailsTest {
    private UpdateLocationDetails updateLocationDetails;

    @Before
    public void setup(){
        updateLocationDetails = new UpdateLocationDetails();
    }
    @Test
    public void testMessageValidationTrue(){
        boolean result = updateLocationDetails.UpdateLocationValidate("This is a test location!");
        Assert.assertEquals(true,result);
    }
    @Test
    public void testMessageValidationFalse(){
        boolean result = updateLocationDetails.UpdateLocationValidate("");
        Assert.assertEquals(false,result);
    }
}
