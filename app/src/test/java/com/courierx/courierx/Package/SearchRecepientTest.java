package com.courierx.courierx.Package;

import com.courierx.courierx.SearchRecepient;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SearchRecepientTest {
    private SearchRecepient searchRecepient;

    @Before
    public void setSearchRecepient(){
        searchRecepient = new SearchRecepient();
    }

    @Test
    public void testRecipientTrue(){
        boolean result = searchRecepient.recepientValidate("HmtlfZshlASVm98WCeaar875Nt73");
        Assert.assertTrue(result);
    }

    @Test
    public void testRecipientFalse(){
        boolean result = searchRecepient.recepientValidate("");
        Assert.assertFalse(result);
    }

}
