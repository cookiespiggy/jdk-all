package com.github.jctools;

import org.jctools.maps.NonBlockingHashMapLong;

public class Use_NonBlockingHashMapLong {
    public static void main(String[] args) {

        NonBlockingHashMapLong<String> map = new NonBlockingHashMapLong<>();
        map.put(1,"小明");
        map.print();
        System.out.println(map);

    }
}
