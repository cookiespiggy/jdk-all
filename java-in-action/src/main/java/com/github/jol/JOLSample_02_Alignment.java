package com.github.jol;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

import static java.lang.System.out;

public class JOLSample_02_Alignment {


    /*
     * This is the more advanced field layout example.
     *
     * Because the underlying hardware platform often requires aligned accesses
     * to maintain the performance and correctness, it is expected the fields
     * are aligned by their size. For booleans it does not mean anything, but
     * for longs it's different. In this example, we can see the long field
     * is indeed aligned for 8 bytes, sometimes making the gap after the
     * object header.
     */


    public static void main(String[] args) throws Exception {
        out.println(VM.current().details());
        out.println(ClassLayout.parseClass(A.class).toPrintable());
    }

    public static class A {
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

}
