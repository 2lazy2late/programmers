package com.weekly_challenge;

import java.util.Arrays;
import java.util.OptionalInt;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;


public class weekly_20210802 {
    public static void main(String[] args) {
        System.out.println("Current Program : weekly_20210802");

        int[][] param = makeParameter(100);
        String result;

        System.gc();
        long beforeMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        long beforeTime = System.nanoTime();

        //////////////////////////////////////////////////////////////////////////////////////////////////////

        result = solution(param);

        //////////////////////////////////////////////////////////////////////////////////////////////////////

        long afterTime = System.nanoTime();
        System.gc();
        long afterMemory  = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        System.out.println("result : " +  result);
        System.out.println((afterTime - beforeTime)/1000+"ms, " + (beforeMemory - afterMemory)/1024 + "MB");

        
        
        beforeTime = System.nanoTime();
        result = solution2(param);
        afterTime = System.nanoTime();

        System.gc();
        long afterMemory2  = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        System.out.println("result : " +  result);
        System.out.println((afterTime - beforeTime)/1000+"ms, " + (beforeMemory - afterMemory2)/1024 + "MB");

    }

    public static int[][] makeParameter(int studentCount){
        int[][] param = new int[studentCount][studentCount];

        // random 에서는 nextLong(long origin, long bound) 를 지원하지 않는다.
        // thread local random 에서는 사용 가능 : param[0] = rand.nextLong(1, 2501);
        ThreadLocalRandom rand = ThreadLocalRandom.current();

        for(int i = 0; i < studentCount; i++){
            for(int j = 0; j < studentCount; j++){
                param[i][j] = rand.nextInt(0,100 + 1);
            }
        }
//        System.out.println("Parameter : ");
//        for(int k = 0; k < studentCount; k++){
//            System.out.println(Arrays.toString(param[k]));
//        }

        return param;
    }

    // 나의 첫 제출 풀이 : Stream과 람다식에 대한 개념 및 이해가 부족하다.
    public static String solution(int[][] scores) {

        StringBuilder builder = new StringBuilder();

        int count = scores.length;
        int[][] pivotedScores = new int[count][count];

        for(int i = 0; i < count; i++){
            for(int j = 0; j < count; j++){
                pivotedScores[i][j] = scores[j][i];
            }
        }

        for(int r = 0; r < count; r++){

            int index = r;
            int ownValue = pivotedScores[r][r];

            double average = Arrays.stream(pivotedScores[r]).average().getAsDouble();

            OptionalInt max = Arrays.stream(pivotedScores[r]).max();
            OptionalInt  min = Arrays.stream(pivotedScores[r]).min();

            long dupCount = ownValue == max.getAsInt()
                    ? Arrays.stream(pivotedScores[r]).filter(item -> item == max.getAsInt()).count()
                    : Arrays.stream(pivotedScores[r]).filter(item -> item == min.getAsInt()).count();

            if((dupCount < 2) && (ownValue == max.getAsInt() || ownValue == min.getAsInt())){
                average = (Arrays.stream(pivotedScores[r]).sum() - ownValue) / (count - 1);
            }

            builder.append(average >= 90 ? "A" : average >= 80 ? "B" : average >= 70 ? "C" : average >= 50 ? "D" : "F" );

        }

        return builder.toString();
    }

    // 다른 사용자의 풀이 : 내가 Stream에 대해 공부하며 첫 풀이를 했을 때 바라던 풀이 형태
    public static String solution2(int[][] scores) {
        return Stream.iterate(0, v -> v + 1).limit(scores.length).map(index -> {
            int[] ints = scores[index];
            int flag = 0;
            int min = Integer.MAX_VALUE;
            int max = 0;
            double sum = 0.0;
            for (int[] score : scores) {
                min = Math.min(min, score[index]);
                max = Math.max(max, score[index]);
                if (ints[index] == score[index])
                    flag++;
                sum += score[index];
            }
            int divider = scores.length;
            if ((max == ints[index] || min == ints[index]) && flag == 1) {
                sum -= ints[index];
                divider--;
            }
            sum = sum / divider;
            return sum >= 90 ? "A" : sum >= 80 ? "B" : sum >= 70 ? "C" : sum >= 50 ? "D" : "F";
        }).reduce((a, b) -> a + b).get();
    }

}
