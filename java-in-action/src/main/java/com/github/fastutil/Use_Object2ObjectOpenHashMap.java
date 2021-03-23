package com.github.fastutil;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;

public class Use_Object2ObjectOpenHashMap {
    public static void main(String[] args) {
        Object2ObjectOpenHashMap<Integer, String> map = new Object2ObjectOpenHashMap<>();
        map.put(1, "小明");
        map.put(2, "小刚");
        System.out.println(map);
    }
}
