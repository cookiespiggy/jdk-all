package com.github.demo;

import java.util.ArrayList;
import java.util.List;

public class Use_Constains {
    public static void main(String[] args) {
        int size = 100_000;
        List<String> list = new ArrayList<>(size);
        for (int j = 0; j < size; j++) {
            list.add("shanghai" + j);
        }

        String url = "我zai上海shanghai";

        long st = System.currentTimeMillis();
        for (String matcher : list) {
            if (url.contains(matcher)) {

            }
        }
        long et = System.currentTimeMillis();

        System.out.println(et - st);
    }
}
