package com.github.disruptor;

public class Sequence {

    private int value;

    public synchronized int getNext() {
        return this.value++;
    }
}
