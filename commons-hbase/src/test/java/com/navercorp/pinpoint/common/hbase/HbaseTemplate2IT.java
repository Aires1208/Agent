package com.navercorp.pinpoint.common.hbase;
import org.junit.Assert;
import org.apache.hadoop.hbase.TableNotFoundException;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.hadoop.hbase.HbaseConfigurationFactoryBean;
import org.springframework.data.hadoop.hbase.HbaseSystemException;

/**
 * @author emeroad
 */
public class HbaseTemplate2IT {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static HbaseConfigurationFactoryBean hbaseConfigurationFactoryBean;

    @Test
    public void test_baseTemplate2IT() {
        System.out.println("HbaseTemplate2IT");
    }


    @Test
    @Ignore
    public void notExist() throws Exception {

        HbaseTemplate2 hbaseTemplate2 = new HbaseTemplate2();
        hbaseTemplate2.setConfiguration(hbaseConfigurationFactoryBean.getObject());
        hbaseTemplate2.afterPropertiesSet();

        try {
            hbaseTemplate2.put("NOT_EXIST", new byte[0], "familyName".getBytes(), "columnName".getBytes(), new byte[0]);
            Assert.fail("exceptions");
        } catch (HbaseSystemException e) {
            if (!(e.getCause().getCause() instanceof TableNotFoundException)) {
                Assert.fail("unexpected exception :" + e.getCause());
            }
        } finally {
            hbaseTemplate2.destroy();
        }


    }
}
