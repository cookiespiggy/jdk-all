package com.github.ftp;

import cn.hutool.core.io.IoUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class MyFTP {

    static class Test {
        public static void main(String[] args) throws IOException, InterruptedException {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(InetAddress.getLocalHost(), 3705));
            Thread.sleep(5000);
            OutputStream out = socket.getOutputStream();
            InputStream in = socket.getInputStream();
            IoUtil.writeUtf8(out, false, "hello");
            in.close();
            out.close();
        }
    }

    // port 主动模式
    static class ActiveMode {
        public static void main(String[] args) throws IOException {
            // 随机
            ServerSocket portSocket = new ServerSocket(0, 1, InetAddress.getLocalHost());
            //portSocket.setSoTimeout(300_000);
            System.out.println(portSocket.getLocalPort());
            Socket clientSocket = portSocket.accept();
            clientSocket.setSoTimeout(1_000);
            portSocket.close();
            InputStream in = clientSocket.getInputStream();
            OutputStream out = clientSocket.getOutputStream();
            String s = IoUtil.readUtf8(in);
            System.out.println(s);
            System.in.read();
        }
    }


}
