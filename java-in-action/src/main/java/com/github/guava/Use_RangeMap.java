package com.github.guava;

import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.ImmutableList;

import java.util.List;

public class Use_RangeMap {

    public static void main(String[] args) {
        ImmutableList<C> db = Lists.immutable.of(new C("baidu.com", "/rule1"), new C("baidu", "/rule2"));
        match("baidu", db);
    }

    public static List<String> match(String cur, ImmutableList<C> db) {

        db.select(new Predicate<C>() {
            @Override
            public boolean accept(C c) {
                return c.value.contains(cur);
            }
        });

        return null;
    }

    static class C {
        private String value;
        private String dir;

        public C(String value, String dir) {
            this.value = value;
            this.dir = dir;
        }
    }
}
