package com.coding_test_highscore_kit;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class camouflage {
    public static void main(String[] args) {
        System.out.println("Current Program : camouflage");

        String[][] param = makeParameter();
        boolean result;

        System.gc();
        long beforeMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        long beforeTime = System.nanoTime();

        //////////////////////////////////////////////////////////////////////////////////////////////////////

        result = solution(param);

        //////////////////////////////////////////////////////////////////////////////////////////////////////

        long afterTime = System.nanoTime();
        System.gc();
        long afterMemory  = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        System.out.println("result : " + result);
        System.out.println((afterTime - beforeTime)/1000/1000+"ms, " + (beforeMemory - afterMemory)/1024 + "MB");

    }

    public static String[][] makeParameter(){

        ThreadLocalRandom rand = ThreadLocalRandom.current();

        int count = rand.nextInt(30);
        String[][] clothes = new String[count][2];

        return clothes;
    }

    public static boolean solution(String[][] clothes){

        return true;
    }
}
