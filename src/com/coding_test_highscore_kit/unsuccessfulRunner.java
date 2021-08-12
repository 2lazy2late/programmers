package com.coding_test_highscore_kit;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class unsuccessfulRunner {

    public static void main(String[] args) {
        System.out.println("Current Program : weekly_20210801");

        String[][] param = makeParameter();
        String result;

        System.gc();
        long beforeMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        long beforeTime = System.nanoTime();

        //////////////////////////////////////////////////////////////////////////////////////////////////////

        result = solution(param[0], param[1]);

        //////////////////////////////////////////////////////////////////////////////////////////////////////

        long afterTime = System.nanoTime();
        System.gc();
        long afterMemory  = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        System.out.println("result : " + result);
        System.out.println((afterTime - beforeTime)/1000+"ms, " + (beforeMemory - afterMemory)/1024 + "MB");

    }

    public static String[][] makeParameter(){


        ThreadLocalRandom rand = ThreadLocalRandom.current();

        int count = rand.nextInt(100000);
        String[] participant = new String[count];
        String[] completion = new String[count - 1];

        System.out.println("participant count : " + count);

        int incompleteIndex = rand.nextInt(count);
        System.out.println("incomplete Index : " + incompleteIndex);


        String chars[] = "a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z".split(",");

        for(int i = 0 ; i < count ; i++){
            StringBuffer buffer = new StringBuffer();

            int length = rand.nextInt(1, 20);

            for (int j = 0 ; j < length ; j++)
            {
                buffer.append(chars[rand.nextInt(chars.length)]);
            }
            participant[i] = buffer.toString();
            if(i != incompleteIndex && i != count-1){
                completion[i] = buffer.toString();
            }else if(i == incompleteIndex ){
                System.out.println("incomplete member name : " + buffer.toString());
            }
        }

        return new String[][] {participant, completion};
    }

    public static String solution(String[] participant, String[] completion) {
        String answer = "";

//        System.out.println(Arrays.toString(participant));


        return answer;
    }
}
