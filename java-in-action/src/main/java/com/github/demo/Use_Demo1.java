package com.github.demo;

import org.eclipse.collections.api.multimap.bag.MutableBagMultimap;
import org.eclipse.collections.api.multimap.list.MutableListMultimap;
import org.eclipse.collections.impl.factory.Multimaps;

public class Use_Demo1 {
    public static void main(String[] args) {

        MutableListMultimap<String, String> multimap =
                Multimaps.mutable.list.with(
                        "nj", "Monmouth",
                        "nj", "Bergen",
                        "nj", "Union");

        System.out.println(multimap);

        MutableBagMultimap<String, String> transformed =
                multimap.collectKeyMultiValues(
                        String::toUpperCase,
                        String::toUpperCase);

        System.out.println(transformed);
    }
}
