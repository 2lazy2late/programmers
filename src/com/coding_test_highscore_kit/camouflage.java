package com.coding_test_highscore_kit;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class camouflage {
    public static void main(String[] args) {
        System.out.println("Current Program : camouflage");

        String[][] param = makeParameter();
        int result;

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

        // 모든 문자열의 길이는 1 이상 20 이하인 자연수이고 알파벳 소문자 또는 '_' 로만 이루어져 있습니다.
        String[] chars = "a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,_".split(",");

        // 의상의 종류는 겹치는 경우를 종종 발생시켜야 하므로 따로 배열을 만든다
        String[] clothesSorts = "headgear,eyewear,face,pants,underwear,outer,accessary".split(",");

        // 스파이가 가진 의상의 수는 1개 이상 30개 이하입니다.
        int count = rand.nextInt(1, 30);

        // clothes의 각 행은 [의상의 이름, 의상의 종류]로 이루어져 있습니다.
        String[][] clothes = new String[count][2];

        for(int i = 0 ; i < count ; i++) {
            StringBuilder sbClothesName = new StringBuilder();

            // 모든 문자열의 길이는 1 이상 20 이하인 자연수이지만, 편의를 위해 bound를 7로 변경
            int length = rand.nextInt(1, 7);

            // 편의상 종류는 의상 이름의 길이에 따라 결정
            String sClothesSort = clothesSorts[length - 1];

            for (int j = 0; j < length; j++) {
                sbClothesName.append(chars[rand.nextInt(chars.length)]);
            }
            clothes[i][0] = sbClothesName.toString();
            clothes[i][1] = sClothesSort;
        }

        return clothes;
    }

    public static int solution(String[][] clothes){
        HashMap<String, Integer> sortCountMap = new HashMap<>();
        int result = 1;

        for (String[] clothe : clothes) {
            String clothesSort = clothe[1];

            if (sortCountMap.containsKey(clothesSort)) {
                sortCountMap.put(clothesSort, sortCountMap.get(clothesSort) + 1);
            } else {
                sortCountMap.put(clothesSort, 1);
            }
        }

        for(Integer value : sortCountMap.values()) result = result * (value + 1);

        return result-1;
    }
}
