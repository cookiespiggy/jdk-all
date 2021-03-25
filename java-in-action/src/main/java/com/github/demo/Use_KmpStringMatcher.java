package com.github.demo;

import java.util.ArrayList;
import java.util.List;

public class Use_KmpStringMatcher {
    public static void main(String[] args) {
        int size = 100_000;
        List<KmpStringMatcher> list = new ArrayList<>(size);
        for (int j = 0; j < size; j++) {
            KmpStringMatcher kmpStringMatcher = new KmpStringMatcher("shanghai" + j);
            list.add(kmpStringMatcher);
        }

        String url = "我zai上海shanghai";

        long st = System.currentTimeMillis();
        for (KmpStringMatcher matcher : list) {
            if (matcher.search(url) != -1) {

            }
        }
        long et = System.currentTimeMillis();

        System.out.println(et - st);
    }
}
