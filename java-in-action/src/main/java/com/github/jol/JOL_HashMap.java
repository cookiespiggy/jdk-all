package com.github.jol;

import org.agrona.collections.Int2ObjectHashMap;
import org.eclipse.collections.impl.map.mutable.primitive.IntObjectHashMap;
import org.openjdk.jol.info.ClassLayout;

import java.util.HashMap;
import java.util.Map;

public class JOL_HashMap {
    public static void main(String[] args) {
        // 48
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "小明");
        map.put(2, "小明");
        map.put(3, "小明");
        map.put(4, "小明");
        System.out.println(ClassLayout.parseInstance(map).instanceSize());
        System.out.println(ClassLayout.parseInstance(map).toPrintable());
        System.out.println("==========");
        // 40
        IntObjectHashMap<String> map2 = new IntObjectHashMap<>();
        System.out.println(ClassLayout.parseInstance(map2).instanceSize());
        System.out.println(ClassLayout.parseInstance(map2).toPrintable());
        System.out.println("==========");
        // 48
        Int2ObjectHashMap<String> map3 = new Int2ObjectHashMap<String>();
        System.out.println(ClassLayout.parseInstance(map3).instanceSize());
        System.out.println(ClassLayout.parseInstance(map3).toPrintable());
        System.out.println("==========");
    }
}
