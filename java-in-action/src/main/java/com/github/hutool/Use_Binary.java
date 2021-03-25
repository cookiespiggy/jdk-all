package com.github.hutool;

import cn.hutool.core.util.HexUtil;
import io.vertx.core.buffer.Buffer;

public class Use_Binary {
    public static void main(String[] args) {

        byte[] bytes = new byte[4];

        bytes[0] = 0x00;
        bytes[0] = 0x11;
        bytes[0] = 0x22;
        bytes[0] = 0x33;

        System.out.println(HexUtil.encodeHexStr(bytes));


//        BufferUtil.

    }
}
