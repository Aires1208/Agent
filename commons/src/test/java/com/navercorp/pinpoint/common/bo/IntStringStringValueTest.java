package com.navercorp.pinpoint.common.bo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by root on 17-2-24.
 */
public class IntStringStringValueTest {
    private IntStringStringValue intStringStringValue ;

    @Before
    public void setUp() throws Exception {
        intStringStringValue = new IntStringStringValue(1,"ut","UTtest");
    }

    @Test
    public void getIntValue() throws Exception {
        Assert.assertEquals(1,intStringStringValue.getIntValue());
    }

    @Test
    public void getStringValue1() throws Exception {
        Assert.assertEquals("ut",intStringStringValue.getStringValue1());
    }

    @Test
    public void getStringValue2() throws Exception {
        Assert.assertEquals("UTtest",intStringStringValue.getStringValue2());
    }

}