package com.github.ac;

import org.ahocorasick.trie.Emit;
import org.ahocorasick.trie.Trie;

import java.util.Collection;

/**
 * 多模式匹配
 */
public class Use_Ac {
    public static void main(String[] args) {
        Trie trie = Trie.builder()
                .addKeyword("hers")
                .addKeyword("his")
                .addKeyword("she")
                .addKeyword("he")
                .build();
        Collection<Emit> emits = trie.parseText("ushers");
        for (Emit emit : emits) {
            String keyword = emit.getKeyword();
            System.out.println(keyword);
        }
    }
}
