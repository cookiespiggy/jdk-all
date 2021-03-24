package com.github.ftp;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.pool2.BaseObjectPool;

// 不太赞成自己去实现连接池,这样会带来额外的维护成本...
// jedis GenericObjectPool
public class MyPool2 extends BaseObjectPool<FTPClient> {
    public static void main(String[] args) {

    }

    @Override
    public FTPClient borrowObject() throws Exception {
        return null;
    }

    @Override
    public void returnObject(FTPClient ftpClient) throws Exception {

    }

    @Override
    public void invalidateObject(FTPClient ftpClient) throws Exception {

    }
}
