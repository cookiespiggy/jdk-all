package com.github.jol;

import org.openjdk.jol.info.ClassLayout;

// 8对齐 与cpu有关系
public class JOLSample_00_Abc {

    /*
    -XX:-UseCompressedOops
    total: 16 + 4 + 4(8对齐) = 24

    -XX:+UseCompressedOops
    total: 12 + 4  = 16
     */
    static class A {

        int num;

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }
    }

    public static void main(String[] args) {
        A obj = new A();
        System.out.println(ClassLayout.parseInstance(obj).instanceSize());
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());
    }
}
