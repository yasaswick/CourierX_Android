package com.courierx.courierx.Payment;

import com.courierx.courierx.AddPayment;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AddPaymentTest {

    private AddPayment addPayment;

    @Before
    public void setUp(){
        addPayment = new AddPayment();
    }

    @Test
    public void testMessageValidationTrue(){
        boolean result = addPayment.paymentUserValidate("No such user");
        Assert.assertEquals(true,result);
    }


}
