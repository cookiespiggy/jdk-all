package com.github.ftp;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.pool2.impl.GenericObjectPool;

public class Jedis用的就是GenericObjectPool {

    public static void main(String[] args) {

        GenericObjectPool<FTPClient> ftpClientGenericObjectPool = new GenericObjectPool(new FtpClientFactory());
        //ftpClientGenericObjectPool.setTestOnBorrow(true);
        //  setTestOnBorrow 这个很有意思，默认为false，不泡异常，但你用的时候肯定头疼，因为它不可用
        //  为 true，抛异常，返回null，我用的时候，仅仅判断不为null即可，不用判断可不可用了
        FTPClient ftpClient = null;
        try {
            ftpClient = ftpClientGenericObjectPool.borrowObject();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(ftpClient);

    }
}
