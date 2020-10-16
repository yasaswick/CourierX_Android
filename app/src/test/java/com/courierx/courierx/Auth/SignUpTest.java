package com.courierx.courierx.Auth;

import com.courierx.courierx.AuthScreens.SignUp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SignUpTest {

    private SignUp signUp;

    @Before
    public void setSignUp(){
        signUp = new SignUp();
    }

    @Test
    public void testEmailValidationTrue(){
        boolean result = signUp.validateEmail("abc@abc.com");
        Assert.assertTrue(result);
    }

    @Test
    public void testEmailValidationFalse(){
        boolean result = signUp.validateEmail("abcabc.com");
        Assert.assertFalse(result);
    }

    @Test
    public void testPasswordValidationTrue(){
        boolean result = signUp.validatePassword("abcdefghi" , "abcdefghi");
        Assert.assertTrue(result);
    }

    @Test
    public void testPasswordValidationFalse(){
        boolean result = signUp.validatePassword("abc" , "abc");
        Assert.assertFalse(result);
    }

}
