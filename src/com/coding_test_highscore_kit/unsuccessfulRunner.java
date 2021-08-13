package com.coding_test_highscore_kit;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@SuppressWarnings("ALL")
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

            if(incompleteIndex == count-1){
                if(i != count-1){
                    completion[i] = sb.toString();
                }else{
                    System.out.println("incomplete member name : " + sb);
                }
            }else if(incompleteIndex == 0) {
                if(i != 0){
                    completion[i-1] = sb.toString();
                }else{
                    System.out.println("incomplete member name : " + sb);
                }
            }else{
                if(i < incompleteIndex){
                    completion[i] = sb.toString();
                }else if(i == incompleteIndex){
                    System.out.println("incomplete member name : " + sb);
                }else{
                    completion[i-1] = sb.toString();
                }
            }
        }

        return new String[][] {participant, completion};
    }

    // 최종 풀이 : 정확성, 속도 모두 만족
    public static String solution(String[] participant, String[] completion) {

        Set<String> memberSet = new HashSet<>();
        int length = participant.length;

        for(int i = 0; i < length; i++){
            if(i != 0){
                String cKey = completion[i-1];
                if(memberSet.contains(cKey)) {
                    memberSet.remove(cKey);
                }else{
                    memberSet.add(cKey);
                }
            }

            String pKey = participant[i];
            if(memberSet.contains(pKey)) {
                memberSet.remove(pKey);
            }else{
                memberSet.add(pKey);
            }
        }

        return memberSet.stream().findFirst().get();
    }



    // 효율성 테스트 5번 항목 실패
    public static String failedSolution2(String[] participant, String[] completion) {

        Map<String, Integer> pMap = new HashMap<>();
        Map<String, Integer> cMap = new HashMap<>();

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
