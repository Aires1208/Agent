package com.navercorp.pinpoint.common.bo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by root on 17-2-24.
 */
public class IntStringValueTest {
    private IntStringValue intStringValue;

    @Before
    public void setUp() throws Exception {
        intStringValue = new IntStringValue(1,"ut");
    }
    @Test
    public void getStringValue() throws Exception {
        Assert.assertEquals(1,intStringValue.getIntValue());
    }

    @Test
    public void getIntValue() throws Exception {
        Assert.assertEquals("ut",intStringValue.getStringValue());
    }

}