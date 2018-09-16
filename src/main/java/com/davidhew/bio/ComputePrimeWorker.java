package com.davidhew.bio;


/**
 *
 * Created by shouru on 2018/9/16.
 */
public class ComputePrimeWorker {


    /**
     * 获取从initial+1到2000（不包含）之间，最小的质数
     * @param initial
     * @return
     */
    public long getResult(int initial){

        for(int i = initial+1;i<2000;i++){

            if(isPrime(i)){
                return i;
            }
        }

        return -1;
    }


    private boolean isPrime(int num){

        for (int i =2;i<Math.sqrt(num);i++){

            if(num %i == 0){
                return false;
            }

        }
        return true;
    }
}
