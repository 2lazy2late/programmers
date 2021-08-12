package com.coding_test_highscore_kit;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class unsuccessfulRunner {

    public static void main(String[] args) {
        System.out.println("Current Program : unsuccessfulRunner");

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


        String[] chars = "a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z".split(",");

        for(int i = 0 ; i < count ; i++){
            StringBuilder sb = new StringBuilder();

            int length = rand.nextInt(1, 20);

            for (int j = 0 ; j < length ; j++)
            {
                sb.append(chars[rand.nextInt(chars.length)]);
            }
            participant[i] = sb.toString();
            if(i != incompleteIndex && i != count-1){
                completion[i] = sb.toString();
            }else if(i == incompleteIndex ){
                System.out.println("incomplete member name : " + sb);
            }
        }

        return new String[][] {participant, completion};
    }

    // 효율성 테스트 5번 실패
    public static String solution(String[] participant, String[] completion) {

        Map pMap = new HashMap<String, Integer>();
        Map cMap = new HashMap<String, Integer>();

        for(int i = 0; i < participant.length; i++){
            String pKey = participant[i];
            if(!pMap.containsKey(pKey)){
                pMap.put(pKey, 1);
            }else{
                pMap.put(pKey, (int)pMap.get(pKey) + 1);
            }
            if(i != participant.length - 1){
                String cKey = completion[i];
                if(!cMap.containsKey(cKey)){
                    cMap.put(cKey, 1);
                }else{
                    cMap.put(cKey, (int)cMap.get(cKey) + 1);
                }
            }
        }
        if(pMap.size() != cMap.size()){
            for (Object key : pMap.keySet()) {
                if(cMap.containsKey(key) != true){
                    return (String) key;
                }
            }
        }else{
            for (Object key : pMap.keySet()) {
                if(cMap.get(key)!= pMap.get(key)){
                    return (String) key;
                }
            }
        }

        return null;
    }

    // 효율성 테스트 모두 실패 (시간 초과)
    public static String failedSolution(String[] participant, String[] completion) {

        List<String> list = new ArrayList<String>(Arrays.asList(participant));

        for(int i = 0; i < completion.length; i++){
            list.remove(completion[i]);
        }

        return list.get(0);
    }
}
