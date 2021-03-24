package com.github.ec;

import org.eclipse.collections.api.list.primitive.MutableIntList;
import org.eclipse.collections.impl.factory.primitive.IntLists;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Use_IntStream {
    public static void main(String[] args) {

        jdk();
        ec();
    }

    private static void ec() {

        MutableIntList values = IntLists.mutable.withInitialCapacity(999_991);
        for (int i = 0; i < 1_000_000; i++) {
            if (i == 0 || i % 100_000 != 0) {
                values.add(i);
            }
        }


        MutableIntList values1 = IntLists.mutable.ofAll(
                IntStream
                        .range(0, 1_000_000)
                        .filter(i -> (i == 0 || i % 100_000 != 0)));


        MutableIntList uniqueSorted = values1.distinct().sortThis();


    }

    private static void jdk() {

        List<Integer> values1 = IntStream.range(0, 1_000_000)
                .filter(i -> (i == 0 || i % 100_000 != 0))
                .boxed()
                .collect(Collectors.toList());

        int[] values2 = IntStream.range(0, 1_000_000)
                .filter(i -> i == 0 || i % 100_000 != 0)
                .toArray();

    }
}
