package com.github.ac;

import org.ahocorasick.trie.Emit;
import org.ahocorasick.trie.Trie;

import java.util.Collection;

/**
 * 多模式匹配
 */
public class Use_Ac {
    public static void main(String[] args) {
        Trie.TrieBuilder trieBuilder = Trie.builder()
                .addKeyword("hers")
                .addKeyword("his")
                .addKeyword("she")
                .addKeyword("he");
        for (int i = 0; i < 100_000; i++) {
            trieBuilder.addKeyword("add" + i);
        }
        Trie trie = trieBuilder.build();

        long st = System.currentTimeMillis();
        Collection<Emit> emits = trie.parseText("ushers");
        long et = System.currentTimeMillis();
        System.out.println(et - st);
        for (Emit emit : emits) {
            String keyword = emit.getKeyword();
            System.out.println(keyword);
        }
    }
}
