package com.davidhew.bio;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by shouru on 2018/9/16.
 */
public class ComputePrimeWorkerTest {


    @Test
    public void testGetResult(){

        Assert.assertEquals(97,new ComputePrimeWorker().getResult(90));
    }
}
