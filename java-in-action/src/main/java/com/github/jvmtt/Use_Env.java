package com.github.jvmtt;

public class Use_Env {
    public static void main(String[] args) {

        System.out.println(System.getProperty("sun.arch.data.model"));

        System.out.println(System.getProperty("os.arch"));

        System.out.println(Runtime.class.getPackage().getImplementationVersion());

    }
}
