package com.github.ftp;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTPConnectionClosedException;
import org.apache.commons.net.ftp.FTPReply;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

/**
 * 需要实际的去验证一下，这种模式究竟怎么样
 * 频繁的创建和关闭连接好不好？
 */
public class ZFTPConnector {

    private String server;
    private int port;
    private String userID;
    private String password;
    private org.apache.commons.net.ftp.FTPClient FTPClient;
    private boolean FTPActiveMode;

    /**
     * Basic constructor with minimal parameters required.
     *
     * @param server        LPAR name or IP address to connect to.
     * @param port          LPAR password.
     * @param userID        UserID.
     * @param password      User password.
     * @param FTPActiveMode FTP data transfer mode (true=active, false=passive)
     */
    ZFTPConnector(String server, int port, String userID, String password, boolean FTPActiveMode) {
        // Copy values
        this.server = server;
        this.port = port;
        this.userID = userID;
        this.password = password;
        this.FTPActiveMode = FTPActiveMode;
        this.FTPClient = null;
    }

    /**
     * Try to connect to the <b><code>server</code></b> using the parameters passed to the constructor.
     *
     * @return Whether the connection was established using the parameters passed to the constructor.
     * @see ZFTPConnector#ZFTPConnector(String, int, String, String, boolean, String)
     */
    private boolean connect() {
        // 1. Disconnect and ignore error.
        try {
            this.FTPClient.disconnect();
        } catch (IOException ignored) {

        }
        // Perform the connection.
        try {
            int reply; // Temp value to contain server response.

            // Try to connect.
            this.FTPClient.connect(this.server, this.port);

            // After connection attempt, check the reply code to verify success.
            reply = this.FTPClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                // Bad reply code.
                this.FTPClient.disconnect(); // Disconnect from LPAR.
                //this.err("FTP server refused connection."); // Print error.
                return false; // Finish with failure.
            }
            //this.log("FTP: connected to " + server + ":" + port);
        }
        // IOException handling
        catch (IOException e) {
            // Close the connection if it's still open.
            if (this.FTPClient.isConnected()) {
                try {
                    this.FTPClient.disconnect();
                } catch (IOException f) {
                    // Do nothing
                }
            }
            //this.err("Could not connect to server.");
            //e.printStackTrace();
            return false;
        }
        // Finally, return with success.
        return true;
    }

    /**
     * Try to logon to the <b><code>server</code></b> using the parameters passed to the constructor.
     * Also, <code>site filetype=jes jesjobname=* jesowner=*</code> command is invoked.
     *
     * @return Whether the credentials supplied are valid and the connection was established.
     * @see ZFTPConnector#ZFTPConnector(String, int, String, String, boolean, String)
     * @see ZFTPConnector#connect()
     */
    private boolean logon() {
        // 1. log out, ignore error
        try {
            this.FTPClient.logout();
        } catch (IOException ignored) {
        }
        // Check whether we are already connected. If not, try to reconnect.
        if (!this.connect())
            return false; // Couldn't connect to the server. Can't check the credentials.
        // Perform the login process.
        try {
            int reply; // Temp value for server reply code.

            // Try to login.
            if (!this.FTPClient.login(this.userID, this.password)) {
                // If couldn't login, we should logout and return failure.
                this.FTPClient.logout();
                return false;
            }

            // Try to set filetype, jesjobname and jesstatus.
            // this.FTPClient.site("filetype=jes jesjobname=* jesstatus=ALL");
            // Check reply.
            reply = this.FTPClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                this.FTPClient.disconnect();
                //this.err("FTP server refused to change FileType and JESJobName.");
                return false;
            }
        } catch (IOException e) {
            if (this.FTPClient.isConnected()) {
                try {
                    this.FTPClient.disconnect();
                } catch (IOException f) {
                    // do nothing
                }
            }
            //this.err("Could not logon to server.");
            e.printStackTrace();
            return false;
        }

        // If go here, everything went fine.
        return true;
    }

    /**
     * Try logging out of the FTP server.
     * This will not fail at all - instead if the next relogon attempt fails you will see something more accurate.
     */
    private void disconnect() {
        if (this.FTPClient == null)
            return;
        try {
            this.FTPClient.logout();
        } catch (IOException ignored) {
        } finally {
            try {
                this.FTPClient.disconnect();
            } catch (IOException ignored) {
            }
        }
    }

    boolean submit(InputStream inputStream) {
        // Create FTPClient
        this.FTPClient = new org.apache.commons.net.ftp.FTPClient();
        // Make password invisible from log
        this.FTPClient.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(new OutputStreamWriter(System.out, StandardCharsets.UTF_8)), true));
        // Verify connection.
        if (!this.logon()) {
            this.disconnect();
            //this.jobCC = "COULD_NOT_CONNECT";
            return false;
        }
        try {
            // Submit the job.
            if (!this.FTPActiveMode) {
                this.FTPClient.enterLocalPassiveMode();
            }
            this.FTPClient.storeFile("jenkins.sub", inputStream);
            inputStream.close();
        } catch (FTPConnectionClosedException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        // If we are here, everything went fine.
        this.disconnect();
        return true;
    }

}
