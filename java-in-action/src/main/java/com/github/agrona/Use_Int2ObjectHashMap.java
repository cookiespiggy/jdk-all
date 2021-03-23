package com.github.agrona;

import org.agrona.collections.Int2ObjectHashMap;

/*
Int2ObjectHashMap<String> 代替 java.util.HashMap<Integer, String>
 */
public class Use_Int2ObjectHashMap {

    public static void main(String[] args) {
        Int2ObjectHashMap<String> map = new Int2ObjectHashMap<String>();
        map.put(1, "小明");
        map.put(2, "小刚");
        System.out.println(map);
    }
}
