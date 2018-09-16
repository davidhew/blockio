package com.davidhew.bio;

/**
 * Created by shouru on 2018/9/16.
 */
public class ComputeSumWorker {


    /**
     * 获取从initial+1到2000（不包含）之间，最小的质数
     * @param initial
     * @return
     */
    public long getResult(int initial){

        long sum = 0;
        for(int i = 1;i<=initial;i++){

            sum+=i;
        }

        return sum;
    }
}
