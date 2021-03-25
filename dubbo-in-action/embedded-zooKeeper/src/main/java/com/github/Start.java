package com.github;

public class Start {
    public static void main(String[] args) throws InterruptedException {
        new EmbeddedZooKeeper(2181, false).start();
        // wait for embedded zookeeper start completely.
        Thread.sleep(1000);
    }
}
