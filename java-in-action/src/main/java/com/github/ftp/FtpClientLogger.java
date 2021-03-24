package com.github.ftp;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.net.ProtocolCommandEvent;
import org.apache.commons.net.ProtocolCommandListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FtpClientLogger implements ProtocolCommandListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(FtpClientLogger.class);
    @Getter
    @Setter
    private String clientId;

    public FtpClientLogger(final String clientId) {
        this.clientId = clientId;
    }

    @Override
    public void protocolCommandSent(ProtocolCommandEvent protocolCommandEvent) {
        if (!"PASS".equalsIgnoreCase(protocolCommandEvent.getCommand())) {
            LOGGER.debug(clientId + " > " + protocolCommandEvent.getMessage().trim());
        }
    }

    @Override
    public void protocolReplyReceived(ProtocolCommandEvent protocolCommandEvent) {
        LOGGER.debug(clientId + " < " + protocolCommandEvent.getMessage().trim());
    }
}
