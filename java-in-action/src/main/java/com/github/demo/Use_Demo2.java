package com.github.demo;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.multimap.list.MutableListMultimap;
import org.eclipse.collections.impl.factory.Multimaps;

public class Use_Demo2 {
    public static void main(String[] args) {

        MutableListMultimap<Integer, Integer> multimap =
                Multimaps.mutable.list.with(2, 1);

        System.out.println(multimap);

        ImmutableList<Integer> defaultValue =
                Lists.immutable.with(1, 2, 3);



        MutableList<Integer> oneValue =
                multimap.getIfAbsentPutAll(1, defaultValue);
        MutableList<Integer> twoValue =
                multimap.getIfAbsentPutAll(2, defaultValue);


    }
}
