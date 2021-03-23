package com.github.ec;

import org.eclipse.collections.api.map.primitive.LongDoubleMap;
import org.eclipse.collections.api.map.primitive.MutableLongDoubleMap;
import org.eclipse.collections.impl.factory.primitive.LongDoubleMaps;

public class Use_LongDoubleMap {

    private static final MutableLongDoubleMap map = LongDoubleMaps.mutable.empty();

    public static void main(String[] args) {

        map.put(1, 2);
        map.put(2, 2);
        map.put(3, 2);

        System.out.println(map);
    }
}
