package com.github.demo;

public class Use_Demo3 {
    public static void main(String[] args) {

        long st = System.currentTimeMillis();
        int[] arr = new int[100_000];
        for (int i = 0; i < 100_000; i++) {
            arr[i] = i;
        }
        long et = System.currentTimeMillis();
        System.out.println(et - st);
    }
}
