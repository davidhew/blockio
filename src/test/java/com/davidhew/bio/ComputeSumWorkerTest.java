package com.davidhew.bio;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by shouru on 2018/9/16.
 */
public class ComputeSumWorkerTest {

    @Test
    public void testGetResult(){

        Assert.assertEquals(15,new ComputeSumWorker().getResult(5));
    }
}
