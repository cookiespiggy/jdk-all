package com.github.ftp;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;

import java.io.IOException;

// https://juejin.cn/post/6844903728747642893
//@Slf4j
public class FtpClientFactory implements PooledObjectFactory<FTPClient> {

    @Override
    public PooledObject<FTPClient> makeObject() throws Exception {
        FTPClient ftpClient = new FTPClient();

        ftpClient.addProtocolCommandListener(new FtpClientLogger("host"));

        ftpClient.setControlEncoding("utf-8");
        ftpClient.setConnectTimeout(30000);
        try {
            ftpClient.connect("host", 21);
            int replyCode = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(replyCode)) {
                ftpClient.disconnect();
                // log.warn("FTPServer refused connection,replyCode:{}", replyCode);
                return null;
            }
            if (!ftpClient.login("", "")) {
                ftpClient.disconnect();
                // log.warn("ftpClient login failed... username is {}; password: {}", config.getUsername(), config.getPassword());
            }
            ftpClient.setBufferSize(1024);
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            if (false) {
                ftpClient.enterLocalPassiveMode();
            }
            return new DefaultPooledObject<>(ftpClient);
        } catch (IOException e) {
            //log.error("create ftp connection failed...", e);
        }
        return null;
    }

    @Override
    public void destroyObject(PooledObject<FTPClient> pooledObject) throws Exception {
        if (pooledObject == null) {
            return;
        }
        FTPClient ftpClient = pooledObject.getObject();
        try {
            if (ftpClient.isConnected()) {
                ftpClient.logout();
            }
        } catch (IOException io) {
            //log.error("ftp client logout failed...{}", io);
        } finally {
            try {
                ftpClient.disconnect();
            } catch (IOException io) {
                //log.error("close ftp client failed...{}", io);
            }
        }
    }

    @Override
    public boolean validateObject(PooledObject<FTPClient> pooledObject) {
        try {
            FTPClient ftpClient = pooledObject.getObject();
            return ftpClient.sendNoOp();
        } catch (IOException e) {
            //log.error("Failed to validate client: {}", e);
        }
        return false;
    }

    @Override
    public void activateObject(PooledObject<FTPClient> pooledObject) throws Exception {

    }

    @Override
    public void passivateObject(PooledObject<FTPClient> pooledObject) throws Exception {

    }
}
