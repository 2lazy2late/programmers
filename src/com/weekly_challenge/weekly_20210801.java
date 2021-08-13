package com.weekly_challenge;

import java.util.concurrent.ThreadLocalRandom;


public class weekly_20210801 {
    public static void main(String[] args) {
        System.out.println("Current Program : weekly_20210801");

        long[] param = makeParameter();

        System.gc();
        long beforeMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        long beforeTime = System.nanoTime();

        System.out.println("result : " +  solution(param[0], param[1], param[2]));

        long afterTime = System.nanoTime();
        System.gc();
        long afterMemory  = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        System.out.println((afterTime - beforeTime)/1000/1000+"ms, " + (beforeMemory - afterMemory)/1024 + "MB");

    }

    public static long[] makeParameter(){
        long[] param = new long[3];

        // random 에서는 nextLong(long origin, long bound) 를 지원하지 않는다.
        // thread local random 에서는 사용 가능 : param[0] = rand.nextLong(1, 2501);
        ThreadLocalRandom rand = ThreadLocalRandom.current();

        // price
        param[0] = rand.nextLong(1,2500 + 1);
        // money
        param[1] = rand.nextLong(1,1000000000 + 1);
        // count
        param[2] = rand.nextLong(1,2500 + 1);

        System.out.println("Parameter Info : [price : " + param[0] +", money : " + param[1] + ", count : " + param[2] + "]");
        return param;
    }

    public static long solution(long price, long money, long count) {
        return money > price*count*(count+1)/2 ? 0 : price*count*(count+1)/2 - money;
    }


}
