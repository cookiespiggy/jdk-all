package com.github.demo;

import cn.hutool.core.util.StrUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Use_Reg {
    public static void main(String[] args) {
        List<Rule> rules = new ArrayList<>();
        for (int i = 0; i < 100_000; i++) {
            rules.add(new Rule("baidu" + i, "/dir" + i));
        }
        String cur = "baidu1";
        long st = System.currentTimeMillis();
        List<String> matchRes = match(cur, rules);
        long et = System.currentTimeMillis();
        System.out.println("cost time mill is " + (et - st));
    }

    private static List<String> match(String cur, List<Rule> rules) {
        List<String> collect = rules.stream().filter(rule -> cur.contains(rule.value)).map(rule -> rule.dir).collect(Collectors.toList());
        return collect;
    }

    static class Rule {
        private String value;
        private String dir;

        public Rule(String value, String dir) {
            this.value = value;
            this.dir = dir;
        }
    }
}
