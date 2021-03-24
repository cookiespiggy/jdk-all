package com.github.ftp;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.pool2.impl.GenericObjectPool;

public class MyPool1 {
    public static void main(String[] args) {

        GenericObjectPool<FTPClient> ftpClientPool = new GenericObjectPool<>(new FtpClientFactory());
        // GenericObjectPool<FTPClient> ftpClientPool = new GenericObjectPool<>(new FtpClientFactory(), null);

//        System.out.println(ftpClientPool.getMinIdle());

        System.out.println(ftpClientPool.getTestOnBorrow()); // false

        // 其实还要看服务端的server配置
        // socket
    }
}
