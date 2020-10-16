package com.courierx.courierx.Feedback;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FeedbackTest {

    private AddFeedback addFeedback;

    @Before
    public void setUp(){
        addFeedback = new AddFeedback();
    }


    @Test
    public void testMessageValidationTrue(){
        boolean result = addFeedback.feedbackMessageValidate("This is a test message!");
        Assert.assertEquals(true,result);
    }

    @Test
    public void testMessageValidationFalse(){
        boolean result = addFeedback.feedbackMessageValidate("Message");
        Assert.assertEquals(false,result);
    }

    @Test
    public void testTopicValidationFalse(){
        boolean result = addFeedback.feedbackTopicValidate("This is a sample long topic!");
        Assert.assertEquals(false,result);
    }

    @Test
    public void testTopicValidationTrue(){
        boolean result = addFeedback.feedbackTopicValidate("Topic");
        Assert.assertEquals(true,result);
    }



}
