package com.github.hutool;

import cn.hutool.core.util.StrUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Use_Common {

    public static void main(String[] args) {
        String str = "just do it! \r\n" +
                "ds" + "    demo    demom";

        String s = StrUtil.cleanBlank(str);
        System.out.println(s);

        System.out.println(replaceBlank(str));

    }


    public static String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\\t|\\r|\\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }
}
