package com.coding_test_highscore_kit;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Pattern;

public class phoneNumberList {
    public static void main(String[] args) {
        System.out.println("Current Program : phoneNumberList");

        String[] param = makeParameter();
        boolean result;

        System.gc();
        long beforeMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        long beforeTime = System.nanoTime();

        //////////////////////////////////////////////////////////////////////////////////////////////////////

        result = solution(param);
        //result = solution(new String[]{"123", "456", "789"});

        //////////////////////////////////////////////////////////////////////////////////////////////////////

        long afterTime = System.nanoTime();
        System.gc();
        long afterMemory  = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        System.out.println("result : " + result);
        System.out.println((afterTime - beforeTime)/1000/1000+"ms, " + (beforeMemory - afterMemory)/1024 + "MB");

    }

    public static String[] makeParameter(){

        ThreadLocalRandom rand = ThreadLocalRandom.current();

        int count = rand.nextInt(1000000);
        String[] phoneNumberList = new String[count];

        System.out.println("phoneNumber count : " + count);

        String[] chars = "0,1,2,3,4,5,6,7,8,9".split(",");

        for(int i = 0 ; i < count ; i++){
            StringBuilder sb = new StringBuilder();

            int length = rand.nextInt(1, 20);

            for (int j = 0 ; j < length ; j++)
            {
                sb.append(chars[rand.nextInt(chars.length)]);
            }
            phoneNumberList[i] = sb.toString();

        }

        return phoneNumberList;
    }



    // 통과..
    public static boolean solution(String[] phone_book){

        quickSort(phone_book, 0, phone_book.length-1);

        int numberCount = phone_book.length;

        Set<String> keySet = new HashSet<String>();

        for(int i = numberCount - 1; i >= 0; i--){
            String key = phone_book[i];
            if(!keySet.add(key)) return false;

            for(int j = 0, len = key.length(); j < len; j++){
                keySet.add(key.substring(0, j));
            }
        }

        return true;
    }

    public static void quickSort(String[] data, int l, int r){
        int leftIndex = l;
        int rightIndex = r;
        int pivot = data[(l+r)/2].length();

        do{
            while(data[leftIndex].length() < pivot) leftIndex++;
            while(data[rightIndex].length() > pivot) rightIndex--;
            if(leftIndex <= rightIndex){
                String temp = data[leftIndex];
                data[leftIndex] = data[rightIndex];
                data[rightIndex] = temp;
                leftIndex++;
                rightIndex--;
            }
        }while (leftIndex <= rightIndex);

        if(l < rightIndex) quickSort(data, l, rightIndex);
        if(r > leftIndex)  quickSort(data, leftIndex, r);
    }


    // 시간 초과
    public static boolean streamSolution(String[] phone_book){
        Set<String> prefixSet = new HashSet<String>();
        prefixSet.addAll(List.of(phone_book));

        for(int i = 0 ; i < phone_book.length; i++){
            int finalIndex = i;
            if(prefixSet.stream().filter(s -> s.startsWith(phone_book[finalIndex])).count() > 1){
                return false;
            }
        }

        return true;

    }

    // 정규식 풀이 : 시간 초과
    public static boolean regexSolution(String[] phone_book){
        boolean answer = true;

        for(int i = 0; i < phone_book.length; i++){
            for(int j = 0; j < phone_book.length; j++){
                if(i != j) {
                    if(Pattern.matches("^" + phone_book[j] + ".+$", phone_book[i])) { return false; }
                }
            }
        }

        return answer;
    }


    // 정렬 후 비교 풀이  : 시간 초과
    public static boolean failedSolution(String[] phone_book) {


        Set<String> prefixSet = new HashSet<String>();

        Arrays.sort(phone_book, new Comparator<String>(){
            @Override
            public int compare(String o1, String o2){
                return o1.length() - o2.length();
            }
        });

        int numberCount = phone_book.length;

        for(int i = 0; i < numberCount; i++){
            String key = phone_book[i];
            int length = key.length();
            prefixSet.add(key);
            for(int j = i + 1; j < numberCount; j++){
                String compareKey = phone_book[j].substring(0, length);
                if(prefixSet.contains(compareKey)){
                    return false;
                }
            }
        }

        return true;
    }
}
