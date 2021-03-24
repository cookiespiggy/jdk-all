package com.github.jol;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

import static java.lang.System.out;

public class JOLSample_01_Basic {

    /*
     * This sample showcases the basic field layout. 字段布局
     * You can see a few notable things here:
     *   a) how much the object header consumes; 对象头消耗了多少
     *   b) how fields are laid out; 字段的布局方式
     *   c) how the external alignment beefs up the object size 外部对准如何增强对象尺寸
     */


    public static void main(String[] args) throws Exception {

        out.println(VM.current().details());
        out.println("==================");
        out.println(ClassLayout.parseClass(A.class).toPrintable());
        out.println(ClassLayout.parseClass(B.class).toPrintable());

        out.println("=======================");


        A a = new A();
        /* ？？
        com.github.jol.JOLSample_01_Basic$A object internals:
         OFFSET  SIZE      TYPE DESCRIPTION                               VALUE
              0     4           (object header)                           05 00 00 00 (00000101 00000000 00000000 00000000) (5)
              4     4           (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
              8     4           (object header)                           81 32 01 f8 (10000001 00110010 00000001 11111000) (-134139263)
             12     1   boolean A.f                                       false
             13     3           (loss due to the next object alignment)
        Instance size: 16 bytes
        Space losses: 0 bytes internal + 3 bytes external = 3 bytes total
         */
        synchronized (a) {
            System.out.println("加锁");
        }
        /*
        加锁 ？？
        com.github.jol.JOLSample_01_Basic$A object internals:
         OFFSET  SIZE      TYPE DESCRIPTION                               VALUE
              0     4           (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
              4     4           (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
              8     4           (object header)                           81 32 01 f8 (10000001 00110010 00000001 11111000) (-134139263)
             12     1   boolean A.f                                       false
             13     3           (loss due to the next object alignment)
        Instance size: 16 bytes
        Space losses: 0 bytes internal + 3 bytes external = 3 bytes total
         */
        System.out.println(ClassLayout.parseInstance(a).toPrintable());

    }


    public static class A {
        boolean f;
    }

    /*

    com.github.jol.JOLSample_01_Basic$A object internals:
     OFFSET  SIZE      TYPE DESCRIPTION                               VALUE
          0    12           (object header)                           N/A
         12     1   boolean A.f                                       N/A
         13     3           (loss due to the next object alignment)
    Instance size: 16 bytes
    Space losses: 0 bytes internal + 3 bytes external = 3 bytes total

     */

    public static class B {
        long f;
    }
    /*

    com.github.jol.JOLSample_02_Alignment$A object internals:
     OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
          0    12        (object header)                           N/A
         12     4        (alignment/padding gap)
         16     8   long A.f                                       N/A
    Instance size: 24 bytes
    Space losses: 4 bytes internal + 0 bytes external = 4 bytes total

     */

    /*
    我们编写一个Java类，编译后会生成.class文件，
    当类加载器将class文件加载到jvm时，会生成一个Klass类型的对象(c++)，称为类描述元数据，存储在方法区中，即jdk1.8之后的元数据区。

    当使用new创建对象时，就是根据类描述元数据Klass创建的对象oop，存储在堆中。每个java对象都有相同的组成部分，称为对象头。
     */
    static class C {

    }
}
